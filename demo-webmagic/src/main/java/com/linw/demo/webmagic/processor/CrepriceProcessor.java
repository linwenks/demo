package com.linw.demo.webmagic.processor;

import com.google.common.collect.Lists;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.validation.constraints.Null;
import java.io.File;
import java.util.Map;

/**
 * http://webmagic.io/
 */
public class CrepriceProcessor implements PageProcessor {

    private TemplateEngine templateEngine;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public CrepriceProcessor(@Null TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来

        var titles = page.getHtml().xpath("//table[@id='px']/tbody[@class='ranklist']/tr[@style='display: none']/th/text()").nodes();

        var values = page.getHtml().xpath("//table[@id='px']/tbody[@class='ranklist']/tr[@style='cursor: pointer;']/th/allText()").nodes();

        var list = Lists.newArrayListWithExpectedSize(values.size() / titles.size());
        for (var i=0; i<values.size(); i++) {
            var title = titles.get(i % 6).get();
            list.add(Map.of(title, values.get(i).get()));
        }


        Context context = new Context();
        context.setVariables(emailContent.getContentMap());
        var content = templateEngine.process("email" + File.separator +  template, context);

        page.putField("数据", list);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
                OOSpider.create(new CrepriceProcessor())
                .addUrl("https://www.creprice.cn/ranklist/eyJkaSI6ImFsbCIsInBpIjoiY2hvbmdxaW5nIiwiY2kiOiJjcSIsInB0IjoxMSwidHkiOiJzYWxlIiwidW4iOiJkaXN0cmljdCIsImluIjoiUHJpY2UiLCJtbiI6IjIwMjAtMDgifQ==.html")
                .addPipeline(new JsonFilePipeline("E:\\webmagic\\"))
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
}