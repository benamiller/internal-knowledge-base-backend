package org.example.exceptions;

public class ArticleDoesNotExistException extends Exception{
    public ArticleDoesNotExistException() {
        super("This article does not exist");
    }
}
