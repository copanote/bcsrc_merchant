package com.merchant.demo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.merchant.demo.log.LogService;
import com.merchant.demo.log.SrcLoggable;
import com.merchant.demo.xml.lpb.api.comm.header.RequestHeader;

@Controller
public class MerchantController {

	@Resource(name = "vnSrci")
	private String vnHtml;

	@RequestMapping(value = "/merchant")
	public ModelAndView merchant() {
		return new ModelAndView("merchant");
	}

	@RequestMapping(value = "/test")
	public ModelAndView test() {
		return new ModelAndView("test");
	}

	@RequestMapping("/home")
	public String home() {
		return "static/test.html";
	}

	@Controller
	public class HtmlController {
		@GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
		@ResponseBody
		public String welcomeAsHTML() {
			return "<html>\n" + "<header><title>Welcome</title></header>\n" + "<body>\n" + "Hello world\n" + "</body>\n"
					+ "</html>";
		}

		@GetMapping(value = "/welcome2", produces = MediaType.TEXT_HTML_VALUE)
		@ResponseBody
		public String welcomeAsHTML2() throws IOException {
			String blogUrl = "static/vn/index.html";
			Document doc = Jsoup.connect(blogUrl).get();

			Element link = new Element(Tag.valueOf("a"), "").text("Checkout this amazing website!")
					.attr("href", "http://baeldung.com").attr("target", "_blank");
			doc.appendChild(link);

			return doc.toString();
		}
		

		@GetMapping(value = "/vn/welcome3", produces = MediaType.TEXT_HTML_VALUE)
		@ResponseBody
		public String welcomeAsHTML3() throws IOException {
			// TODO:: URL 정책
			Document doc = null;
//	    	ClassPathResource resource = new ClassPathResource("static/vn/index.html");
//	    	System.out.println("resource" + resource.toString());
			try {
//	    	    Path path = Paths.get(resource.getURI());
//	    	    byte[] b = Files.readAllBytes(path);
//	    	    System.out.println(b.length);
//	    	    System.out.println(new String(b, Charset.forName("utf-8")));
				doc = Jsoup.parse(vnHtml);
				System.out.println(vnHtml.length());

				Element link = new Element(Tag.valueOf("script"), "").text(
						"window.__VP__ = { srcClientId: \"43a83638-0122-4a9f-baaa-a4b9eb3caa51\",srcDpaId: \"c21195dc-39eb-422c-8328-db2b0b535ed3\"}")
						.appendText("aaa");
//			    		  .attr("href", "http://baeldung.com")
//			    		  .attr("target", "_blank");
				doc.body().appendChild(link);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return doc.toString();

		}

		@GetMapping(value = "/vn/welcome4", produces = MediaType.TEXT_HTML_VALUE)
		@ResponseBody
		public String welcomeAsHTML4() {
			Document doc = null;
			doc = Jsoup.parse(vnHtml);
			System.out.println(vnHtml.length());

			Element link = new Element(Tag.valueOf("script"), "")
					.text("window.__VP__ = { srcClientId: \"43a83638-0122-4a9f-baaa-a4b9eb3caa51\",srcDpaId: \"c21195dc-39eb-422c-8328-db2b0b535ed3\"}");
			
			doc.body().appendChild(link);
			return doc.toString();

		}

		@RequestMapping("/page")
		public String someOtherPage(HttpServletRequest request, HttpServletResponse response) {
			return "forward:/static/vn/index.html";
		}

		@RequestMapping("/home22")
		public String home() {
			return "forward:/static/test.html";
		}
		@Autowired LogService ls;

		@RequestMapping("/aop")
		@SrcLoggable(name = "aop", owner = "CONT")
		@ResponseBody
		public String aop() {
			ls.helloWorld(RequestHeader.of("command", "pwd", UUID.randomUUID().toString(), LocalDateTime.now()));
			return "aopController";
		}

	}

}
