package ru.frolov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.frolov.springcourse.dao.AuthorDAO;
import ru.frolov.springcourse.model.Author;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorController(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorDAO.getAllAuthors());

        return "authors/index";
    }

    @GetMapping("/new")
    public String getFormForAddAuthor(@ModelAttribute("author") Author author) {
        return "authors/new";
    }

    @PostMapping
    public String addNewAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/new";
        }

        authorDAO.addAuthor(author);

        return "redirect:/authors";
    }

    @GetMapping("/{id}")
    public String getAuthor(@PathVariable("id") int id, Model model) {
        model.addAttribute("author", authorDAO.getAuthorById(id));
        model.addAttribute("authorBooks", authorDAO.getAuthorBooks(id));
        return "authors/author";
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") int id) {
        authorDAO.deleteAuthor(id);

        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String getFormForEditAuthor(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", authorDAO.getAuthorById(id));
        return "authors/edit";
    }

    @PatchMapping("/{id}")
    public String updateAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "authors/edit";
        }

        authorDAO.updateAuthor(author);
        return "redirect:/authors";
    }
}
