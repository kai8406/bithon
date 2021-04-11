var http_client_metrics_dashboard = {
    "title": "",
    "charts":[{
        "dataSource": "http-client-metrics",
        "title": "QPS",
        "width": 4, //1,2,3,4
        "metrics":[{
            "name": "qps"
        }]
    },{
        "dataSource": "http-client-metrics",
        "title": "Response Time",
        "width": 4, //1,2,3,4
        "yAxis": [ {
            "unit": "millisecond"
          }
        ],
        "metrics":[{
          "name": "avgResponseTime"
        },{
          "name": "minResponseTime"
        },{
          "name": "maxResponseTime"
        }]
    },{
        "dataSource": "http-client-metrics",
        "title": "Error",
        "width": 4, //1,2,3,4
        "metrics":[{
          "name": "count4xx"
        },{
           "name": "count5xx"
        },{
           "name": "countException"
        }]
     },{
        "dataSource": "http-client-metrics",
        "title": "IO",
        "width": 4, //1,2,3,4
        "yAxis": [ {
           "minInterval": 1024 * 1024,
           "interval": 1024 * 1024 * 1024,
           "unit": "binary_byte"
        }],
        "metrics":[{
            "name": "requestBytes"
        },{
          "name": "responseBytes"
        }]
    }]
}