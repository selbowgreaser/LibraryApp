package ru.frolov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frolov.springcourse.model.Author;
import ru.frolov.springcourse.model.Book;

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

    public Author getAuthorById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM author WHERE id = ?",
                new BeanPropertyRowMapper<>(Author.class),
                id);
    }

    public void addAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author(full_name) VALUES (?)",
                author.getFullName());
    }

    public void updateAuthor(Author updatedAuthor) {
        jdbcTemplate.update("UPDATE author SET full_name = ? WHERE id = ?",
                updatedAuthor.getFullName(),
                updatedAuthor.getId());
    }

    public void deleteAuthor(int id) {
        jdbcTemplate.update(
                "DELETE FROM author WHERE id = ?", id
        );
    }

    public List<Book> getAuthorBooks(int id) {
        return jdbcTemplate.query(
                "SELECT b.id, b.title, a.full_name, b.book_year FROM book b JOIN author a ON a.id = b.author_id WHERE a.id = ?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }
}
