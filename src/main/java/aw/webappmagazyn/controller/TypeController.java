package aw.webappmagazyn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TypeController {

    @GetMapping("/typeAdd")
    public String addType(){
        return "type/typeAdd";
    }

}
