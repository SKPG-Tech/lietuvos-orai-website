package dev.salvijus.orai.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebErrorController implements ErrorController {
    @RequestMapping("/error")
    public String errorPage(HttpServletRequest request, Model model) {
        model.addAttribute("error", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        return "error";
    }
}
