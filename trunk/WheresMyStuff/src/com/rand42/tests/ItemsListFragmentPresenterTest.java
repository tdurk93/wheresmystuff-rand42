package com.rand42.tests;

import com.rand42.model.Item;
import com.rand42.presenters.ItemListFragmentPresenter;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author Tyler
 */
public class ItemsListFragmentPresenterTest {
    
    ItemListFragmentPresenter lostPresenter = new ItemListFragmentPresenter(new LoginTestModel(),0);
    ItemListFragmentPresenter foundPresenter = new ItemListFragmentPresenter(new LoginTestModel(),1);
    
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





}
