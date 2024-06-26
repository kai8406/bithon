function onTableComponentButtonClick(id, rowIndex, buttonIndex) {
    const tableComponent = window.gTableComponents[id];
    if (tableComponent != null) {
        tableComponent.onButtonClick(rowIndex, buttonIndex);
    }
}

function onTableComponentColumnToggle(event, id, col) {
    if (event.target.localName === 'input') {
        const tableComponent = window.gTableComponents[id];
        if (tableComponent != null) {
            tableComponent.toggleColumn(col);
        }
    }
    event.stopPropagation();
}

class TableComponent {
    /**
     *
     * @param option
     *     parent: p,
     *     columns: columns
     *     pagination: either a list or an object. If it's an object, should be { pages: [], side: 'server' | 'client' }
     *     tableId: an unique id for the table component
     *     toolbar: {minimize: true, close: false}
     */
    constructor(option) {
        this.vToolbar = Object.assign({
            minimize: true,
            close: false,
            showColumns: false
        }, option.toolbar);

        // view
        this.mStickyHeader = option.stickyHeader !== undefined ? option.stickyHeader : false;
        this.vComponentContainer = $(`<div class="card card-block rounded-0" style="display:none"></div>`);
        this.vTable = this.vComponentContainer.append(`<div class="table-container"><table id="${option.tableId}"></table></div>`).find('table');
        option.parent.append(this.vComponentContainer);

        // view state
        this.mColumnStateKey = 'bithon.table.column' + window.location.pathname + '/' + option.tableId;
        this.mColumnState = this.#loadColumnState();

        this.mColumns = option.columns;
        this.mCreated = false;
        this.mPopoverShown = false;
        this.mTotal = 0;

        this.mServerSort = option.serverSort;
        this.mHasPagination = option.pagination !== undefined && option.pagination.length > 0;
        if (Array.isArray(option.pagination)) {
            this.mPagination = option.pagination;
            this.mPaginationSide = 'server';
        } else if (option.pagination !== undefined) {
            this.mPagination = option.pagination.pages;
            this.mPaginationSide = option.pagination.side;
        } else {
            // Make sure the variable has been defined
            this.mPaginationSide = 'server';
            this.mPagination = [10];
        }

        this.mDetailViewField = null;
        this.mColumnMap = {};

        this.mDefaultOrder = option.order;
        this.mDefaultOrderBy = option.orderBy;

        this.mFormatters = {};
        this.mFormatters['shortDateTime'] = (v) => new Date(v).format('MM-dd hh:mm:ss');
        this.mFormatters['json_string'] = (val, row, index) => `<button class="btn btn-sm btn-outline-info" onclick="toggleTableDetailView('${option.tableId}', ${index})">Toggle</button>`;
        this.mFormatters['detail'] = (val, row, index) => val !== "" ? `<button class="btn btn-sm btn-outline-info" onclick="toggleTableDetailView('${option.tableId}', ${index})">Toggle</button>` : '';
        this.mFormatters['dialog'] = (val, row, index, field) => val !== "" ? `<button class="btn btn-sm btn-outline-info" onclick="showTableDetailViewInDlg('${option.tableId}', ${index}, '${field}')">Show</button>` : '';
        this.mFormatters['block'] = (val) => `<pre>${val.htmlEncode()}</pre>`;
        this.mFormatters['index'] = (val, row, index) => index + 1;
        this.mFormatters['kv'] = (val) => {
            let text = '<pre style="margin-bottom: 0">';
            for (const propName in val) {
                const propVal = val[propName];
                if (typeof propVal === 'string') {
                    text += `<b>${propName}</b>: ${propVal.htmlEncode()}<br/>`;
                } else {
                    text += `<b>${propName}</b>: ${propVal.htmlEncode()}<br/>`;
                }
            }
            text += '</pre>';
            return text;
        };
        this.mFormatters['time'] = (val, row, index, field) => {
            // Get the column definition first
            let format = this.mColumnMap[field].template;

            return new Date(val).format(format);
        };
        this.mFormatters['template'] = (val, row, index, field) => {
            // Get the template from the column definition first
            let template = this.mColumnMap[field].template;

            const interval = this.mStartTimestamp + '/' + this.mEndTimestamp;

            // Find all replacements
            const referencedVariables = new Map();
            {
                const extractVariable = /\{([^}]+)}/g;
                let match;
                while ((match = extractVariable.exec(template)) !== null) {
                    const varName = match[1];
                    referencedVariables.set(varName, true);
                }
            }

            // Do replacement
            referencedVariables.forEach((val, varName) => {
                // interval is a special variable
                let v = varName === 'interval' ? interval : row[varName];
                if (v === undefined || v === null) {
                    v = '';
                }
                template = template.replaceAll(`{${varName}}`, v);
            });

            return template;
        };
        this.mFormatters['timeDuration'] = (val) => val.formatTimeDuration();

