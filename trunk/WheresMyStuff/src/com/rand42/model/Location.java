package com.rand42.model;

/**
 * Stores coordinates
 * @author Rand-42
 */
public class Location
{
    private int lat;
    private int longt;

    /**
     * Create a new location
     * @param lat
     * @param longt
     */
    public Location(int lat, int longt)
    {
        this.lat=lat;
        this.longt=longt;
    }
    public int getLat()
    {return lat;}
    public int getLong()
    {return longt;}
}
