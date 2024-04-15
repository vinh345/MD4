package example.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JV06Controller {


    @GetMapping("/jv06")
    public String jv06(){
        return "jv06";
    }
}