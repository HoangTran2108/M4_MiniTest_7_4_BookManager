package com.example.bookmanager.service.book;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Long getTotalPrice() {
        List<Book> books = bookRepository.findAll();
        Long totalPrice = 0L;
        for (Book book : books) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    @Override
    public List<Book> findAllByNameContainingAndAuthorContainingAndPriceBetween(String name, String author, Long minPrice, Long maxPrice) {
        return bookRepository.findAllByNameContainingAndAuthorContainingAndPriceBetween(name, author, minPrice, maxPrice);
    }
}
