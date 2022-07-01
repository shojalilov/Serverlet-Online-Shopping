package shop.nick.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller("/")
public class HomeController {

    @GetMapping
    public String index(Model model, HttpServletResponse response){
        return "index";
    }

}
