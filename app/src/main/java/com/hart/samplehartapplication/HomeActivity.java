package com.hart.samplehartapplication;

import android.os.Bundle;

import com.hart.fragmentnavigation.Navigation;
import com.hart.fragmentnavigation.NavigationEvent;
import com.hart.hartapplicationbase.HartBaseActivity;
import com.hart.hartapplicationbase.HartBaseNavAdapter;
import com.hart.hartapplicationbase.NavBaseItem;
import com.hart.hartapplicationbase.NavDrawerEvent;
import com.squareup.otto.Subscribe;


public class HomeActivity extends HartBaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Register the activity for fragment navigations
        // Note: do this on every new activity
        Navigation.navTo(HomeFragment.class);

        // set the default navigation drawer adapter
        // Note: the adapter must extend HartBaseNavAdapter
        setNavAdapter(new NavDrawerAdapter());


        setNavSchemeA();
    }

    private void setNavSchemeA()
    {
        HartBaseNavAdapter adapter = getNavAdapter();
        adapter.clear();

        NavBaseItem item = new NavBaseItem(NavDrawerAdapter.HEADER);
        item.resourceID = R.layout.navigation_header;
        item.itemTitle = "Sample Nav Header";

        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_camera;
        item.itemTitle = "Home Fragment";
        item.showSimpleDivider = true;
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_gallery;
        item.itemTitle = "Fragment A";
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_manage;
        item.itemTitle = "Fragment B";
        item.showSimpleDivider = true;
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_send;
        item.itemTitle = "Fragment C";
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_share;
        item.itemTitle = "Fragment D";
        adapter.add(item);

        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onNavDrawerItemSelected(NavDrawerEvent event)
    {
        closeNavDrawer();

        switch (event.item.itemTitle)
        {
            case "Home Fragment":
                Navigation.clearBackStack();
                Navigation.navTo(HomeFragment.class);
                break;
            case "Fragment A":
                Navigation.navTo(FragmentA.class);
                break;
            case "Fragment B":
                Navigation.navTo(FragmentB.class);
                break;
            case "Fragment C":
                Navigation.navTo(FragmentC.class);
            break;
            case "Fragment D":
                Navigation.navTo(FragmentD.class);
                break;
        }
    }


    // NOTE : otto dosn't allow subscribe on the base class
    // this is a workaround for now that must be included
    // to automate nav drawer behaviour
    @Subscribe
    public void navigationEvent(NavigationEvent event)
    {
        onNavigationEvent(event);
    }
}
