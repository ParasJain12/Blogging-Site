package com.twinline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinline.model.Blog;
import com.twinline.model.User;
import com.twinline.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
    private BlogRepository blogRepository;

    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getUserBlogs(User user) {
        return blogRepository.findByUser(user);
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public void updateBlog(Long id, String title, String content) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setTitle(title);
            blog.setContent(content);
            blogRepository.save(blog);
        }
    }
}
