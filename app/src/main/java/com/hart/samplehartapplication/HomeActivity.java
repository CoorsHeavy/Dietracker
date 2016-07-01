package com.hart.samplehartapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.hart.depflowbase.Events;
import com.hart.fragmentnavigation.Navigation;
import com.hart.fragmentnavigation.NavigationEvent;
import com.hart.hartapplicationbase.HartBaseActivity;
import com.hart.hartapplicationbase.HartBaseNavAdapter;
import com.hart.hartapplicationbase.NavBaseItem;
import com.hart.hartapplicationbase.NavDrawerEvent;
import com.hart.samplehartapplication.adapters.NavDrawerAdapter;
import com.hart.samplehartapplication.fragments.FragmentChat;
import com.hart.samplehartapplication.fragments.FragmentCheck;
import com.hart.samplehartapplication.fragments.FragmentHistory;
import com.soundcloud.android.crop.Crop;
import com.squareup.otto.Subscribe;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;


public class HomeActivity extends HartBaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Register the activity for fragment navigations
        // Note: do this on every new activity
        Navigation.navTo(FragmentCheck.class);

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
        item.itemTitle = "Are You Eatin Tho";

        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_gallery;
        item.itemTitle = "Todays Progress";
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_manage;
        item.itemTitle = "History";
        adapter.add(item);

        item = new NavBaseItem(NavDrawerAdapter.ITEM);
        item.iconID = R.drawable.ic_menu_send;
        item.itemTitle = "Chat with your doctor";
        adapter.add(item);

        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onNavDrawerItemSelected(NavDrawerEvent event)
    {
        closeNavDrawer();

        switch (event.item.itemTitle)
        {
            case "Todays Progress":
                Navigation.navTo(FragmentCheck.class);
                break;
            case "History":
                Navigation.navTo(FragmentHistory.class);
                break;
            case "Chat with your doctor":
                Navigation.navTo(FragmentChat.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 541 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri imageUri = data.getData();

            try {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 542 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Log.d("Hudson", "Log");
            Uri imageUri = data.getData();

            try {
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    Events.getBus().post(MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
