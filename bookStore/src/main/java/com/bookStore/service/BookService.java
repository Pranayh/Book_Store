package com.bookStore.service;

import com.bookStore.entity.book;
import com.bookStore.repository.BookRepository;
import com.bookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MyBookRepository myBookRepository;
public void save(book b){

    bookRepository.save(b);
}

public List<book> getAllBooks(){
    return bookRepository.findAll();
}

public book getBookById(int id){
    return bookRepository.findById(id).get();
}


public void getDelete(int id){
    bookRepository.deleteById(id);
    myBookRepository.deleteById(id);
}


}
