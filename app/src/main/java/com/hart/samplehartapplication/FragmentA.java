package com.hart.samplehartapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hart.hartapplicationbase.HartBaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Alex on 6/23/16.
 * Proprietary (Hart)
 */
public class FragmentA extends HartBaseFragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        setActionBarTitle("Fragment A");

        ButterKnife.bind(this, v);

        enableNav();

        enableFab();
        setFabIcon(android.R.drawable.ic_dialog_alert);

        return v;
    }
}
