package ru.frolov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.frolov.springcourse.dao.AuthorDAO;
import ru.frolov.springcourse.dao.BookDAO;
import ru.frolov.springcourse.dao.PersonDAO;
import ru.frolov.springcourse.model.Author;
import ru.frolov.springcourse.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, AuthorDAO authorDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("people", personDAO.getAllPeople());
        return "books/book";
    }

    @GetMapping("/new")
    public String getFormForCreateBook(@ModelAttribute("book") Book book, @ModelAttribute("author") Author author, Model model) {
        model.addAttribute("authors", authorDAO.getAllAuthors());
        return "books/new";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorDAO.getAllAuthors());
            return "books/new";
        }

        bookDAO.addBook(book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getFormForUpdateBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("authors", authorDAO.getAllAuthors());

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.updateBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/borrow")
    public String borrowBook(@ModelAttribute("book") Book book, Model model, @PathVariable("id") int id) {

        bookDAO.borrowBook(book.getPersonId(), id);
        model.addAttribute("book", bookDAO.getBookById(id));
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id, Model model) {

        bookDAO.releaseBook(id);
        model.addAttribute("book", bookDAO.getBookById(id));
        return "redirect:/books/{id}";
    }
}

