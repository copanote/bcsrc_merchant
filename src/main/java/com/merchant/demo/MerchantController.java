package com.merchant.demo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MerchantController {

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
		return "HelloWorld";
	}
	
	@Controller
	public class HtmlController {
	    @GetMapping(value = "/welcome", produces = MediaType.TEXT_HTML_VALUE)
	    @ResponseBody
	    public String welcomeAsHTML() {
	        return "<html>\n" + "<header><title>Welcome</title></header>\n" +
	          "<body>\n" + "Hello world\n" + "</body>\n" + "</html>";
	    }
	    
	    @GetMapping(value = "/welcome2", produces = MediaType.TEXT_HTML_VALUE)
	    @ResponseBody
	    public String welcomeAsHTML2() throws IOException {
		    String blogUrl = "http://localhost:7070/welcome";
		    Document doc = Jsoup.connect(blogUrl).get();
		    
		    Element link = new Element(Tag.valueOf("a"), "")
		    		  .text("Checkout this amazing website!")
		    		  .attr("href", "http://baeldung.com")
		    		  .attr("target", "_blank");
		    doc.appendChild(link);
		   
	        return doc.toString();
	    }
	    
	    

	    
	}

}
