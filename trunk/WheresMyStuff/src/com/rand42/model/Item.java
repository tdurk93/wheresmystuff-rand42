package com.rand42.model;

import java.util.Date;

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
    private boolean lost;
    private String category;



    private Date date;

    /**
     * Item files are self explanatory.
     * 
     * @param name
     * @param description
     * @param owner
     * @param date
     * @param id
     * @param lost
     * @param category
     */
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

    /**
     * Accessor for category descriptor.
     * @return
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Mutator for category descriptor
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Accessor for item name
     * @return
     */
    public String getName()
    {
    	return name;
    }
    
    /**
     * Accessor for descriptor
     * @return
     */
    public String getDescription()
    {
    	return description;
    }
    
    /**
     * Accessor for the item's proprietor
     * @return
     */
    public User getOwner()
    {
    	return owner;
    }
    
    /**
     * Accessor for the item id
     * @return
     */
    public long getID()
    {
        return id;
    }
    
    /**
     * Is the item lost or found?
     * @return
     */
    public boolean isLost()
    {
        return lost;
    }
    
    /**
     * At what date was the item lost?
     * @return
     */
    public Date getDate()
    {
        return date;
    }
    
    /**
     * Set the date at which the item was lost
     * @param date
     */
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
