package com.bookStore.controller;


import com.bookStore.entity.MyBookList;
import com.bookStore.entity.book;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookListService;


    @GetMapping(value={"", "/"})
    public String home(){
        return "home.html";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister.html";
    }

    @GetMapping("/available_books")
    public ModelAndView availableBooks(){
        List<book>list = bookService.getAllBooks();
        return new ModelAndView("availableBooks","book",list);

    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model)
    {
        List<MyBookList>list=myBookListService.getAllMyBooks();
        model.addAttribute("mybooksList",list);
        return "myBooks";
    }

    @PostMapping("/save")
        public String addBook(@ModelAttribute book b){
            bookService.save(b);
           return "redirect:/available_books";
        }


    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        book b= bookService.getBookById(id);
        MyBookList mb= new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String updateBook(@PathVariable("id") int id, Model model){
        book b= bookService.getBookById(id);
        model.addAttribute("book",b);
        return "/Bookedit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id){

        bookService.getDelete(id);
        return "redirect:/available_books";
    }
}
