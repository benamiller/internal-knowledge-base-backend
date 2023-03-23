package org.example.data;

import org.example.models.Article;
import org.example.models.Comment;
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
        return Integer.parseInt(jdbcTemplate.queryForList(sql).get(0).get("max(articleID)").toString()) + 1;
    }

    public int getNextCommentID() {
        final String sql = "SELECT max(commentID) FROM Comments";
        return Integer.parseInt(jdbcTemplate.queryForList(sql).get(0).get("max(commentID").toString()) + 1;
    }

    public List<Article> getAllByRole(String authorizedRole) {
        List<Article> arrayList = jdbcTemplate.query("SELECT * FROM Articles WHERE authorizedRole = ?;", new ArticleMapper(), authorizedRole);
        arrayList.forEach((article -> {
            System.out.println(article.getArticleID());
            System.out.println(article.getArticleSubject());
            System.out.println(article.getArticleBody());
            System.out.println(article.getAuthorizedRole());
            System.out.println(article.isComplete());
        }));
        return arrayList;
    }

    public void addArticle(Article article) {
        int articleID = getNextArticleID();
        article.setArticleID(articleID);
        jdbcTemplate.update("INSERT INTO Articles VALUES (?, ?, ?, ?, ?);",
                articleID,
                article.getArticleSubject(),
                article.getArticleBody(),
                article.getAuthorizedRole(),
                article.isComplete()
        );
    }

    public List<Comment> getAllCommentsByArticleID(int articleID) {
        List<Comment> arrayList = jdbcTemplate.query("SELECT * FROM Comments WHERE articleID = ?;", new CommentMapper(), articleID);
        arrayList.forEach((comment) -> {
            System.out.println(comment.getCommentID());
            System.out.println(comment.getArticleID());
            System.out.println(comment.getCommentBody());
        });
        return arrayList;
    }

    public void addComment(Comment comment) {
        int commentID = getNextCommentID();
        comment.setCommentID(commentID);
        jdbcTemplate.update("INSERT INTO Comments VALUES (?, ?, ?);",
            commentID,
            comment.getArticleID(),
            comment.getCommentBody()
        );
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

    private static final class CommentMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int index) throws SQLException {
            Comment comment = new Comment();
            comment.setCommentID(rs.getInt("commentID"));
            comment.setArticleID(rs.getInt("articleID"));
            comment.setCommentBody(rs.getString("commentBody"));
            return comment;
        }
    }
}
