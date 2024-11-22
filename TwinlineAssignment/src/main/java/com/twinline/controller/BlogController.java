package com.twinline.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twinline.services.BlogService;
import com.twinline.model.User;
import com.twinline.model.Blog;

@Controller
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
    private BlogService blogService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("blogs", blogService.getUserBlogs(user));
        return "dashboard";
    }

    @PostMapping("/create")
    public String createBlog(@RequestParam String title, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUser(user);
        blogService.saveBlog(blog);
        return "redirect:/blogs/dashboard";
    }
    
    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        if (blog != null) {
            model.addAttribute("blog", blog);
            return "edit-blog";
        }
        return "redirect:/blogs/dashboard";
    }

    @PostMapping("/update/{id}")
    public String updateBlog(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        blogService.updateBlog(id, title, content);
        return "redirect:/blogs/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs/dashboard";
    }
}
