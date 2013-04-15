package com.rand42.tests;

import com.rand42.WheresMyStuffApplication;
import com.rand42.database.ItemsDataSource;
import com.rand42.database.UsersDataSource;
import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.ItemFilter;
import com.rand42.model.LocalModel;
import com.rand42.model.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class ItemsListTestModel implements IModel{
    
    IModel testLocalModel = LocalModel.getModel();

    public boolean logIn(String email, String password) {
        return testLocalModel.logIn(email, password);
    }

    public boolean addUser(String email, String name, String password, boolean isAdmin) {
        return testLocalModel.addUser(email, name, password, isAdmin);
    }

    public User getCurrentUser() {
        return testLocalModel.getCurrentUser();
    }


    public void setCurrentUser(User user) {
        testLocalModel.setCurrentUser(user);
    }

    public void createItem(String name, String description, User owner, Date date, boolean lost, String category) {
        testLocalModel.createItem(name, description, owner, date, lost, category);
    }

    public List<Item> getUserItems(User user) {
        return testLocalModel.getUserItems(user);
    }


    public boolean deleteItem(Item item) {
        return testLocalModel.deleteItem(item);
    }


    public User getUser(String email) {
        return testLocalModel.getUser(email);
    }

    public void setFilter(ItemFilter filter) {
        testLocalModel.setFilter(filter);
    }

    public ItemFilter getFilter() {
        return testLocalModel.getFilter();
    }

    public List<Item> searchItems(String query) {
        return testLocalModel.searchItems(query);
    }

    public void logOut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean checkUserAttempts(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Item getItemById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void resetAttempts(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUser(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void lockUser(User currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void unlockUser(User currentUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
