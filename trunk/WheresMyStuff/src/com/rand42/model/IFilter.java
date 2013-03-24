package com.rand42.model;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 3/23/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IFilter<T>
{
    public boolean filter(T item);
}
