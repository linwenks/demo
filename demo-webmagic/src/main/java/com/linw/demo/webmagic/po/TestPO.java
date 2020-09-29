package com.linw.demo.webmagic.po;

import us.codecraft.webmagic.model.annotation.ExtractBy;

public class TestPO {

    @ExtractBy("//a[@id='cb_post_title_url']/text()")
    private String title;

    @ExtractBy("//div[@id='cnblogs_post_body']/tidyText()")
    private String post;

    @ExtractBy("//div[@class='postDesc']//span[@id='post-date']/text()")
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
