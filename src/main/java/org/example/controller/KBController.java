package org.example.controller;

import org.example.data.ArticleDBDao;
import org.example.models.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class KBController {
    private final ArticleDBDao articleDAO;

    public KBController(ArticleDBDao articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping
    public void add() {
        articleDAO.add();
    }
}
