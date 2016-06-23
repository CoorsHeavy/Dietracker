package com.hart.hartapplicationbase;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hart.depflowbase.Events;
import com.hart.fragmentnavigation.Navigation;
import com.hart.fragmentnavigation.NavigationEvent;
import com.squareup.otto.Bus;


/**
 * Created by Alex on 6/14/16.
 * Proprietary (Hart)
 */
public class HartBaseActivity extends AppCompatActivity
{
    //private Toolbar toolbar;

    private FloatingActionButton fab;

    DrawerLayout drawer;
    private RecyclerView navDrawerRecycler;

    private HartBaseNavAdapter adapter;

    private ActionBarDrawerToggle toggle;

    private Toolbar toolbar;

    private static Bus bus = new Bus();

    private boolean actionBackEnabled;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Navigation.registerActivity(this, R.id.conenet_main);
    }

    public void setNavAdapter(HartBaseNavAdapter navAdapter)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        setSupportActionBar(toolbar);

        toggle.syncState();

        navDrawerRecycler = (RecyclerView) findViewById(R.id.nav_recycler);
        if (navDrawerRecycler != null)
        {
            navDrawerRecycler.setHasFixedSize(true);
        }


        adapter = navAdapter;

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        navDrawerRecycler.setLayoutManager(mLayoutManager);
        navDrawerRecycler.setItemAnimator(new DefaultItemAnimator());
        navDrawerRecycler.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Events.getBus().post(new FabEvent());
            }
        });
    }

    public HartBaseNavAdapter getNavAdapter()
    {
        return adapter;
    }

    public void setFabIcon(int imageID)
    {
        fab.setImageResource(imageID);
    }


    public void enableNavDrawer()
    {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);

        if (Navigation.getBackStackCount() >= 1)
        {
            setActionBackButton(true);
        }

        toggle.syncState();
    }

    public void disableNavDrawer()
    {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
    }

    public void fullDisable()
    {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        disableNavDrawer();
    }

    public void closeNavDrawer()
    {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    // TODO : figure out why this isn't working correctly
    public void setActionBackButton(boolean homeUp)
    {
        actionBackEnabled = homeUp;
        if (getSupportActionBar() != null)
        {
            if (homeUp)
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                toggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            else
            {
                toggle.setDrawerIndicatorEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toggle.syncState();
            }
        }
    }

    // TODO : which one do we want to set?
    public void setToolbarTitle(String title)
    {
        ActionBar ab = getSupportActionBar();
        if (ab != null)
        {
            ab.setTitle(title);
        }

        //toolbar.setTitle(title);
    }

    public DrawerLayout getNavDrawer()
    {
        return drawer;
    }

    public void showFab()
    {
        fab.setVisibility(View.VISIBLE);
    }

    public void hideFab()
    {
        fab.setVisibility(View.GONE);
    }

    public FloatingActionButton getFab()
    {
        return fab;
    }

    public void setFabListener(View.OnClickListener listener)
    {
        fab.setOnClickListener(listener);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item)
    {
        if (actionBackEnabled)
        {
            onBackPressed();
            return actionBackEnabled;
        }
        return toggle.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (drawer != null)
        {
            if (drawer.isDrawerOpen(GravityCompat.START))
            {
                drawer.closeDrawer(GravityCompat.START);
            }
            else
            {
                super.onBackPressed();
            }
        }

        if (Navigation.getBackStackCount() == 1)
        {
            setActionBackButton(false);
        }
        else if (Navigation.getBackStackCount() == 0)
        {
            finish();
        }
    }

    public void onNavigationEvent(NavigationEvent event)
    {
        if (event.stackCount >= 1)
        {
            setActionBackButton(true);
        }
        else
        {
            setActionBackButton(false);
        }
    }

    public void postDelayed(Runnable runnable, long delay)
    {
        if (handler == null)
        {
            handler = new Handler();
        }
        handler.postDelayed(runnable, delay);
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
        Events.getBus().unregister(this);
    }
}
