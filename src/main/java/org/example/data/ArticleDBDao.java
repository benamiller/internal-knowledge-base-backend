package org.example.data;

import org.example.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ArticleDBDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArticleDBDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add() {
        System.out.println(jdbcTemplate.query("SELECT * FROM Articles;", new ArticleMapper()));
    }

    private static final class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int index) throws SQLException {
            Article article = new Article();
            article.setArticleID(rs.getInt("articleID"));
            article.setArticleSubject(rs.getString("articleSubject"));
            article.setArticleBody(rs.getString("articleBody"));
            article.setAuthorizedRole(rs.getString("authorizedRole"));
            article.setComplete(rs.getBoolean("isComplete"));
            return article;
        }
    }
}
