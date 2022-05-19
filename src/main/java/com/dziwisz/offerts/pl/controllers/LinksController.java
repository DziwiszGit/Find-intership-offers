package com.dziwisz.offerts.pl.controllers;

import com.dziwisz.offerts.pl.model.SelectedPages;
import com.dziwisz.offerts.pl.service.impl.EmailSenderService;
import com.dziwisz.offerts.pl.service.impl.LinksService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class LinksController {

    final EmailSenderService emailSenderService;
    final LinksService linksService;

    public LinksController(EmailSenderService emailSenderService, LinksService linksService) {
        this.emailSenderService = emailSenderService;
        this.linksService = linksService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String basic(){
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String basicToMain() {
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String mail(@RequestParam String email,
                       @RequestParam(name = "pracuj" , defaultValue = "false") boolean pracuj,
                       @RequestParam(name = "linkedin" , defaultValue = "false") boolean linkedin) throws IOException {
        emailSenderService.sendEmail(
                email,emailSenderService.mailFormat(
                        linksService.getFewLinks(new SelectedPages(pracuj,linkedin))));
        return "redirect:/main";
    }


}
