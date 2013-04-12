package com.rand42.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/23/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateFilter implements IFilter<Item>
{
    private long filterTime;
    private boolean beforeTime;

    public DateFilter(long filterTime, boolean beforeTime)
    {
        this.filterTime = filterTime;
        this.beforeTime = beforeTime;
    }

    @Override
    public boolean filter(Item item)
    {
        if(beforeTime)
        {
            return item.getDate().getTime()<filterTime;
        }
        else
        {
            return item.getDate().getTime()>filterTime;
        }
    }
    
    /**
     * Accessor for the time of the filter
     * @return
     */
    public long getFilterTime()
    {
        return filterTime;
    }
    
    /**
     * Are we looking backwards or forwards
     * @return
     */
    public boolean getBefore()
    {
        return beforeTime;
    }
}
