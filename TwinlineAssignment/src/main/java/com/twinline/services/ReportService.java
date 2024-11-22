package com.twinline.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.twinline.model.Blog;

@Service
public class ReportService {

	public Map<String, Integer> generateReport(List<Blog> blogs) {
		Map<String, Integer> wordCount = new HashMap<>();
		blogs.forEach(blog -> {
			String[] words = blog.getContent().split("\\s+");
			for (String word : words) {
				wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
			}
		});
		return wordCount.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
