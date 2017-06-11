package com.kxw.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoup {

	public static void main(String[] args) {

		// 1
		/*
		 * String html = "<html><head><title>First parse</title></head>" +
		 * "<body><p>Parsed HTML into a doc.</p></body></html>"; Document doc =
		 * Jsoup.parse(html);
		 * 
		 * 
		 * System.out.println(doc.body()); System.out.println(doc.data());
		 * System.out.println(doc.html()); System.out.println(doc.nodeName());
		 * System.out.println(doc.text()); System.out.println(doc.title());
		 * System.out.println(doc.head());
		 */

		// 2
		/*Document doc = null;
		try {
			doc = Jsoup.connect("http://www.jb51.net/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String title = doc.title();
		System.out.println(title);
*/
		
		File input = new File("CSDN.NET.htm");
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element content = doc.getElementById("content");
		System.out.println(content);
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
		  String linkHref = link.attr("href");
		  String linkText = link.text();
		  System.out.println(linkHref);
		  System.out.println(linkText);
		}
		
	}

}
