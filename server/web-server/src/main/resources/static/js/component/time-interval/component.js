class TimeInterval {

    constructor(option) {
        this._listeners = [];

        this._control = $('<select id="intervalSelector" class="form-control">       ' +
                          '    <option value="5" data-unit="minute">5 min</option>   ' +
                          '    <option value="15" data-unit="minute">15 min</option> ' +
                          '    <option value="30" data-unit="minute">30 min</option> ' +
                          '    <option value="1" data-unit="hour">1 hour</option>    ' +
                          '    <option value="3" data-unit="hour">3 hour</option>    ' +
                          '    <option value="6" data-unit="hour">6 hour</option>    ' +
                          '    <option value="12" data-unit="hour">12 hour</option>  ' +
                          '    <option value="24" data-unit="hour">24 hour</option>  ' +
                          '</select>                                                 ');

        $(this._control).change(()=>{
            var option = $(this._control).find("option:selected");
            var val = option.val();
            var unit = option.attr('data-unit');
            var fn = function(){
                    return {
                     start: moment().utc().subtract(val, unit).local().toISOString(),
                     end: moment().utc().local().toISOString()
                }
            };
            $.each(this._listeners, (index, listener)=>{
                listener(fn);
            });
        });
    }

    childOf(element) {
        $(element).append(this._control);
        return this;
    }

    registerIntervalChangedListener(listener) {
        this._listeners.push(listener);
    }
}