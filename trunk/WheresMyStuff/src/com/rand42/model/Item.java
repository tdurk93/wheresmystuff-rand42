package com.rand42.model;

import com.parse.*;


import java.util.Date;
import java.util.UUID;

/**
 * The item class is used to contain details about lost stuff
 * @author Rand-42
 */
public class Item
{
    private long id;
    private String name;
    private User owner;
    private String description;
    private Location location;
    private boolean lost;
    private String category;



    private Date date;

    public Item(String name, String description, User owner, Date date, long id, boolean lost, String category)
    {
        this.description = description;
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.lost = lost;
        this.date=date;
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getName()
    {return name;}
    public String getDescription()
    {return description;}
    public User getOwner()
    {return owner;}
    public Location getLocation()
    {return location;}
    public long getID()
    {
        return id;
    }
    public boolean isLost()
    {
        return lost;
    }
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * The name of the item
     * @return Name
     */
    public String toString()
    {
        return name;
    }

}
