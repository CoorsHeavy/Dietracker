package com.hart.hartapplicationbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hart.depflowbase.Events;

/**
 * Created by Alex on 6/17/16.
 * Proprietary (Hart)
 */
public class HartBaseFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected HartBaseNavAdapter getNavAdapter()
    {
        return ((HartBaseActivity) getActivity()).getNavAdapter();
    }

    protected void postDelayed(Runnable runnable, long delay)
    {
        ((HartBaseActivity) getActivity()).postDelayed(runnable, delay);
    }

    public void setFabIcon(int imageID)
    {
        ((HartBaseActivity) getActivity()).setFabIcon(imageID);
    }


    protected void disableNav()
    {
        ((HartBaseActivity) getActivity()).disableNavDrawer();
    }

    protected void fullDisableNav()
    {
        ((HartBaseActivity) getActivity()).fullDisable();
    }

    protected void enableNav()
    {
        ((HartBaseActivity) getActivity()).enableNavDrawer();
    }

    protected void disableFab()
    {
        ((HartBaseActivity) getActivity()).hideFab();
    }

    protected void enableFab()
    {
        ((HartBaseActivity) getActivity()).showFab();
    }

    protected void setActionBarTitle(String title)
    {
        ((HartBaseActivity) getActivity()).setToolbarTitle(title);
    }

    protected void setActionBackButton(boolean open)
    {
        ((HartBaseActivity) getActivity()).setActionBackButton(open);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Events.getBus().register(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        disableFab();
        Events.getBus().unregister(this);
    }
}
