package com.hart.depflowbase;

import com.squareup.otto.Bus;

/**
 * Created by Alex on 6/22/16.
 * Proprietary (Hart)
 */
public class Events
{
    private static Bus bus;

    public static Bus getBus()
    {
        if (bus == null)
        {
            bus = new Bus();
        }
        return bus;
    }
}
