package dao;

import dao.connection.ConnectionBulder;
import entity.Catalog;
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
public class CatalogDao implements DAO<Catalog> {
    private ConnectionBulder connectionBulder;
    private static final String SELECT_ALL = "select id, catalog_name from catalog";
    private static final String SELECT_ID = "select id, catalog_name from catalog where id = ?";
    private static final String INSERT = "insert into catalog (id, catalog_name) values(default, ?) returning id";
    private static final String UPDATE = "update catalog set catalog_name = ? where id = ? returning id";
    private static final String DELETE = "delete from catalog where id = ?";

    @Override
    public Optional<Catalog> get(long id) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(Catalog.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("catalog_name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Catalog> getAll() {
        final List<Catalog> result = new ArrayList<>();

        try (Statement stmt = connectionBulder.getConnection().createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
                while (resultSet.next()) {
                    result.add(
                            Catalog.builder()
                                    .id(resultSet.getLong("id"))
                                    .name(resultSet.getString("catalog_name"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long create(Catalog item) {
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection()
                .prepareStatement(INSERT)) {
            int count = 1;
            //ПРОБЛЕМЫ
            preparedStatement.setString(count++, item.getName());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Long update(Catalog item) {
        long id = -1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(UPDATE)) {
            int count = 1;
            preparedStatement.setString(count++, item.getName());
            preparedStatement.setLong(count++, item.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public void delete(Catalog item) {
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
