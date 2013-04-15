package com.rand42.tests;

import com.rand42.WheresMyStuffApplication;
import com.rand42.database.ItemsDataSource;
import com.rand42.database.UsersDataSource;
import com.rand42.model.IModel;
import com.rand42.model.Item;
import com.rand42.model.ItemFilter;
import com.rand42.model.LocalModel;
import com.rand42.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class ItemsListTestModel implements IModel{

       List<Item> items = new ArrayList<>();
    ItemFilter filter = new ItemFilter();
    public boolean logIn(String email, String password) {
        return false;
    }

    public boolean addUser(String email, String name, String password, boolean isAdmin) {
        return false;
    }

    public User getCurrentUser() {
        return null ;
    }


    public void setCurrentUser(User user) {

    }

    public void createItem(String name, String description, User owner, Date date, boolean lost, String category)
    {
         items.add(new Item(name,description,owner,date,1,lost,category));
    }

    public List<Item> getUserItems(User user) {
        return items;
    }


    public boolean deleteItem(Item item) {
        items.remove(item);
        return true;
    }


    public User getUser(String email) {
        return null;
    }

    public void setFilter(ItemFilter filter) {
        this.filter=filter;
    }

    public ItemFilter getFilter() {
        return filter;
    }

    public List<Item> searchItems(String query) {
        return null;
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
