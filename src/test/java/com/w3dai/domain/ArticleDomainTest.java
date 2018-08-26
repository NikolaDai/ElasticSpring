package com.w3dai.domain;

public class ArticleDomainTest {
    public static void main(String[] args){
        Article aArticle = new Article();
        aArticle.setArticleContent("文章主要内容");
        System.out.println(aArticle.getArticleContent());
    }
}
