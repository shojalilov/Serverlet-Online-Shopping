package shop.nick.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.nick.demo.config.CustomConfig;
import shop.nick.demo.entity.User;
import shop.nick.demo.service.SecondaryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthController {

    private final SecondaryService secondaryService;
    private final CustomConfig customConfig;

    public AuthController(SecondaryService secondaryService, CustomConfig customConfig) {
        this.secondaryService = secondaryService;
        this.customConfig = customConfig;
    }


    @GetMapping("/auth/login")
    public String loginPage(HttpServletResponse response) throws IOException {
        User user = customConfig.getPrincipal();
        if (user != null) {
            response.sendRedirect("/auth/authenticated");
        }
        return "auth/login";
    }


    @GetMapping("/auth/authenticated")
    public String authenticatedPage(Model model, HttpServletResponse response) throws IOException {
        User user = customConfig.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user);

            if (secondaryService.orderAdmin(user)) {
                response.sendRedirect("/admin/index");
            }
            return "index";
        }
        return "auth/login";
    }


}
