package com.hart.hartapplicationbase;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Alex on 6/21/16.
 * Proprietary (Hart)
 */
public class HartBaseNavAdapter extends RecyclerView.Adapter<HartBaseNavViewHolder>
{
    public static final String TAG = HartBaseNavAdapter.class.getSimpleName();


    public ArrayList<NavBaseItem> items;

    public HartBaseNavAdapter()
    {
        items = new ArrayList<>();
    }

    @Override
    public HartBaseNavViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return null;
    }

    public void clear()
    {
        items.clear();
    }

    public void add(NavBaseItem item)
    {
        items.add(item);
    }


    @Override
    public int getItemViewType(int position)
    {
        return items.get(position).viewType;
    }


    @Override
    public void onBindViewHolder(HartBaseNavViewHolder holder, int position)
    {
        holder.set(items.get(position));
    }

    @Override
    public int getItemCount()
    {
        return 0;
    }
}
