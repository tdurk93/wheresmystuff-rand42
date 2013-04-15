package com.rand42.tests;

import com.rand42.model.Item;
import com.rand42.presenters.ItemListFragmentPresenter;
import com.rand42.model.IModel;
import com.rand42.model.ItemFilter;
import com.rand42.model.User;
import java.util.Date;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author Tyler
 */
public class ItemsListFragmentPresenterTest {
    
//<<<<<<< .mine
    private IModel myModel = new ItemsListTestModel();
    private ItemListFragmentPresenter presenter = new ItemListFragmentPresenter(myModel,0);
    private static final boolean USER_IS_ADMIN = true, ITEM_IS_LOST = true;
    
    public ItemsListFragmentPresenterTest()
    {
            //populate model with users and set the current user
           /* myModel.addUser("admin@example.com", "testAdmin", "password", USER_IS_ADMIN);
            myModel.addUser("normalUser@example.com", "testUser", "password", !USER_IS_ADMIN);

            User normalUser = myModel.getUser("normalUser@example.com");
            myModel.setCurrentUser(normalUser);   */
            User adminUser = new User("Admin","admin@email.com","password",true,1, true);
            //populate model with items
            myModel.createItem("Batman figurine", "about 6\" tall", adminUser, new Date(), ITEM_IS_LOST, "collectibles");
            myModel.createItem("mobile home", "i lost it. it is white.", adminUser,  new Date(), ITEM_IS_LOST, "collectibles");
            myModel.createItem("My heart", "You stole it", adminUser, new Date(), ITEM_IS_LOST, "body parts");
    }
//=======
    ItemListFragmentPresenter lostPresenter = new ItemListFragmentPresenter(new LoginTestModel(),0);
    ItemListFragmentPresenter foundPresenter = new ItemListFragmentPresenter(new LoginTestModel(),1);
//>>>>>>> .r99
    
    @Test(timeout=200)
    public void shouldReturnTrue()
    {
        assertTrue(true);
    }
    
    @Test(timeout=200)
    public void presenterShouldBeNonNull()
    {
        assertTrue(lostPresenter != null);
    }
    
    //public void 
    
    @Test(timeout=200)
    public void testGetFilterItems()
    {
        ItemFilter filter = new ItemFilter();
        filter.enableCategoryFilter("body parts");
        myModel.setFilter(filter);
        List<Item> items = presenter.getFilterItems();
        assertTrue(items.size() == 1);
        assertTrue(items.get(0).getName().equals("My heart"));
        filter.enableCategoryFilter("collectibles");
        items = presenter.getFilterItems();
        assertTrue(items.size() == 2);
    }
    
    
//=======
    @Test(timeout=200)
    public void lostTest()
    {
        List<Item> items =  lostPresenter.getUserItems();
        assertTrue(items.get(0).isLost());
    }
    @Test(timeout=200)
    public void foundTest()
    {
        List<Item> items =  foundPresenter.getUserItems();
        assertFalse(items.get(0).isLost());
    }





//>>>>>>> .r99
}
