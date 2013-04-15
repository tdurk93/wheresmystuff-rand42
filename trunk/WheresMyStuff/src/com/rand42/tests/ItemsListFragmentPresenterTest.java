package com.rand42.tests;
import com.rand42.presenters.ItemListFragmentPresenter;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 *
 * @author Tyler
 */
public class ItemsListFragmentPresenterTest {
    
    ItemListFragmentPresenter presenter = new ItemListFragmentPresenter(new ItemsListTestModel(),0);
    
    @Test(timeout=200)
    public void shouldReturnTrue()
    {
        assertTrue(true);
    }
    
    @Test(timeout=200)
    public void presenterShouldBeNonNull()
    {
        assertTrue(presenter != null);
    }
    
    
}
