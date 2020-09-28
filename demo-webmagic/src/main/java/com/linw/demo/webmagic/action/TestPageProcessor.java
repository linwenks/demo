package com.linw.demo.webmagic.action;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

public class TestPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.putField("a", page.getHtml().toString());

        // 部分三：从页面发现后续的url地址来抓取
        //page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://www.creprice.cn/ranklist/eyJkaSI6ImFsbCIsInBpIjoiY2hvbmdxaW5nIiwiY2kiOiJjcSIsInB0IjoxMSwidHkiOiJzYWxlIiwidW4iOiJkaXN0cmljdCIsImluIjoiUHJpY2UiLCJtbiI6IjIwMjAtMDgifQ==.html")
                .addPipeline(new JsonFilePipeline("E:\\webmagic\\"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}