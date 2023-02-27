package ru.frolov.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.frolov.springcourse.dao.PersonDAO;
import ru.frolov.springcourse.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDAO.getAllPeople());

        return "people/index";
    }

    @GetMapping("/new")
    public String getFormForCreatePersonPage(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String createPersonPage(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.createPersonPage(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPersonInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonPageById(id));
        model.addAttribute("personBooks", personDAO.getPersonBooks(id));
        return "/people/person";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePersonPage(id);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String getFormForEditPersonInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonPageById(id));
        return "people/edit";
    }

    @PatchMapping("/id")
    public String editPersonInfo(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.updatePersonInfo(person);
        return "redirect:/people";
    }


}
