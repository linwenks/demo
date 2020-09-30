package com.linw.demo.webmagic.controller.search;

import com.linw.demo.webmagic.dto.search.SearchDtoSubmit;
import com.linw.demo.webmagic.processor.CrepriceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 *
 * @author linw
 *
 */
@Controller
@RequestMapping(value = "/webmagic/api/search/basis/")
public class SearchController {

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${webmagic.download.path}")
	private String webmagicDownloadPath;

	@RequestMapping(value = "page")
	public String page(Map<String, String> param, Model model, HttpServletRequest request, HttpServletResponse response) {

		return "search/page";
	}

	@RequestMapping(value = "submit")
	public String search(SearchDtoSubmit dto, Model model, HttpServletRequest request, HttpServletResponse response) {
		var url = dto.getUrl();
		OOSpider.create(new CrepriceProcessor(templateEngine))
				.addUrl(url)
				.addPipeline(new FilePipeline(webmagicDownloadPath))
				//开启5个线程抓取
				//.thread(1)
				//启动爬虫
				.run();
		return "success";
	}
}