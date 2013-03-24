package com.rand42.model;

/**
 * This class is used as a wrapper to filter items. It uses a modified version of the delegate pattern
 * @author rand42
 *
 */
public class ItemFilter implements IFilter<Item>
{
    DateFilter dateFilter;
    CategoryFilter categoryFilter;

    public void enableDateFilter(long time, boolean beforeTime)
    {
        dateFilter = new DateFilter(time,beforeTime);
    }
    public void enableCategoryFilter()
    {
        categoryFilter = new CategoryFilter();
    }
    public void disableDateFilter()
    {
        dateFilter = null;
    }
    public void disableCategoryFilter()
    {
        categoryFilter = null;
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
