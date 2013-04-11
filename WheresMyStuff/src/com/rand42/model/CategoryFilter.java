package com.rand42.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/23/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryFilter implements IFilter<Item>
{
    private String filterString;
    public CategoryFilter(String selectedItem)
    {
        filterString=selectedItem;
    }

    @Override
    public boolean filter(Item item)
    {
        return item.getCategory().equals(filterString);
    }
    public String getFilterString()
    {
        return filterString;
    }
}
