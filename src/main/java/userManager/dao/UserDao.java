package userManager.dao;


import userManager.domain.User;

import java.util.List;

public interface UserDao {
    List<User> queryAllusers();

    List<User> queryEmail(String email);

    void addUser(User user);

    User queryUserByid(int id);

    int updateUser(User user);

    void deleteUser(int id);

    List<User> pageQueryUser(int startIndex, int pageSize);

    int queryCountUser();

    List<User> searchQuery(String name, String qq);
}
