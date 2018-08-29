package com.w3dai.domain;
import java.io.Serializable;

public class Article implements Serializable{
    private String articleTitle;
    private String articleContent;
    private String[] articleAuthors;

    public String[] getArticleAuthos() {
        return articleAuthors;
    }

    public void setArticleAuthos(String[] articleAuthos) {
        this.articleAuthors = articleAuthos;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}