package com.hart.samplehartapplication.retrofit;

import android.os.Bundle;

import com.hart.depflowbase.Events;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RestObjectCallback<T> implements Callback<T> {
    private RestObjectEvent<T> event;

    public RestObjectCallback(RestObjectEvent<T> event) {
        this.event = event;
    }

    public static final <T> RestObjectCallback<T> create(Class<? extends RestObjectEvent<T>> cls) {
        return create((Class)cls, (Bundle)null);
    }

    public static final <T> RestObjectCallback<T> create(RestObjectEvent<T> event) {
        return create((RestObjectEvent)event, (Bundle)null);
    }

    public static final <T> RestObjectCallback<T> create(Class<? extends RestObjectEvent<T>> cls, Bundle data) {
        RestObjectCallback callback = null;

        try {
            callback = create((RestObjectEvent)cls.newInstance(), data);
        } catch (InstantiationException var4) {
            var4.printStackTrace();
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        }

        return callback;
    }

    public static final <T> RestObjectCallback<T> create(RestObjectEvent<T> event, Bundle data) {
        RestObjectCallback callback = null;
        event.setData(data);
        callback = new RestObjectCallback(event);
        return callback;
    }

    public void failure(RetrofitError error) {
        this.event.setError(error);
        if(error != null && error.getResponse() != null) {
            this.event.setStatus(error.getResponse().getStatus());
        }

        Events.getBus().post(this.event);
    }

    public void success(T result, Response response) {
        this.event.setResult(result);
        if(response != null) {
            this.event.setStatus(response.getStatus());
        }

        Events.getBus().post(this.event);
    }
}