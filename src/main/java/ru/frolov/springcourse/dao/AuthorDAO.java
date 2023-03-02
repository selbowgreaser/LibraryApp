package ru.frolov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frolov.springcourse.model.Author;

import java.util.List;

@Component
public class AuthorDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM author", new BeanPropertyRowMapper<>(Author.class));
    }

    public void addAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author(full_name) VALUES (?)",
                author.getFullName());
    }

}
