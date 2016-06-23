package com.hart.samplehartapplication.deprecated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hart.hartapplicationbase.HartBaseFragment;
import com.hart.samplehartapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alex on 6/16/16.
 * Proprietary (Hart)
 */


public class HomeFragment_old extends HartBaseFragment
{
    @Bind(R.id.button_disable_nav)
    public Button disableNavBtn;

    @Bind(R.id.button_enable_nav)
    public Button enableNavBtn;

    @Bind(R.id.button_disable_fab)
    public Button disableFabBtn;

    @Bind(R.id.button_enable_fab)
    public Button enableFabBtn;

    @Bind(R.id.button_set_title)
    public Button setActionBarTitleBtn;

    @Bind(R.id.button_set_back_arrow)
    public Button setBackArrow;

    @Bind(R.id.button_remove_back_arrow)
    public Button removeBackArrow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_home_old, container, false);

        ButterKnife.bind(this, v);



        return v;
    }


    @OnClick(R.id.button_disable_nav)
    public void onDisableNav(View view)
    {
        disableNav();
    }

    @OnClick(R.id.button_enable_nav)
    public void onEnableNav(View view)
    {
        enableNav();
    }

    @OnClick(R.id.button_disable_fab)
    public void onDisableFab(View view)
    {
        disableFab();
    }

    @OnClick(R.id.button_enable_fab)
    public void onEnableFab(View view)
    {
        enableFab();
    }

    @OnClick(R.id.button_set_title)
    public void onSetTilte(View view)
    {
        setActionBarTitle("Home Fragment");
    }

    @OnClick(R.id.button_set_back_arrow)
    public void onSetBack(View view)
    {
        setActionBackButton(true);
    }

    @OnClick(R.id.button_remove_back_arrow)
    public void onRemoveBack(View view)
    {
        setActionBackButton(false);
    }
}
