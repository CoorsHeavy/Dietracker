package com.hart.samplehartapplication.retrofit;

import android.util.Log;

import com.hart.depflowbase.Events;
import com.squareup.otto.Bus;
import retrofit.RetrofitError;
/**
 * Created by jhummel on 10/5/15.
 */
@Deprecated
public class RestErrorHandler implements retrofit.ErrorHandler {
    private Bus eventBus;
    public RestErrorHandler() {
        if (eventBus == null)
            eventBus = Events.getBus();
    }
    @Override
    public Throwable handleError(final RetrofitError cause) {
        Log.e("Retrofit", cause.getMessage());
        String text = "Network error";
        String actionText = "Dismiss";

        return cause;
    }
}