package com.rand42.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/1/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Location
{
    private int lat;
    private int longt;
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