        for (let i = 0; i < this.mColumns.length; i++) {

            const column = this.mColumns[i];

            if (column.format !== undefined && column.formatter == null) {
                // formatter is an option provided by bootstrap-table
                column.formatter = this.mFormatters[column.format];
                if (column.format === 'detail' || column.format === 'json_string') {
                    this.mDetailViewField = column.field;
                }
            }

            // original sorter uses string.localeCompare
            // That comparator returns different order from the result ordered by the server,
            // So here we define a new comparator
            column.sorter = (a, b) => this.#compare(a, b);

            // Use the stored state if exists
            const storedState = this.mColumnState[column.field];
            column.visible = storedState === undefined || storedState === null ? true : storedState;

            this.mColumnMap[column.field] = column;
        }
        this.mTableHasDetailView = this.mDetailViewField != null;

        this.mButtons = option.buttons;
        $.each(this.mButtons, (buttonIndex, button) => {
            this.mColumns.push(
                {
                    field: 'id',
                    title: button.title,
                    align: 'center',
                    visible: button.visible,
                    formatter: (cell, row, rowIndex) => {
                        // href is not set, so a 'class' is needed to make default global css render it as an Anchor
                        // style cursor is explicitly set
                        return `<a class="non-exist" style="cursor: pointer" onclick="onTableComponentButtonClick('${option.tableId}', ${rowIndex}, ${buttonIndex})"><span class="fa fa-forward"></span></a>`;
                    }
                }
            );
        })
        if (window.gTableComponents === undefined) {
            window.gTableComponents = {};
        }
        window.gTableComponents[option.tableId] = this;

