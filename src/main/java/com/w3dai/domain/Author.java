package com.w3dai.domain;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author NicolaDai
 * @since 2018/8/26
 */
@Entity
@Table(name = "Authors")
public class Author implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String authorName;
    private String authorAddress;
    private String authorCellphone;
    private String authorWechat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public String getAuthorCellphone() {
        return authorCellphone;
    }

    public void setAuthorCellphone(String authorCellphone) {
        this.authorCellphone = authorCellphone;
    }

    public String getAuthorWechat() {
        return authorWechat;
    }

    public void setAuthorWechat(String authorWechat) {
        this.authorWechat = authorWechat;
    }
}