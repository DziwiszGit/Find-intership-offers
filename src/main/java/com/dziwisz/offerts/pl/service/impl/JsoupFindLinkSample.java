package com.dziwisz.offerts.pl.service.impl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JsoupFindLinkSample {


    public static List<String> findLinks(String url) throws IOException {

        Set<String> links = new HashSet<>();
        Document doc = Jsoup.connect(url)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(86400000)
                .get();

        Elements elements = doc.select("a[href]");

        for (Element element : elements) {
            links.add(element.attr("href"));
        }

        return new ArrayList<>(links) ;
    }

}