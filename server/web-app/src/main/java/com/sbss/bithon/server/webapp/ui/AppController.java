/*
 *    Copyright 2020 bithon.cn
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.sbss.bithon.server.webapp.ui;

import com.sbss.bithon.server.webapp.services.ServiceDiscovery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/31 12:17 上午
 */
@Controller
public class AppController {

    private final ServiceDiscovery serviceDiscovery;

    public AppController(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @GetMapping("/ui/app/*")
    public String appHomePage(Model model) {
        model.addAttribute("apiHost", serviceDiscovery.getApiHost());
        return "app/index";
    }

    @GetMapping("/ui/app/metric/{appName}/{metricName}")
    public String webServerPage(@PathVariable("appName") String appName,
                                @PathVariable("metricName") String metricName,
                                Model model) {
        model.addAttribute("apiHost", serviceDiscovery.getApiHost());
        model.addAttribute("appName", appName);
        model.addAttribute("metricName", metricName);
        return "app/metric-template";
    }

    @GetMapping("/ui/app/trace/{appName}")
    public String traceHomePage(@PathVariable("appName") String appName,
                                Model model) {
        model.addAttribute("apiHost", serviceDiscovery.getApiHost());
        model.addAttribute("appName", appName);
        return "app/trace";
    }

    @GetMapping("/ui/app/topo/{appName}")
    public String topoHome(@PathVariable("appName") String appName,
                           Model model) {
        model.addAttribute("apiHost", serviceDiscovery.getApiHost());
        model.addAttribute("appName", appName);
        return "app/topo";
    }
}