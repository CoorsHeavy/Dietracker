package com.hart.hartapplicationbase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Alex on 6/21/16.
 * Proprietary (Hart)
 */
public class HartBaseNavViewHolder extends RecyclerView.ViewHolder
{
    public LinearLayout headerLayout;
    public ImageView icon;
    public TextView title;

    public HartBaseNavViewHolder(View itemView, int viewType)
    {
        super(itemView);
    }

    public void set(NavBaseItem item)
    {
    }
}
