package com.w3dai.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * @author NicolaDai
 * @since 2018/8/26
 */

public class Author implements Serializable{
    private String authorName;
    private String authorAddress;
    private String authorCellphone;
    private String authorWechat;

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