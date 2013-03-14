package com.rand42.model;

import java.util.Collection;

/**
 * An object that requests data. The querySuccess is called with request results
 */

public interface Requestor<T>
{
    public void querySuccess(Collection<T> items);
    public void queryFail(String message);
}
