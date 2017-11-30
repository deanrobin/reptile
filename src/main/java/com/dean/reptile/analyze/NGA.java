package com.dean.reptile.analyze;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;

public class NGA {
    private Document doc = null;
    private NGA() {
    }

    public NGA(String html) {
        doc = Jsoup.parse(html);
    }

    public String getJobSearch() {
        try {
            Element title = doc.select("title").first();
            return title.text();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
