var sql_metrics_dashboard = {
    "title": "SQL metrics",
    "charts":[{
        "dataSource": "sql-metrics",
        "title": "TPS",
        "width": 4, //1,2,3,4,
        "yAxis": [{},{}],
        "metrics":[{
            "name": "tps"
        },{
            "name": "callCount",
            "yAxis": 1
        },{
           "name": "updateCount",
           "yAxis": 1
       },{
           "name": "queryCount",
           "yAxis": 1
       }]
    },{
        "dataSource": "sql-metrics",
        "title": "Response Time",
        "width": 4, //1,2,3,4,
        "yAxis": [{
          "unit": "millisecond"
        }],
        "metrics":[{
            "name": "minResponseTime",
        },{
           "name": "avgResponseTime",
       },{
           "name": "maxResponseTime",
       }]
    }]
}