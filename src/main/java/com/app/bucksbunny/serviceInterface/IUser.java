package com.app.bucksbunny.serviceInterface;

import com.app.bucksbunny.entity.User;

public interface IUser {

    public User addUser(User user);
    public User addAdmin(User user);
    public void updatePassword(String userEmail, String newPassword);
    public User updateUserName(String userEmail, String userName);
    public String getUserName(String userEmail);

    public User getUserByEmail(String userEmail);

    public void deleteUser(String userEmail);

}
