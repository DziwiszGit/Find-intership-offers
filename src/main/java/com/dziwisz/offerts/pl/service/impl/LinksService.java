package com.dziwisz.offerts.pl.service.impl;

import com.dziwisz.offerts.pl.model.SelectedPages;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinksService  {

    public List<String> getFewLinks(SelectedPages selectedPages) throws IOException {

        List<String> listWithAllLinks = new ArrayList<>();
        if(selectedPages.isLinkedin()){
            listWithAllLinks.addAll(getLinksFromSingleLink("https://www.linkedin.com/jobs/java-intern-jobs/?originalSubdomain=pl"));
        }
        if(selectedPages.isPracuj()){
            listWithAllLinks.addAll(getLinksFromSingleLink("https://www.pracuj.pl/praca/praktyki%20java;kw/krakow;wp?rd=0"));
        }

        return listWithAllLinks;
    }

    public List<String> getLinksFromSingleLink(String url) throws IOException {
        List<String> listOfLinks = new ArrayList<>(JsoupFindLinkSample.findLinks(url));
        List<String> listToReturn = new ArrayList<>();
        for(int i = 0 ; i < 7 ; i++){
            if(listOfLinks.get(i).charAt(0) == '/' || listOfLinks.get(i).charAt(0) == '#'){
                continue;
            }else{
                listToReturn.add(listOfLinks.get(i));
            }
        }
        return listToReturn;
    }
}
