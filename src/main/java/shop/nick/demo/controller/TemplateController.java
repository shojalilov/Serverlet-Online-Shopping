package shop.nick.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import shop.nick.demo.config.CustomConfig;
import shop.nick.demo.entity.Template;
import shop.nick.demo.payload.CustomResponse;
import shop.nick.demo.service.TemplateService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TemplateController {

    private final TemplateService templateService;
    private final CustomConfig customConfig;

    public TemplateController(TemplateService templateService, CustomConfig customConfig) {
        this.templateService = templateService;
        this.customConfig = customConfig;
    }


    @PostMapping("/template")
    public void saveTemplate(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        templateService.saveTemplate(request);

        response.sendRedirect("/admin/index");
    }

    @PostMapping("/template/add-image")
    public void addTemplateImage(Model model, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomResponse customResponse = templateService.addTemplateImage(request);
        Template template = (Template) customResponse.getObject();

        model.addAttribute("user", customConfig.getPrincipal());
        model.addAttribute("template", templateService.getTemplate(template));


        response.sendRedirect("/admin/template/" + template.getId());
    }



    @GetMapping("/template")
    public void getTemplate(Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("templates", templateService.getTemplates());

        response.sendRedirect("/admin/index");
    }

    @GetMapping("/template/buy/{id}")
    public void buyTemplate(Model model, HttpServletResponse response, @PathVariable Integer id) throws IOException {
        model.addAttribute("templates", templateService.getTemplates());

        response.sendRedirect("/admin/index");
    }

    @GetMapping("/admin/template/{id}")
    public String getTemplateOne(Model model, HttpServletResponse response, @PathVariable Integer id) throws IOException {
        model.addAttribute("user", customConfig.getPrincipal());
        model.addAttribute("template", templateService.getTemplateOne(id));

        return "/admin/template";
//        response.sendRedirect("/admin/index");
    }
}
