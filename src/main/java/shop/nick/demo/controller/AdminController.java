package shop.nick.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.nick.demo.config.CustomConfig;
import shop.nick.demo.payload.TemplateDto;
import shop.nick.demo.service.TemplateService;
import shop.nick.demo.utils.AppConstant;

import javax.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    private final TemplateService templateService;
    private final CustomConfig customConfig;

    public AdminController(TemplateService templateService, CustomConfig customConfig) {
        this.templateService = templateService;
        this.customConfig = customConfig;
    }


    @GetMapping("/index")
    public String index(Model model, HttpServletResponse response){

        model.addAttribute("templates", templateService.getTemplates());
        model.addAttribute("user", customConfig.getPrincipal());


        return "admin/index";
    }

}
