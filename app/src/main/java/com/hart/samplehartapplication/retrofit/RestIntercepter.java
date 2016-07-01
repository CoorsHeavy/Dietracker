package com.hart.samplehartapplication.retrofit;
import android.util.Log;

import com.hart.depflowbase.Events;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.otto.Bus;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
/**
 * Created by jhummel on 10/6/15.
 */
/**
 * An interceptor for the OkHttp client
 * Catches UnknownHostExceptions (commonly caused by lack of internet connectivity),
 * and presents them to the user as a snackbar, with the option to retry the request
 */
public class RestIntercepter implements Interceptor{
    private static final int MAX_RETRIES = 5;
    private static Bus eventBus;
    private final static long SLEEP_LENGTH_IN_MILLIS = 100;
    private static String FRIENDLY_MESSAGE = "Oops, something went wrong.";
    private static String INTERNAL_ERROR =  "Oops, an internal server error has occured";
    private static String DISMISS = "Dismiss";
    /**
     * Default constructor, sets up the eventbus
     */
    public RestIntercepter() {
        if (eventBus == null)
            eventBus = Events.getBus();
    }
    /**
     * Intercept outgoing requests, catch failures
     * @param chain the OkHttp request chain
     * @return the response to the request
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        try {
            // try the request
            response = chain.proceed(request);
            // response was not successful
            if (!response.isSuccessful()) {
                Log.d("intercept", "Request is not successful, code: " + response.code());
                String message = response.body().string();
                eventBus.post(new RestIntercepterErrorEvent(Integer.toString(response.code()), message));
            }
            // response was successful
        }
        // Catches exceptions caused by lack of internet connectivity
        catch (UnknownHostException e) {
            Log.d("Interceptor", "Caught UnknownHostException");
            // TODO: Change to user friendly message (get from design/product team)
            // TODO: Message should be a constant (public static final String)
            String message = e.getMessage();
            eventBus.post(new RestIntercepterErrorEvent("UnknownHostException", message));
        }
        catch (SocketTimeoutException e) {
            Log.d("Interceptor", "Caught SocketTimeoutException");
            // TODO: Change to user friendly message (get from design/product team)
            // TODO: Message should be a constant (public static final String)
            String message = e.getMessage();
            eventBus.post(new RestIntercepterErrorEvent("SocketTimeoutException", message));
        }
        finally {
        }
        if (response == null) {
            Log.d("Interceptor", "Null response");
        }
        return response;
    }
}
