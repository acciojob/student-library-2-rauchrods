package com.driver.services;

import com.driver.models.Book;
import com.driver.models.Author;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository;

    public void createBook(Book book){

        int authorId = book.getAuthor().getId();

        Author author =  authorRepository.findById(authorId).get();

        //Update the bookList written by Author
        author.getBooksWritten().add(book);

        //Updated the book
        book.setAuthor(author);
        //bookRepository2.save(book);
        bookRepository2.save(book);

        authorRepository.save(author);

    }


    public List<Book> getBooks(String genre, boolean available, String author){
         //find the elements of the list by yourself

        if(genre != null && author != null){
            return bookRepository2.findBooksByGenreAuthor(genre, author, available);
        }else if(genre != null){
            return bookRepository2.findBooksByGenre(genre, available);
        }else if(author != null){
            return bookRepository2.findBooksByAuthor(author, available);
        }else{
            return bookRepository2.findByAvailability(available);
        }
    }
}
