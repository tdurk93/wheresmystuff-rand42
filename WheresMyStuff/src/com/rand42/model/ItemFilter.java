package com.rand42.model;

/**
 * This class is used as a wrapper to filter items. It uses a modified version of the delegate pattern
 * @author rand42
 *
 */
public class ItemFilter implements IFilter<Item>
{
    private DateFilter dateFilter;
    private CategoryFilter categoryFilter;

    /**
     * Activates the filter for the specified date and direction.
     * @param time
     * @param beforeTime
     */
    public void enableDateFilter(long time, boolean beforeTime)
    {
        dateFilter = new DateFilter(time,beforeTime);
    }
    
    /**
     * Sets a filter category to the specified string.
     * @param selectedItem
     */
    public void enableCategoryFilter(String selectedItem)
    {
        categoryFilter = new CategoryFilter(selectedItem);
    }
    
    /**
     * Clears the set filter.
     *  //TODO: implement a NullObject pattern here.
     */
    public void disableDateFilter()
    {
        dateFilter = null;
    }
    
    /**
     * Clears the set filter.
     *  //TODO: implement a NullObject pattern here.
     *  
     */
    public void disableCategoryFilter()
    {
        categoryFilter = null;
    }
    
    /**
     * Is this a date filter?
     * @return
     */
    public boolean isDateFilter()
    {
        return dateFilter !=null;
    }
    
    /**
     * Is a category filter on?
     * @return
     */
    public boolean isCategoryFilter()
    {
        return categoryFilter!=null;
    }
    
    /**
     * Is a date filter on?
     * @return
     */
    public long getDateFilterTime()
    {
        if(dateFilter!=null)
            return dateFilter.getFilterTime();
        return -1;

    }
    
    /**
     * Are we looking backwards?
     * @return
     */
    public boolean getDateBefore()
    {
        if(dateFilter!=null)
            return dateFilter.getBefore();
        return false;
    }
    
    /**
     * What's the category of this filter?
     * @return
     */
    public String getCategoryFiterString()
    {
        if(categoryFilter!=null)
            return categoryFilter.getFilterString();
        return "";
    }

    @Override
    public boolean filter(Item item)
    {
        boolean isValid=true;
        if(dateFilter!=null)
        {
            isValid=dateFilter.filter(item);
        }
        if(categoryFilter!=null)
        {
            if(isValid)
                isValid = categoryFilter.filter(item);
        }
        return isValid;
    }
}
