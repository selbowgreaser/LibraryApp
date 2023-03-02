package ru.frolov.springcourse.model;

import jakarta.validation.constraints.Pattern;

public class Author {

    private static final String FULL_NAME_PATTERN = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+";
    private static final String FULL_NAME_ERROR_MESSAGE = "Введите ФИО в корректном формате: Фамилия Имя Отчество";

    private int id;

    @Pattern(regexp = FULL_NAME_PATTERN, message = FULL_NAME_ERROR_MESSAGE)
    private String fullName;

    public Author() {
    }

    public int getId() {
        return id;
    }

    public Author setId(int id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Author setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
