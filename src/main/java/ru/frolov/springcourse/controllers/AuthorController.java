package ru.frolov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
