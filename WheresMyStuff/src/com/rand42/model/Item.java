package com.rand42.model;

import com.parse.*;


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

    public Item(String name, String description, User owner,  long id, boolean lost)
    {
        this.description = description;
        this.owner = owner;
        this.name = name;
        this.id = id;
        this.lost = lost;
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

    /**
     * The name of the item
     * @return Name
     */
    public String toString()
    {
        return name;
    }

}
