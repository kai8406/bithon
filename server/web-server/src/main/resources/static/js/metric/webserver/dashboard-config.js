var webServerDashboardConfig = {
    "title": "",
    "charts":[{
        "dataSource": "web-server-metrics",
        "title": "Web Server",
        "width": 4, //1,2,3,4
        "metrics":[{
            "name": "connectionCount"
        },{
          "name": "activeThreads"
        }]
    }]
}
