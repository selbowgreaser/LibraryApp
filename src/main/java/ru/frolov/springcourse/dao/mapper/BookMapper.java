package ru.frolov.springcourse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.frolov.springcourse.model.Author;
import ru.frolov.springcourse.model.Book;
import ru.frolov.springcourse.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String AUTHOR_ID = "author_id";
    private static final String AUTHOR_FULL_NAME = "author_full_name";
    private static final String YEAR = "book_year";
    private static final String PERSON_ID = "person_id";
    private static final String PERSON_FULL_NAME = "person_full_name";
    private static final String PERSON_BIRTH_YEAR = "person_birth_year";

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        Person person = new Person();
        Author author = new Author();

        book.setId(resultSet.getInt(ID))
                .setTitle(resultSet.getString(TITLE))
                .setYear(resultSet.getInt(YEAR));

        author.setId(resultSet.getInt(AUTHOR_ID))
                .setFullName(resultSet.getString(AUTHOR_FULL_NAME));
        book.setAuthor(author);

        if (resultSet.getInt(6) == 0) {
            return book;
        }

        person.setId(resultSet.getInt(PERSON_ID))
                .setFullName(resultSet.getString(PERSON_FULL_NAME))
                .setBirthYear(resultSet.getInt(PERSON_BIRTH_YEAR));
        book.setPerson(person);

        return book;
    }
}
