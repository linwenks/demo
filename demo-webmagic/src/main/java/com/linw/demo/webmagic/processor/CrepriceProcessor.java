package com.linw.demo.webmagic.processor;

import com.google.common.collect.Lists;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import javax.validation.constraints.Null;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * http://webmagic.io/
 *
 * https://www.cnblogs.com/justcooooode/p/7913365.html
 *
 *
 */
public class  CrepriceProcessor implements PageProcessor {

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
        var titlesTemp = titles.stream().map(Selectable::get).collect(Collectors.toList());

        var values = page.getHtml().xpath("//table[@id='px']/tbody[@class='ranklist']/tr[@style='cursor: pointer;']/th/allText()").nodes();
        var valuesTemp = values.stream().map(Selectable::get).collect(Collectors.toList());

        var list = Lists.newArrayListWithExpectedSize(valuesTemp.size() / titlesTemp.size());
        var detailList = Lists.newArrayListWithExpectedSize(titlesTemp.size());

        for (var i=0; i<valuesTemp.size(); i++) {
            detailList.add(values.get(i));
            if (i % titlesTemp.size() == (titlesTemp.size() - 1)) {
                list.add(detailList);
                detailList = Lists.newArrayListWithExpectedSize(titlesTemp.size());
            }
        }

        var context = new Context();
        context.setVariable("titles", titlesTemp);
        context.setVariable("list", list);
        var content = templateEngine.process("content/table", context);

       // page.setRawText(content);
        page.putField("", content);
    }

    @Override
    public Site getSite() {
        return site;
    }
/*
    public static void main(String[] args) {
                OOSpider.create(new CrepriceProcessor())
                .addUrl("https://www.creprice.cn/ranklist/eyJkaSI6ImFsbCIsInBpIjoiY2hvbmdxaW5nIiwiY2kiOiJjcSIsInB0IjoxMSwidHkiOiJzYWxlIiwidW4iOiJkaXN0cmljdCIsImluIjoiUHJpY2UiLCJtbiI6IjIwMjAtMDgifQ==.html")
                .addPipeline(new JsonFilePipeline("E:\\webmagic\\"))
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();
    }
    */
}