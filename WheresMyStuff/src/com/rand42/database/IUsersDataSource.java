package com.rand42.database;

import android.database.sqlite.SQLiteException;
import com.rand42.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 4/3/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUsersDataSource
{
    void open() throws SQLiteException;

    void close();

    boolean createUser(String name, String email, String password, boolean isAdmin);

    User getUserById(long id);

    User getUserByEmail(String email);

    User loginUser(String email, String password);

    boolean deleteUser(long id);

    List<User> getAllUsers();

    void lockUser(long id);

    void unlockUser(long id);
}
