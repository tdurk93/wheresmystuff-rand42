package com.rand42.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.rand42.model.DateFilter;
import com.rand42.model.Item;

public class DateFilterTest {


    @Test(timeout=200)
    public void beforeTest(){
    	Item beforeItem = new Item("before", "this is a test", null, new Date(1l ), 1, false, null   );
    	DateFilter beforeFilter = new DateFilter( 2l, true );
    	
    	assertTrue(beforeFilter.filter(beforeItem));
    }
    
    @Test(timeout=200)
    public void afterTest(){

    	Item after = new Item("after", "this is a test", null, new Date(2l ), 1, false, null   );
    	DateFilter afterFilter = new DateFilter( 1l, false );
  
    	assertTrue(afterFilter.filter(after));  	
    	
    }
    
    @Test(timeout=200)
    public void negativeTest(){

    	Item beforeItem = new Item("before", "this is a test", null, new Date(1l ), 1, false, null   );
    	DateFilter beforeFilter = new DateFilter( 2l, false );
    	
    	assertTrue(!beforeFilter.filter(beforeItem));

    }

    @Test(timeout=200)
    public void equalityTest(){

    	Item beforeItem = new Item("equal", "this is a test", null, new Date(1l ), 1, false, null   );
    	DateFilter beforeFilter = new DateFilter( 1l, true );
    	
    	assertTrue(!beforeFilter.filter(beforeItem));

    }

    
}
