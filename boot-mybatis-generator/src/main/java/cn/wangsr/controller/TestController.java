package cn.wangsr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/01")
    public String toIndex(){

        return "index";
    }


    @RequestMapping("/02")
    @ResponseBody
    public String toIndex02(){

        return "index";
    }


}
