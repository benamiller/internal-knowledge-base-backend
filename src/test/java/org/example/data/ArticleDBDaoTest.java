package org.example.data;

import org.example.TestApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class ArticleDBDaoTest {

    @Autowired
    ArticleDBDao articleDBDao;

    @Test
    void getNextArticleID() {
        assertEquals(4, articleDBDao.getNextArticleID());
    }

    @Test
    void getNextCommentID() {
        assertEquals(3, articleDBDao.getNextCommentID());
    }

    @Test
    void getAllByRole() {
    }

    @Test
    void addArticle() {
    }

    @Test
    void getAllCommentsByArticleID() {
    }

    @Test
    void addComment() {
    }

    @Test
    void setRead() {
    }

    @Test
    void setUnread() {
    }
}