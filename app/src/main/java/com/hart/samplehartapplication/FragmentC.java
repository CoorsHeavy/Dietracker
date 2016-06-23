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
public class FragmentC extends HartBaseFragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_c, container, false);

        setActionBarTitle("Fragment C");

        ButterKnife.bind(this, v);

        disableNav();
        disableFab();

        return v;
    }
}
