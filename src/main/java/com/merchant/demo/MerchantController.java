package com.merchant.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.core.io.ClassPathResource;
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
		return "static/test.html";
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
		    String blogUrl = "static/vn/index.html";
		    Document doc = Jsoup.connect(blogUrl).get();
		    
		    Element link = new Element(Tag.valueOf("a"), "")
		    		  .text("Checkout this amazing website!")
		    		  .attr("href", "http://baeldung.com")
		    		  .attr("target", "_blank");
		    doc.appendChild(link);
		   
	        return doc.toString();
	    }
	    
	    @GetMapping(value = "/vn/welcome3", produces = MediaType.TEXT_HTML_VALUE)
	    @ResponseBody
	    public String welcomeAsHTML3() throws IOException {
	    	Document doc = null;
	    	ClassPathResource resource = new ClassPathResource("static/vn/index.html");
	    	System.out.println("resource" + resource.toString());
	    	try {
	    	    Path path = Paths.get(resource.getURI());
	    	    byte[] content = Files.readAllBytes(path);
	    	    String s = new String(content, "utf-8");
	    	    System.out.println("contents " + s);
			    doc = Jsoup.parse(s);
			    
			    Element link = new Element(Tag.valueOf("a"), "")
			    		  .text("Checkout this amazing website!")
			    		  .attr("href", "http://baeldung.com")
			    		  .attr("target", "_blank");
			    doc.appendChild(link);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
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

	    
	}

}
