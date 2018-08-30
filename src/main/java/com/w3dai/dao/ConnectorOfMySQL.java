package com.w3dai.dao;

import com.w3dai.domain.Author;
import com.w3dai.web.Application;

import org.aspectj.lang.annotation.Before;
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
@ContextConfiguration(classes = {Application.JpaConfiguration.class })
public class ConnectorOfMySQL {
    @Autowired
    AuthorRepository authorRepository;

    @Before
    public void initData(){
        authorRepository.deleteAll();
        Author author = new Author();
        author.setAuthorName("曾火伦");
        author.setAuthorCellphone("13801310525");
        author.setAuthorWechat("335860828");

        authorRepository.save(author);
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(1, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<Author> page = userRepository.findAll(pageable);

        for(Author author : page.getContent()){
            System.out.println(author.getAuthorName());
        }
    }

}
