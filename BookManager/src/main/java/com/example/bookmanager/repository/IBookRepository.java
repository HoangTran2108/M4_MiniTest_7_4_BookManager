package com.example.bookmanager.repository;

import com.example.bookmanager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByNameContainingAndAuthorContainingAndPriceBetween(String name, String author, Long minPrice, Long maxPrice);

}
