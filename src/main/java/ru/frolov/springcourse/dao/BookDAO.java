package ru.frolov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frolov.springcourse.dao.mapper.BookMapper;
import ru.frolov.springcourse.model.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                "SELECT b.id id, b.title title, a.id author_id, a.full_name author_full_name, b.book_year book_year, p.id person_id, p.full_name person_full_name, p.birth_year person_birth_year " +
                        "FROM book b JOIN author a on b.author_id = a.id LEFT JOIN person p on p.id = b.person_id", new BookMapper());
    }

    public Book getBookById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT b.id id, b.title title, a.id author_id, a.full_name author_full_name, b.book_year book_year, p.id person_id, p.full_name person_full_name, p.birth_year person_birth_year " +
                        "FROM book b JOIN author a on b.author_id = a.id LEFT JOIN person p on p.id = b.person_id WHERE b.id = ?",
                new BookMapper(),
                id);

    }

    public void addBook(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author_id, book_year) values (?, ?, ?)",
                book.getTitle(),
                book.getAuthorId(),
                book.getYear());
    }

    public void updateBook(Book updatedBook) {
        jdbcTemplate.update(
                "UPDATE book SET title = ?, author_id = ?, book_year = ? WHERE id = ?",
                updatedBook.getTitle(),
                updatedBook.getAuthorId(),
                updatedBook.getYear());
    }

    public void deleteBook(int id) {
        jdbcTemplate.update(
                "DELETE FROM person WHERE id = ?", id
        );
    }

    public void borrowBook(int personId, int id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id = ? WHERE id = ?",
                personId,
                id
        );
    }

    public void releaseBook(int id) {
        jdbcTemplate.update(
                "UPDATE book SET person_id = null WHERE id = ?",
                id
        );
    }
}
