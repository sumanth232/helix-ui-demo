package com.example.helixdemo.controller;


import com.example.helixdemo.MyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author sumanth on 07/11/17
 */
@Controller
public class MyRestController {

    /*@RequestMapping("/")
    public String myroot() {
        return "Wow it works man !!";
    }*/

    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        int nodesCount = MyClient.getNumberOfNodes();
        model.put("nodes", nodesCount);
        model.put("instanceVsPartitionsExternalView", MyClient.getInstanceVsPartitionsExternalView());
        model.put("instanceVsPartitionsIdealState", MyClient.getInstanceVsPartitionsIdealState());
        model.put("message", this.message);
        return "welcome";
    }
}
