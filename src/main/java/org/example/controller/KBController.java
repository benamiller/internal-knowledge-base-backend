package org.example.controller;

import org.example.data.ArticleDBDao;
import org.example.models.Article;
import org.example.models.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class KBController {
    private final ArticleDBDao articleDAO;

    public KBController(ArticleDBDao articleDAO) {
        this.articleDAO = articleDAO;
    }

    @GetMapping
    public void getAllByRole() {
        articleDAO.getAllByRole("MARKETING");
    }

    @PostMapping("/article")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    public void addArticle(@RequestBody Article article) {
        articleDAO.addArticle(article);
    }

//    @PostMapping("/comment")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addComment(@RequestBody Comment comment) {
//        articleDAO.addComment(comment);
//    }
}
