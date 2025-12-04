package com.example.spring.blog;
import com.example.spring.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "SELECT * FROM todos.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)
    List<Item> fullTextSearch(String text);
}