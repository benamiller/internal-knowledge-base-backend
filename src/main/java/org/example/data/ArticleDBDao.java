package org.example.data;

import org.example.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDBDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ArticleDBDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNextArticleID() {
        final String sql = "SELECT max(articleID) FROM Articles;";
        return Integer.parseInt(jdbcTemplate.queryForList(sql).get(0).get("max(gameID)").toString());
    }

    public void getAllByRole(String authorizedRole) {
        List<Article> arrayList = jdbcTemplate.query("SELECT * FROM Articles WHERE authorizedRole = ?;", new ArticleMapper(), authorizedRole);
        arrayList.forEach((article -> {
            System.out.println(article.getArticleID());
            System.out.println(article.getArticleSubject());
            System.out.println(article.getArticleBody());
            System.out.println(article.getAuthorizedRole());
            System.out.println(article.isComplete());
        }));
    }

    public void addArticle(Article article) {
        int articleID = getNextArticleID();

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
