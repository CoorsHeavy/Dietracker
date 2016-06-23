package com.hart.samplehartapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hart.depflowbase.Events;
import com.hart.hartapplicationbase.HartBaseNavAdapter;
import com.hart.hartapplicationbase.HartBaseNavViewHolder;
import com.hart.hartapplicationbase.NavBaseItem;
import com.hart.hartapplicationbase.NavDrawerEvent;

/**
 * Created by Alex on 6/22/16.
 * Proprietary (Hart)
 */
public class NavDrawerAdapter extends HartBaseNavAdapter
{
    public static final int HEADER = 0;
    public static final int ITEM = 1;

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getResIdFromType(viewType), parent, false);
        setItemListener(itemView, viewType);
        return new NavViewHolder(itemView, viewType);
    }

    private View.OnClickListener itemListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int pos = ((RecyclerView) v.getParent()).getChildLayoutPosition(v);
            Events.getBus().post(new NavDrawerEvent(items.get(pos)));
        }
    };

    private void setItemListener(View view, int type)
    {
        if(type == ITEM)
        {
            view.setOnClickListener(itemListener);
        }
    }

    private int getResIdFromType(int type)
    {
        switch (type)
        {
            case HEADER:
                return R.layout.navigation_header;
            case ITEM:
                return R.layout.basic_list_row;
            default:
                return 0;
        }
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class NavViewHolder extends HartBaseNavViewHolder
    {
        public LinearLayout headerLayout;
        public ImageView icon;
        public TextView title;
        public View simpleDivider;

        public NavViewHolder(View itemView, int viewType)
        {
            super(itemView, viewType);
            switch (viewType)
            {
                case NavDrawerAdapter.HEADER:
                    title = (TextView) itemView.findViewById(R.id.header_text);
                    break;
                case NavDrawerAdapter.ITEM:
                    simpleDivider = (View) itemView.findViewById(R.id.simple_divider);
                    icon = (ImageView) itemView.findViewById(R.id.rowIcon);
                    title = (TextView) itemView.findViewById(R.id.rowText);
                    break;

            }
        }

        @Override
        public void set(NavBaseItem item)
        {
            super.set(item);
            switch (item.viewType)
            {
                case NavDrawerAdapter.HEADER:
                    title.setText(item.itemTitle);
                    break;
                case NavDrawerAdapter.ITEM:
                    icon.setImageResource(item.iconID);
                    title.setText(item.itemTitle);
                    if (item.showSimpleDivider)
                    {
                        simpleDivider.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        simpleDivider.setVisibility(View.GONE);
                    }
                    break;

            }
        }
    }
}
