package com.rand42.model;

import com.parse.ParseObject;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item
{
    private String name;
    private User owner;
    private String description;
    private Location location;
    private ParseObject parseObject;

    public Item(String name, User owner, String description, Location location)
    {
        this.name=name;
        this.owner=owner;
        this.description=description;
        this.location=location;

        parseObject = new ParseObject("Item");
        parseObject.put("name",name);
        parseObject.put("desc",description);
        parseObject.put("owner",(ParseObject)owner.getParseUser());


    }
    public Item(ParseObject target)
    {

    }

    public String getName()
    {return name;}
    public String getDescription()
    {return description;}
    public User getOwner()
    {return owner;}
    public Location getLocation()
    {return location;}
    public void saveInBackground()
    {
        parseObject.saveInBackground();
    }


}
