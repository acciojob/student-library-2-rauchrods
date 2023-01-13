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
              Author author=  authorRepository.findById(book.getAuthor().getId()).get();
         book.setAuthor(author);

         List<Book> currlist = author.getBooksWritten();

         currlist.add(book);
         author.setBooksWritten(currlist);

         authorRepository.save(author);

    }


    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = null; //find the elements of the list by yourself

        if(genre==null && author!=null)
            books=bookRepository2.findBooksByAuthor(author,available);

        else if(genre!=null && author==null && available)
        {
            books=bookRepository2.findBooksByGenre(genre,available);
        }
        else if(genre!=null && author!=null && !available)
        {
            books=bookRepository2.findBooksByGenreAuthor(genre,author,available);
        }
        else if(genre!=null && author!=null && available)
        {
            books=bookRepository2.findBooksByGenreAuthor(genre,author,available);
        }
        else if(genre==null && author==null && available)
            books=bookRepository2.findByAvailability(available);

        return books;
    }
}