        $('body').on('click', () => {
            this.#hidePopover();
        });
    }

    #ensureHeader() {
        if (this._header == null) {
            this._header = $(this.vComponentContainer).prepend(
                '<div class="card-header d-flex" style="padding: 0.5em 1em">' +
                '<span class="header-text btn-sm" style="display: none"></span><span class="header-interval btn-sm" style="padding-left: 0"></span>' +
                '<div class="tools ml-auto">' +
                '</div>' +
                '</div>');

            if (this.vToolbar.minimize) {
                const toggleButton = $('<button class="btn btn-sm btn-toggle"><span class="far fa-window-minimize"></span></button>');
                this._header.find('.card-header').append(toggleButton);
                toggleButton.click(() => {
                    const tableContainer = this.vComponentContainer.find('.table-container');
                    tableContainer.toggle();

                    if (tableContainer.is(':visible')) {
                        toggleButton.find('span').removeClass('fa-window-maximize').addClass("fa-window-minimize");
                    } else {
                        toggleButton.find('span').removeClass('fa-window-minimize').addClass("fa-window-maximize");
                    }
                });
            }

            if (this.vToolbar.close) {
                const closeButton = $('<button class="btn btn-sm btn-close"><span class="far fa-window-close"></span></button>');
                this._header.find('.card-header').append(closeButton);
                closeButton.click(() => {
                    this.vComponentContainer.hide();
                });
            }
        }
        return this._header;
    }

    header(text) {
        this.#ensureHeader().find('.header-text').html(text).show();
        return this;
    }

    toggleColumn(columnName) {
        let oldState = this.mColumnState[columnName];
        if (oldState === undefined || oldState === null) {
            oldState = true;
        }

        if (oldState === true) {
            this.vTable.bootstrapTable('hideColumn', columnName);
        } else if (oldState === false) {
            this.vTable.bootstrapTable('showColumn', columnName);
        }

        //
        // Save UI state storage
        //
        this.mColumnState[columnName] = !oldState;
        this.#saveColumnState();
    }

    #loadColumnState() {
        if (this.vToolbar.showColumns) {
            const uiState = localStorage.getItem(this.mColumnStateKey);
            if (uiState !== null) {
                return JSON.parse(uiState);
            } else {
                return {};
            }
        } else {
            return {};
        }
    }

    #saveColumnState() {
        if (this.vToolbar.showColumns) {
            localStorage.setItem(this.mColumnStateKey, JSON.stringify(this.mColumnState));
        }
    }

    onButtonClick(rowIndex, buttonIndex) {
        const row = this.vTable.bootstrapTable('getData')[rowIndex];
        this.mButtons[buttonIndex].onClick(rowIndex, row, this.mStartTimestamp, this.mEndTimestamp);
    }

    /**
     * public interface of the component in dashboard
     */
    setOpenHandler(openHandler) {
    }

    /**
     * public interface of the component in dashboard
     */
    resize() {
    }

    /**
     * public interface of the component in dashboard
     */
    showHint(hint) {
    }

    load(option) {
        console.log(option);

        if (option.start !== undefined) {
            this.mStartTimestamp = option.start;
            this.mEndTimestamp = option.end;
        } else {
            this.mStartTimestamp = option.ajaxData.interval.startISO8601;
            this.mEndTimestamp = option.ajaxData.interval.endISO8601;
        }
        this.mQueryParam = option.ajaxData;
        if (option.showInterval !== undefined && option.showInterval) {
            const s = moment(this.mStartTimestamp).local().format('YYYY-MM-DD HH:mm:ss');
            const e = moment(this.mEndTimestamp).local().format('YYYY-MM-DD HH:mm:ss');
            this.#ensureHeader().find('.header-interval').html(`from ${s} to ${e}`);
        }

        if (!this.mCreated) {
            this.mCreated = true;

            // 1 is the border
            let stickyHeaderOffsetLeft = 1;
            let stickyHeaderOffsetRight = 1;
            if (this.mStickyHeader) {
                this.vComponentContainer
                    .parentsUntil('body')
                    .each(function() {
                        // Convert the DOM element to a jQuery object if needed
                        const parent = $(this);
                        stickyHeaderOffsetLeft += parseInt(parent.css('padding-left'), 10);
                        stickyHeaderOffsetRight += parseInt(parent.css('padding-right'), 10);
                });
            }

            const tableOption = {
                url: option.url,
                method: 'post',
                contentType: "application/json",
                showRefresh: false,
                responseHandler: (data, jqXHR) => {
                    const ret = option.responseHandler(data);

                    if (jqXHR.status == 200 && data.limit !== undefined && data.limit !== null) {
                        if (data.limit.offset == 0) {
                            this.mTotal = ret.total;
                        } else {
                            // For pagination request, the 'total' is not returned from the server,
                            // But bootstrap-table requires the 'total' field tor refresh the pagination
                            // So we use the cached 'total'
                            ret.total = this.mTotal;
                        }
                    }
                    return ret;
                },

                sidePagination: this.mPaginationSide,
                pagination: this.mHasPagination,

                serverSort: this.mServerSort,
                silentSort: !this.mServerSort,
                sortName: this.mDefaultOrderBy,
                sortOrder: this.mDefaultOrder,

                queryParamsType: '',
                queryParams: (params) => this.#getQueryParams(params),

                columns: this.mColumns,

                detailView: this.mTableHasDetailView,
                detailFormatter: (index, row) => this.#showDetail(index, row),

                stickyHeader: this.mStickyHeader,
                stickyHeaderOffsetLeft: stickyHeaderOffsetLeft,
                stickyHeaderOffsetRight: stickyHeaderOffsetRight,
                theadClasses: 'thead-light',



                onLoadError: (status, jqXHR) => {
                    let message = jqXHR.responseText;
                    if (jqXHR.responseJSON !== undefined && jqXHR.responseJSON.message !== undefined) {
                        message = '<pre style="margin:0 !important">' + jqXHR.responseJSON.message.htmlEncode() + '</pre>';
                    }

                    // Show Popover
                    this.#ensureHeader().find('.header-text')
                                        .popover('dispose')
                                        .popover({title: 'Error', trigger: 'focus', html: true, content: message, placement: 'bottom' })
                                        .popover('show')
                                        .on('shown.bs.popover', () => {
                                           this.mPopoverShown = true;
                                        })
                                        .on('hidden.bs.popover', () => {
                                           this.mPopoverShown = false;
                                        });
                },
                onLoadSuccess: () => {
                    this.#hidePopover();
                }
            };
            if (this.mHasPagination) {
                tableOption.paginationPreText = '<';
                tableOption.paginationNextText = '>';
                tableOption.pageNumber = 1;
                tableOption.pageSize = this.mPagination[0];
                tableOption.pageList = this.mPagination;
            }

            this.vTable.bootstrapTable(tableOption);

            this.#createShowColumnDropdownList();
        } else {
            if (this.mHasPagination) {
                this.vTable.bootstrapTable('refresh', {pageNumber: 1});
            } else {
                this.vTable.bootstrapTable('refresh');
            }
        }
        this.vComponentContainer.show();
    }

    #createShowColumnDropdownList() {
        if (!this.vToolbar.showColumns)
            return;

        let headerText = this.#ensureHeader().find('.header-text').html();
        if (headerText === '') {
            headerText = 'Columns';
        }

        // Build the dropdown list
        let dropDownList = '<div class="btn-group dropright"><button type="button" class="btn btn-sm dropdown-toggle header-text" data-toggle="dropdown" aria-expanded="false">' + headerText +
            '<div class="dropdown-menu dropdown-menu-lg-right">';
        {
            const tableId = this.vTable.attr('id');
            for (let i = 0; i < this.mColumns.length; i++) {
                const col = this.mColumns[i];
                const checkedAttribute = col.visible ? 'checked' : '';
                dropDownList += `<label class="dropdown-item dropdown-item-marker" onclick="onTableComponentColumnToggle(event, '${tableId}', '${col.field}');"><input type="checkbox" ${checkedAttribute}><span>&nbsp;${col.title}</span></label>`;
            }
        }
        dropDownList += '</div></button></div>';

        // Add the dropdown list to DOM
        this.#ensureHeader().find('.header-text').replaceWith(dropDownList);
    }

    #compare(a, b) {
        if (a === b) {
            return 0;
        } else {
            return a < b ? -1 : 1;
        }
    }

    #showDetail(index, row) {
        let cell = row[this.mDetailViewField];
        const column = this.mColumnMap[this.mDetailViewField];
        if (column.format === 'json_string') {
            cell = JSON.stringify(JSON.parse(cell), null, 2);
        }
        return '<pre>' + cell + '</pre>';
    }

    #getQueryParams(params) {
        if (this.mHasPagination && this.mPaginationSide === 'server') {
            // Compatible with old interface
            this.mQueryParam.pageNumber = params.pageNumber - 1;
            this.mQueryParam.pageSize = params.pageSize;

            this.mQueryParam.limit = {
                limit: params.pageSize,
                offset: this.mQueryParam.pageNumber * this.mQueryParam.pageSize
            }
        }

        if (params.sortName === undefined || params.sortName == null) {
            delete this.mQueryParam.orderBy;
        } else {
            this.mQueryParam.orderBy = {
                name: params.sortName,
                order: params.sortOrder
            };
        }
        return this.mQueryParam;
    }

    show() {
        this.vComponentContainer.show();
    }

    clear() {
        this.vTable.bootstrapTable('removeAll');
    }

    hide() {
        this.vComponentContainer.hide();
    }

    getColumns() {
        return this.mColumns;
    }

    #hidePopover() {
        if (!this.mPopoverShown) {
            return;
        }

        $(this._header).find('.header-text').popover('dispose');
    }
}

function toggleTableDetailView(tableId, index) {
    $('#' + tableId).bootstrapTable('toggleDetailView', index);
}

function showTableDetailViewInDlg(tableId, index, field) {
    const rows = $('#' + tableId).bootstrapTable('getData');
    const row = rows[index];
    const cell = row[field];

    bootbox.dialog({
        centerVertical: true,
        size: 'xl',
        onEscape: true,
        backdrop: true,
        message: `<pre id="tagValueView">${cell}\n</pre>`,
    });
}