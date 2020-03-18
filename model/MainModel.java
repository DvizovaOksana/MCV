package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> result = getAllUsers();
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(result);
    }

    @Override
    public void loadDeletedUsers() {
        List<User> result = userService.getAllDeletedUsers();
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(result);
    }

    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        User user = userService.deleteUser(id);
        List<User> result = getAllUsers();
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(result);
    }

    @Override
    public void changeUserData(String name, long id, int level){
        User user = userService.createOrUpdateUser(name, id, level);
        List<User> result = getAllUsers();
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(result);
    }

    private List<User> getAllUsers(){
        List<User> result = userService.getUsersBetweenLevels(1, 100);
        return userService.filterOnlyActiveUsers(result);
    }
}
