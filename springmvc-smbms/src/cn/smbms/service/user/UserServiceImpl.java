package cn.smbms.service.user;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    //登录判断
    @Override
    public User getLogin(String userCode, String userPassword) {
        return userMapper.getLogin(userCode, userPassword);
    }

    //用户列表展示
    @Override
    public List<User> getUserList(String userName,Integer userRole,Integer pageIndex,Integer pageSize) {
        return userMapper.getUserList(userName,userRole,(pageIndex-1)*pageSize,pageSize);
    }

    //根据用户名和角色查询用户总数
    @Override
    public int getUserCount(String userName, Integer userRole) {
        return userMapper.getUserCount(userName,userRole);
    }

    //增加
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    //查询用户编码是否存在
    @Override
    public int userCodeExist(String userCode) {
        return userMapper.userCodeExist(userCode);
    }

    //根据id查询用户
    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    //更新
    @Override
    public int upUserById(User user) {
        return userMapper.upUserById(user);
    }

    //根据id删除用户
    @Override
    public int delUserById(String id) {
        return userMapper.delUserById(id);
    }

    //修改密码
    @Override
    public int upPw(String newpw,Integer id) {
        return userMapper.upPw(newpw,id);
    }


}
