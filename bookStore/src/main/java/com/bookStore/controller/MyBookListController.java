package com.bookStore.controller;

import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookListController {

    @Autowired
    MyBookListService myBookListService;
    @RequestMapping("/deleteMyBook/{id}")
    public String deleteBook(@PathVariable("id")int id){

        myBookListService.getDelete(id);
        return "redirect:/my_books";
    }
}
