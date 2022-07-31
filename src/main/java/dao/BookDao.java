package dao;

import dao.connection.ConnectionBulder;
import entity.Book;
import lombok.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDao implements DAO<Book> {
    private ConnectionBulder connectionBulder;
    private static final String SELECT_ALL = "select id, catalog_id, title, author, text_book," +
            " countofpages, publishingdate, popular, newrelease, genre from book";
    private static final String SELECT_ID = "select id, catalog_id, title, author, text_book, " +
            "countofpages, publishingdate, popular, newrelease, genre from book where id = ?";
    private static final String INSERT = "insert into book (catalog_id, title, author, text_book, " +
            "countofpages, publishingdate, popular, newrelease, genre) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "update book set catalog_id = ?, title = ?, author = ?, text_book = ?, " +
            "countofpages = ?, publishingdate = ?, popular = ?, newrelease = ?, genre = ? where id = ?";
    private static final String DELETE = "delete from book where id = ?";

    @Override
    public Optional<Book> get(long id) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(Book.builder()
                        .id(resultSet.getLong("id"))
                        .catalogId(resultSet.getLong("catalog_id"))
                        .title(resultSet.getString("title"))
                        .author(resultSet.getString("author"))
                        .textBook(resultSet.getString("text_book"))
                        .countOfPages(resultSet.getInt("countofpages"))
                                .publishingDate(resultSet.getDate("publishingdate"))
                       // .publishingDate(LocalDate.parse(resultSet.getString("publishingdate")))
                        .popular(resultSet.getBoolean("popular"))
                        .newRelease(resultSet.getBoolean("newrelease"))
                        .genre(resultSet.getString("genre"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        final List<Book> result = new ArrayList<>();
        try (Statement stmt = connectionBulder.getConnection().createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
                while (resultSet.next()) {
                    result.add(
                            Book.builder()
                                    .id(resultSet.getLong("id"))
                                    .catalogId(resultSet.getLong("catalog_id"))
                                    .title(resultSet.getString("title"))
                                    .author(resultSet.getString("author"))
                                    .textBook(resultSet.getString("text_book"))
                                    .countOfPages(resultSet.getInt("countofpages"))
                                    .publishingDate(resultSet.getDate("publishingdate"))
                                    //.publishingDate(LocalDate.parse(resultSet.getString("publishingdate")))
                                    .popular(resultSet.getBoolean("popular"))
                                    .newRelease(resultSet.getBoolean("newrelease"))
                                    .genre(resultSet.getString("genre"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long create(Book item) {
        int count = 1;
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(INSERT)) {
          preparedStatement.setLong(count++, item.getCatalogId());
            preparedStatement.setString(count++, item.getTitle());
            preparedStatement.setString(count++, item.getAuthor());
            preparedStatement.setString(count++, item.getTextBook());
            preparedStatement.setInt(count++, item.getCountOfPages());
            preparedStatement.setDate(count++, item.getPublishingDate());
            preparedStatement.setBoolean(count++, item.isPopular());
            preparedStatement.setBoolean(count++, item.isNewRelease());
            preparedStatement.setString(count++, item.getGenre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Long update(Book item) {
        long id=-1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(UPDATE)) {
            int count = 1;
            preparedStatement.setLong(count++, item.getCatalogId());
            preparedStatement.setString(count++, item.getTitle());
            preparedStatement.setString(count++, item.getAuthor());
            preparedStatement.setString(count++, item.getTextBook());
            preparedStatement.setInt(count++, item.getCountOfPages());
            preparedStatement.setDate(count++, item.getPublishingDate());
            preparedStatement.setBoolean(count++, item.isPopular());
            preparedStatement.setBoolean(count++, item.isNewRelease());
            preparedStatement.setString(count++, item.getGenre());
            preparedStatement.setLong(count++, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Book item) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(DELETE)) {
            preparedStatement.setLong(1, item.getId());
            if (preparedStatement.executeUpdate() == 0) {
                throw new IllegalStateException("Record with id = " + item.getId() + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
