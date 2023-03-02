package ru.frolov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frolov.springcourse.dao.mapper.BookMapper;
import ru.frolov.springcourse.model.Book;
import ru.frolov.springcourse.model.Person;

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
                "SELECT b.id id, b.title title, a.id author_id, a.full_name author_full_name, b.year book_year, p.id person_id, p.full_name person_full_name, p.birth_year person_birth_year " +
                "FROM book b JOIN author a on b.author_id = a.id LEFT JOIN person p on p.id = b.person_id", new BookMapper());
    }

    public Person getBookPageById(int id) {
        return jdbcTemplate.queryForObject(
                        "SELECT * FROM book WHERE id = ?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id);

    }

    public void createBookPage(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author_id, year) values (?, ?, ?)",
                book.getTitle(),
                book.getAuthorId(),
                book.getYear());
    }
//
//    public void createPersonPage(Person person) {
//        jdbcTemplate.update(
//                "INSERT INTO person(full_name, birth_year) values (?, ?)",
//                person.getFullName(),
//                person.getBirthYear());
//    }
//
//    public void updatePersonInfo(Person updatedPerson) {
//        jdbcTemplate.update(
//                "UPDATE person SET full_name = ?, birth_year = ? WHERE id = ?",
//                updatedPerson.getFullName(),
//                updatedPerson.getBirthYear(),
//                updatedPerson.getId());
//    }
//
//    public void deletePersonPage(int id) {
//        jdbcTemplate.update(
//                "DELETE FROM person WHERE id = ?", id
//        );
//    }
//
//    public List<Book> getPersonBooks(int id) {
//        return jdbcTemplate.query(
//                "SELECT b.id, b.title, a.full_name, b.year FROM person p JOIN book b ON p.id = b.person_id JOIN author a ON a.id = b.author_id WHERE p.id = ?",
//                new BeanPropertyRowMapper<>(Book.class),
//                id);
//    }
}
