package com.w3dai.test;

import com.w3dai.domain.Author;
import com.w3dai.repository.AuthorRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    AuthorRepository authorRepository;


    @Before
    public void initData(){
        authorRepository.deleteAll();

        Author author = new Author();
        author.setAuthorName("曾火伦");
        authorRepository.save(author);
        Assert.notNull(author.getId());

        Author author1 = new Author();
        author1.setAuthorName("魏兵");
        authorRepository.save(author1);
        Assert.notNull(author1.getId());

        Author author2 = new Author();
        author2.setAuthorName("张新");
        authorRepository.save(author2);
        Assert.notNull(author2.getId());

        List<Author> authors = authorRepository.findAll();
        Assert.notNull(authors);
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<Author> page = authorRepository.findAll(pageable);
        Assert.notNull(page);
        for(Author author : page.getContent()) {
            logger.info("====user==== user name:{}, department name:{}, role name:{}",
                    author.getAuthorName());
        }
    }

    //@Test
    public void test(){
        Author user1 = authorRepository.findByAuthorName("u%");
        Assert.notNull(user1);
    }
}
