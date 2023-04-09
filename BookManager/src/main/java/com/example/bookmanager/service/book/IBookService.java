package com.example.bookmanager.service.book;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.service.IGeneralService;

import java.util.List;

public interface IBookService extends IGeneralService<Book> {
    Long getTotalPrice();

    List<Book> findAllByNameContainingAndAuthorContainingAndPriceBetween(String name, String author, Long minPrice, Long maxPrice);
}
