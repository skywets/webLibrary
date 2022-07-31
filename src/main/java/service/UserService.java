package service;

import dao.EducationDao;
import dao.UserDao;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserService {
    private UserDao userDao;
    private EducationDao educationDao;


    public List<User> getAll() {
        return userDao.getAll();
    }
//    public User getItem(long id) {
//        User user = userDao.get(id).orElseThrow();
//        Optional<Education> education = educationDao.get(user.getEducationId());
//        System.out.println(user.toString(education));
//        return user;
//    }
    public User getItem(long id) {
        return userDao.get(id).orElseThrow();
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void editUser(long id, User user) {
        user.setId(id);
        userDao.update(user);
    }

    public void deleteBook(User user) {
        userDao.delete(user);
    }


}
