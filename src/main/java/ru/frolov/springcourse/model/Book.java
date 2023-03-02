package ru.frolov.springcourse.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class Book {

    private int id;

    @NotNull
    private String title;

    private Author author;

    private int authorId;

    @Range(min = 1000, max = 2023)
    private int year;

    private Person person;

    private int personId;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public int getAuthorId() {
        return authorId;
    }

    public Book setAuthorId(int authorId) {
        this.authorId = authorId;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Book setYear(int year) {
        this.year = year;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Book setPerson(Person person) {
        this.person = person;
        return this;
    }

    public int getPersonId() {
        return personId;
    }

    public Book setPersonId(int personId) {
        this.personId = personId;
        return this;
    }
}
