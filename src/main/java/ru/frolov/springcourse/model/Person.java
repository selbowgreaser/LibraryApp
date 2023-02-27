package ru.frolov.springcourse.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

public class Person {
    private static final String FULL_NAME_PATTERN = "[А-Я][а-я]+ [А-Я][а-я]+ [А-Я][а-я]+";
    private static final String FULL_NAME_ERROR_MESSAGE = "Введите ФИО в корректном формате: Фамилия Имя Отчество";

    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2022;

    private int id;

    @Pattern(regexp = FULL_NAME_PATTERN, message = FULL_NAME_ERROR_MESSAGE)
    private String fullName;

    @NotNull
    @Range(min = MIN_YEAR, max = MAX_YEAR)
    private int birthYear;

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
