package com.hart.hartapplicationbase;

/**
 * Created by Alex on 6/22/16.
 * Proprietary (Hart)
 */
public class NavBaseItem
{
    public String sID;

    public int iID;

    public int resourceID;

    public int viewType;

    public int iconID;

    public String itemTitle;

    public String itemInfo;

    public boolean showSimpleDivider;

    public NavBaseItem(int type)
    {
        viewType = type;
    }
}
