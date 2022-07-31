package dao;

import dao.connection.ConnectionBulder;
import entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class ReviewDao implements DAO<Review> {
    private ConnectionBulder connectionBulder;
    private static final String SELECT_ALL = "select id, user_id, book_id, textreviewbook, reviewdate from review";
    private static final String SELECT_ID = "select id, user_id, book_id, textreviewbook, reviewdate from review where id = ?";
    private static final String INSERT = "insert into review (user_id, book_id, textreviewbook, reviewdate) values(?, ?, ?, current_timestamp)";
    private static final String UPDATE = "update review set user_id = ?, book_id = ?, textreviewbook = ? where id = ?";
    private static final String DELETE = "delete from review where id = ?";

    @Override
    public Optional<Review> get(long id) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(Review.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .bookId(resultSet.getLong("book_id"))
                        .text(resultSet.getString("textreviewbook"))
                        .REVIEWDATE(resultSet.getDate("reviewdate"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Review> getAll() {
        final List<Review> result = new ArrayList<>();

        try (Statement stmt = connectionBulder.getConnection().createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
                while (resultSet.next()) {
                    result.add(
                            Review.builder()
                                    .id(resultSet.getLong("id"))
                                    .userId(resultSet.getLong("user_id"))
                                    .bookId(resultSet.getLong("book_id"))
                                    .text(resultSet.getString("textreviewbook"))
                                    .REVIEWDATE(resultSet.getDate("reviewdate"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long create(Review item) {
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(INSERT)) {
            int count = 1;
            preparedStatement.setLong(count++, item.getUserId());
            preparedStatement.setLong(count++, item.getBookId());
            preparedStatement.setString(count++, item.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Long update(Review item) {
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(UPDATE)) {
            int count = 1;
            preparedStatement.setLong(count++, item.getUserId());
            preparedStatement.setLong(count++, item.getBookId());
            preparedStatement.setString(count++, item.getText());
            preparedStatement.setLong(count++, item.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Review item) {
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

