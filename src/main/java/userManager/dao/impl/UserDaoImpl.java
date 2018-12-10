package userManager.dao.impl;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import userManager.dao.UserDao;
import userManager.domain.User;
import userManager.utils.JdbcTemplateUtil;

import java.util.List;

/*id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `qq` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `isdelete` int(10) unsigned zerofill NOT NULL,*/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getJdbcTemplate();

    @Override
    public List<User> searchQuery(String name, String qq) {
        String sql = "select * from t_user where name like '%" + name + "%' and qq like '%" + qq + "%'";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    //分页查询所有用户
    @Override
    public List<User> pageQueryUser(int startIndex, int pageSize) {
        String sql = "select * from t_user where isdelete=0 limit ?,?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), startIndex, pageSize);
        return userList;
    }

    //查询用户个数
    @Override
    public int queryCountUser() {
        String sql = "select count(*) from t_user where isdelete=0";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    //删除用户
    @Override
    public void deleteUser(int id) {
      /*  //物理删除
        String sql ="delete from t_user where id=?";
        jdbcTemplate.update(sql,id);*/
        //逻辑删除,并没有真正删除数据,而是在数据库放一个删除的标志
        String sql = "update t_user set isdelete=1 where id=?";
        jdbcTemplate.update(sql, id);
    }

    //修改更新用户信息
    @Override
    public int updateUser(User user) {
        String sql = "update t_user set name=?,sex=?,age=?,address=?,qq=?,email=? where id=?";
        int row = jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return row;
    }

    //根据id查询用户信息
    @Override
    public User queryUserByid(int id) {
        String sql = "select * from t_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);

        return user;
    }

    //添加用户
    @Override
    public void addUser(User user) {
        String sql = "insert into t_user values(null,?,?,?,?,?,?,0)";
        jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    //查询email是否存在
    @Override
    public List<User> queryEmail(String email) {
        String sql = "select * from t_user where email=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email);
        return users;
    }

    //查询所有用户
    @Override
    public List<User> queryAllusers() {
        String sql = "select * from t_user where isdelete=0";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }
}
