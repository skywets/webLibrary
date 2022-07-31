package dao;

import dao.connection.ConnectionBulder;
import entity.Education;
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
public class EducationDao implements DAO<Education> {
    private ConnectionBulder connectionBulder;
    private static final String SELECT_ALL = "select id, hasElementrySchoolEducation, hasSecondrySchoolEducation, hasHigherEducation from education";
    private static final String SELECT_ID = "select id, hasElementrySchoolEducation, hasSecondrySchoolEducation, hasHigherEducation from education where id = ?";
    private static final String INSERT = "insert into education (id, haselementryschooleducation, hassecondryschooleducation, hashighereducation) values(default, ?, ?, ?) returning id";
    private static final String UPDATE = "update education set haselementryschooleducation = ?, hassecondryschooleducation = ?, hashighereducation = ? where id = ? returning id";
    private static final String DELETE = "delete from education where id = ?";

    @Override
    public Optional<Education> get(long id) {
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(SELECT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(Education.builder()
                        .id(resultSet.getLong("id"))
                        .hasElementrySchoolEducation(resultSet.getBoolean("haselementryschooleducation"))
                        .hasSecondrySchoolEducation(resultSet.getBoolean("hassecondryschooleducation"))
                        .hasHigherEducation(resultSet.getBoolean("hashighereducation"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Education> getAll() {
        final List<Education> result = new ArrayList<>();

        try (Statement stmt = connectionBulder.getConnection().createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
                while (resultSet.next()) {
                    result.add(
                            Education.builder()
                                    .id(resultSet.getLong("id"))
                                    .hasElementrySchoolEducation(resultSet.getBoolean("haselementryschooleducation"))
                                    .hasSecondrySchoolEducation(resultSet.getBoolean("hassecondryschooleducation"))
                                    .hasHigherEducation(resultSet.getBoolean("hashighereducation"))
                                    .build());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Long create(Education item) {
        long id = - 1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(INSERT)) {
            int count = 1;
            preparedStatement.setBoolean(count++, item.isHasElementrySchoolEducation());
            preparedStatement.setBoolean(count++, item.isHasSecondrySchoolEducation());
            preparedStatement.setBoolean(count++, item.isHasHigherEducation());
           // preparedStatement.executeUpdate();
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
    public Long update(Education item) {
        long id=-1;
        try (PreparedStatement preparedStatement = connectionBulder.getConnection().prepareStatement(UPDATE)) {
            int count = 1;
            preparedStatement.setBoolean(count++, item.isHasElementrySchoolEducation());
            preparedStatement.setBoolean(count++, item.isHasSecondrySchoolEducation());
            preparedStatement.setBoolean(count++, item.isHasHigherEducation());
            preparedStatement.setLong(count++, item.getId());
           // preparedStatement.executeUpdate();
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
    public void delete(Education item) {
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



