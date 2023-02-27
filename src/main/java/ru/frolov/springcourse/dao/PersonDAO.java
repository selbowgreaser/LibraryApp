package ru.frolov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.frolov.springcourse.model.Book;
import ru.frolov.springcourse.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonPageById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE id = ?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id)
                .stream()
                .findAny()
                .orElse(null);

    }

    public void createPersonPage(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person(full_name, birth_year) values (?, ?)",
                person.getFullName(),
                person.getBirthYear());
    }

    public void updatePersonInfo(Person updatedPerson) {
        jdbcTemplate.update(
                "UPDATE person SET full_name = ?, birth_year = ? WHERE id = ?",
                updatedPerson.getFullName(),
                updatedPerson.getBirthYear(),
                updatedPerson.getId());
    }

    public void deletePersonPage(int id) {
        jdbcTemplate.update(
                "DELETE FROM person WHERE id = ?", id
        );
    }

    public List<Book> getPersonBooks(int id) {
        return jdbcTemplate.query(
                "SELECT b.id, b.title, a.full_name, b.year FROM person p JOIN book b ON p.id = b.person_id JOIN author a ON a.id = b.author_id WHERE p.id = ?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }
}
