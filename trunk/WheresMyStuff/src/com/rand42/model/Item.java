package com.rand42.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


import java.util.UUID;

/**
 * The item class is used to contain details about lost stuff
 * @author Rand-42
 */
public class Item
{
    private String uid;
    private String name;
    private User owner;
    private String description;
    private Location location;
    private boolean lost;
    private ParseObject parseObject;

    /**
     * Create a new Item from scratch
     * @param name
     * @param owner
     * @param description
     * @param location
     */
    public Item(String name, User owner, String description, Location location, boolean lost)
    {
        this.name=name;
        this.owner=owner;
        this.description=description;
        this.location=location;
        this.lost=lost;
        uid = UUID.randomUUID().toString();


        parseObject = new ParseObject("Item");
        parseObject.put("name",name);
        parseObject.put("desc",description);
        parseObject.put("owner",(ParseObject)owner.getParseUser());
        parseObject.put("uid", uid);
        parseObject.put("lost",lost);


    }

    /**
     * Create a new item from an existing database object.
     * @param target
     */
    public Item(ParseObject target)
    {
        target.fetchIfNeededInBackground(new GetCallback()  //target is often just a skeleton with no data. must load here
        {
            @Override
            public void done(ParseObject parseObject, ParseException e)
            {
                name=parseObject.getString("name");
                description=parseObject.getString("desc");
                owner = new User((ParseUser)parseObject.getParseObject("owner"));
                uid = parseObject.getString("uid");
                lost = parseObject.getBoolean("lost");

            }
        });
    }

    public String getName()
    {return name;}
    public String getDescription()
    {return description;}
    public User getOwner()
    {return owner;}
    public Location getLocation()
    {return location;}
    public String getUID()
    {
        return uid;
    }

    /**
     * Saves the item to the parse database
     */
    public void saveInBackground()
    {
        parseObject.saveInBackground();
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
