package com.hart.samplehartapplication.lib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.hart.depflowbase.Events;
import com.hart.hartapplicationbase.HartBaseActivity;

import timber.log.Timber;

/**
 * Created by Alex on 10/27/15.
 * Proprietary (Hart)
 */
public class PermissionUtil
{
    public static final String TAG = "PermissionUtil";
    public static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 10;

    public static String[] getHartPermissions()
    {
        return new String[]{
                "android.permission.CALL_PHONE",
                "android.permission.INTERNET",
                "android.permission.READ_EXTERNAL_STORAGE",
                "com.google.android.c2dm.permission.RECEIVE",
                "com.google.android.c2dm.permission.SEND",
                "android.permission.WAKE_LOCK",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.USE_CREDENTIALS",
                "android.permission.GET_ACCOUNTS",
                "android.permission.READ_PHONE_STATE",
                "android.permission.ACCESS_FINE_LOCATION"
        };
    }

    /**
     * shows an explanation of why this permission is required
     *
     * @param context
     * @param permission
     */
    public static void showPermissionRationale(final Context context, final String permission)
    {
        new AlertDialog.Builder(context)
                .setTitle("Permission Required")
                .setMessage(getPermissionRationale(permission))
                .setPositiveButton("ok", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                requestPermission(context, permission);
                            }
                        }

                ).setIcon(android.R.drawable.ic_menu_camera).show();
    }


    /**
     * get the appropriate permission rationale, this will be further implemented when more runtime permissions are added.
     *
     * @param permission
     * @return
     */

    private static String getPermissionRationale(String permission)
    {
        String rationale = "A permission is required to continue!";
        switch (permission)
        {
            case "android.permission.GET_ACCOUNTS":
                rationale = "Hart uses a subset of the Accounts permission to detect your google accounts. Hart does not use this to access your contacts in any way.";
                break;
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                rationale = "Hart uses the external storage permission to access image from your camera and gallery";
                break;
            case "android.permission.READ_PHONE_STATE":
                rationale = "Hart needs this permission to communicate with your device!";
                break;
            case "android.permission.ACCESS_FINE_LOCATION":
                rationale = "Hart would like to use your current location to help you find the nearest pharmacy.";
        }
        return rationale;
    }

    /**
     * decide weather a user should be prompted for a permission or presented with a permission rationale if they have previously declined the permission in question
     *
     * @param context
     * @param permission
     */
    public static void handlePermission(Context context, String permission)
    {
        if (!hasPermission(context, permission))
        {
            Timber.i("SHOULD SHOW : %s", shouldShowExplanation(context, permission));
            if (shouldShowExplanation(context, permission))
            {
                showPermissionRationale(context, permission);
            }
            else
            {
                requestPermission(context, permission);
            }
        }
    }

    /**
     * returns the permission index
     *
     * @param permission
     * @return
     */
    public static int getPermissionConstant(String permission)
    {
        String[] hartPermissions = getHartPermissions();

        for (int i = 0; i < hartPermissions.length; i++)
        {
            if (permission.equals(hartPermissions[i]))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * promps the user for the required runtime permission
     *
     * @param context
     * @param permission
     */
    public static void requestPermission(Context context, String permission)
    {

        ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, getPermissionConstant(permission));
    }

    /**
     * return true if the user has previously declined this permission. used to decide if a rationale should be shown
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean shouldShowExplanation(Context context, String permission)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
    }

    /**
     * checks weather the applicable runtime permission has been granted
     *
     * @param context
     * @param permission
     * @return true if the permission has been granted
     */
    public static boolean hasPermission(Context context, String permission)
    {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        return true;
    }

    /**
     * posts the permission request event to any subscribed listeners.
     *
     * @param context
     * @param requestCode
     * @param permission
     * @param grantResult
     */
    public static void handleResult(Context context, int requestCode, String permission, int grantResult)
    {

        String clicked = (grantResult == PackageManager.PERMISSION_GRANTED) ? "allow" : "deny";

        PermissionEvent event = new PermissionEvent(permission);
        event.setValue(grantResult == PackageManager.PERMISSION_GRANTED);

        Events.getBus().post(event);
    }
}