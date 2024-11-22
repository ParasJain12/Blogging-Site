package com.twinline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twinline.model.User;
import com.twinline.model.Blog;
import com.twinline.services.BlogService;
import com.twinline.services.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String report(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Blog> blogs = blogService.getUserBlogs(user);
        model.addAttribute("report", reportService.generateReport(blogs));
        return "report";
    }
}
