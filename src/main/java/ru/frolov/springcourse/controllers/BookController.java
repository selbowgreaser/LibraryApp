package ru.frolov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.frolov.springcourse.dao.AuthorDAO;
import ru.frolov.springcourse.dao.BookDAO;
import ru.frolov.springcourse.model.Author;
import ru.frolov.springcourse.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;

    @Autowired
    public BookController(BookDAO bookDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorDAO.getAllAuthors());
            return "books/new";
        }

        bookDAO.createBookPage(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookPageById(id));
        return "books/book";
    }

    @GetMapping("/new")
    public String getFormForCreateBook(@ModelAttribute("book") Book book, @ModelAttribute("author") Author author, Model model) {
        model.addAttribute("authors", authorDAO.getAllAuthors());
        return "books/new";
    }
}
