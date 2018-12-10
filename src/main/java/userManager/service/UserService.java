package userManager.service;

import userManager.domain.PageBean;
import userManager.domain.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUsers();

    boolean addUser(User user);

    User queryUserByid(int id);

    void updateUserById(User user);

    void deleteUserById(int id);

    PageBean getPageBean(int curPage, int pageSize);

    List<User> search(String name, String qq);
}
