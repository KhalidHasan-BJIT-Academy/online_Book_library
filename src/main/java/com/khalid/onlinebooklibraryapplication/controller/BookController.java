package com.khalid.onlinebooklibraryapplication.controller;


import com.khalid.onlinebooklibraryapplication.dto.*;
import com.khalid.onlinebooklibraryapplication.entity.BookEntity;
import com.khalid.onlinebooklibraryapplication.service.implementation.BookBorrowingServiceImplementation;
import com.khalid.onlinebooklibraryapplication.service.implementation.BookReserveServiceImplementation;
import com.khalid.onlinebooklibraryapplication.service.implementation.BookReviewServiceImplementation;
import com.khalid.onlinebooklibraryapplication.service.implementation.BookServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImplementation bookServiceImplementation;

    @Autowired
    private BookBorrowingServiceImplementation bookBorrowingServiceImplementation;

    @Autowired
    private BookReviewServiceImplementation bookReviewServiceImplementation;

    @Autowired
    private BookReserveServiceImplementation bookReserveServiceImplementation;


    @PostMapping ("/books/create")
    public ResponseEntity<?> createBook(@RequestBody BookDto bookDto) {
        try {
            BookDto newBook = bookServiceImplementation.createBook(bookDto);
            return new  ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/books/update")
    public ResponseEntity<?> updateBook(@RequestBody BookDto bookDto) {
        try {
            BookDto updatedBook = bookServiceImplementation.updateBook(bookDto);
            return new  ResponseEntity<>(updatedBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/books/delete")
    public ResponseEntity<?> deleteBook(@RequestBody BookDto bookDto) {
        try {
            bookServiceImplementation.deleteBook(bookDto);
            return new  ResponseEntity<>("Book Deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books/all")
    public ResponseEntity<?> allBooks() {
        try {
            List <BookDto> allBook = bookServiceImplementation.getAllBook();
            return new  ResponseEntity<>(allBook, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/books/{bookId}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId) {
        try {
            BookBorrowingDto book = bookBorrowingServiceImplementation.bookBorrowing(bookId);
            return new  ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        try {
            BookBorrowingDto book = bookBorrowingServiceImplementation.bookReturning(bookId);
            return new  ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/users/{userId}/books")
    public ResponseEntity<?> retriveBooks(@PathVariable Long userId) {
        try {
            List<BookEntity> allBookByUser = bookBorrowingServiceImplementation.getAllBookByUser(userId);
            return new ResponseEntity<>(allBookByUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}/borrowed-books")
    public ResponseEntity<?> retriveBorrowedBooks(@PathVariable Long userId) {
        try {
            List<BookEntity> allBookByUser = bookBorrowingServiceImplementation.getAllBorrowedBookByUser(userId);
            return new ResponseEntity<>(allBookByUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/users/{userId}/history")
    public ResponseEntity<?> userHistory(@PathVariable Long userId) {
        try {
            List<BookBorrowingInfoDto> userAllHistory = bookBorrowingServiceImplementation.getUserAllHistory(userId);
            return new ResponseEntity<>(userAllHistory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/books/{bookId}/reviews/create")
    public ResponseEntity<?> createReview(@PathVariable Long bookId, @RequestBody BookReviewDto bookReviewDto) {
        try {
            BookReviewDto newReview =  bookReviewServiceImplementation.createBookReview(bookId, bookReviewDto);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<?> allReview(@PathVariable Long bookId) {
        try {
            List <BookReviewDto> newReview =  bookReviewServiceImplementation.allBookReview(bookId);
            return new ResponseEntity<>(newReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/books/{bookId}/reviews/{reviewId}/delete")
    public ResponseEntity<?> deleteReview (@PathVariable Long bookId, @PathVariable Long reviewId) {
        try {
            bookReviewServiceImplementation.deleteReview(bookId, reviewId);
            return new  ResponseEntity<>("Review Deleted!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/books/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<?> updateReview (@PathVariable Long bookId, @PathVariable Long reviewId, @RequestBody BookReviewDto bookReviewDto) {
        try {
            BookReviewDto updatedReview =  bookReviewServiceImplementation.updateReview(bookId, reviewId, bookReviewDto);
            return new  ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/books/{bookId}/reserve")
    public ResponseEntity<?> reserveBook (@PathVariable Long bookId) {
        try {
            BookReserveDto updatedReview =  bookReserveServiceImplementation.reserveBook(bookId);
            return new  ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/books/{bookId}/cancel-reservation")
    public ResponseEntity<?> cancelReserveBook (@PathVariable Long bookId) {
        try {
            BookReserveDto cancelReview =  bookReserveServiceImplementation.cancelReserveBook(bookId);
            return new  ResponseEntity<>(cancelReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }






}
