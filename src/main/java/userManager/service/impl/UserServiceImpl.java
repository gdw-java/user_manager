package userManager.service.impl;

import userManager.dao.UserDao;
import userManager.dao.impl.UserDaoImpl;
import userManager.domain.PageBean;
import userManager.domain.User;
import userManager.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public List<User> search(String name, String qq) {
     List<User> users= userDao.searchQuery(name,qq);
     return users;
    }

    //获取分页数据
    @Override
    public PageBean getPageBean(int curPage, int pageSize) {
        PageBean pageBean = new PageBean(); //创建分页对象
        int count=userDao.queryCountUser();  //获取数据库总记录数
        pageBean.setCount(count);    //设置总个数
        pageBean.setTotalPage((int)Math.ceil(count*1.0/pageSize));  //计算并设置总页数
        pageBean.setCurPage(curPage); //当前页
        pageBean.setPageSize(pageSize); //每页显示的数据条数
        int startIndex = pageBean.getStartIndex();   //limit查询起始index
        List<User> users = userDao.pageQueryUser(startIndex, pageSize);  //每页查到的数据
        pageBean.setUserData(users);
        return pageBean;
    }

    //根据id修改用户用户信息
    @Override
    public void updateUserById(User user) {
        int row=userDao.updateUser(user);
    }
    //根据id删除用户信息
    @Override
    public void deleteUserById(int id) {
        userDao.deleteUser(id);
    }


    //根据id查询用户
    @Override
    public User queryUserByid(int id) {
       return userDao.queryUserByid(id);
    }


    //添加用户
    @Override
    public boolean addUser(User user) {
        List<User> users=userDao.queryEmail(user.getEmail());
        if (users.size() != 0) {
            return true; //email 存在
        } else {
            userDao.addUser(user);
            return false;
        }
    }

    //查询所有用户
    @Override
    public List<User> queryAllUsers() {
        List<User> users= userDao.queryAllusers();
        return users;

    }
}
