package com.example.mdbspringboot.controller;



import com.example.mdbspringboot.dto.BookDTO;
import com.example.mdbspringboot.model.Book;
import com.example.mdbspringboot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repo;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    /**
     * Add a new book
     */
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        LOGGER.info("Adding book: {}", book);
        repo.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    /**
     * Get all books
     */
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return repo.findAll().stream()
                .map(book -> new BookDTO(book.getId(), book.getBookName(), book.getAuthorName()))
                .collect(Collectors.toList());
    }

    /**
     * Update an existing book by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = repo.findById(id);

        if (!existingBook.isPresent()) {

            LOGGER.warn("Book with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        Book book = existingBook.get();
        book.setBookName(updatedBook.getBookName());
        book.setAuthorName(updatedBook.getAuthorName());
        repo.save(book);

        LOGGER.info("Updated book with ID {}: {}", id, book);
        return ResponseEntity.ok("Book updated successfully");
    }

    /**
     * Delete a book by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        if (!repo.existsById(id)) {
            LOGGER.warn("Book with ID {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        repo.deleteById(id);
        LOGGER.info("Deleted book with ID {}", id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
