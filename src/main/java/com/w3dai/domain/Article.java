package com.w3dai.domain;
import java.io.Serializable;

public class Article implements Serializable{
    private String articleTitle;
    private String articleContent;
    private String[] articleAuthos;

    public String[] getArticleAuthos() {
        return articleAuthos;
    }

    public void setArticleAuthos(String[] articleAuthos) {
        this.articleAuthos = articleAuthos;
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