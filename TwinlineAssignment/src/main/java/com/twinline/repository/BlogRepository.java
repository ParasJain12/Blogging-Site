package com.twinline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twinline.model.Blog;
import com.twinline.model.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
	 List<Blog> findByUser(User user);
}
