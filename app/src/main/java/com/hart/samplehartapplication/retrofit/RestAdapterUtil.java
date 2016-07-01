package com.hart.samplehartapplication.retrofit;

import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestAdapterUtil {
    private static final Map<RestAdapterConfig, RestAdapter> restAdapters = new HashMap();
    public RestAdapterUtil() {
    }
    public static <T> T createRestService(RestAdapterConfig restAdapterConfig, Class<T> t) {
        return getRestAdapter(restAdapterConfig).create(t);
    }
    private static final RestAdapter getRestAdapter(RestAdapterConfig restAdapterConfig) {
        RestAdapter restAdapter = null;
        if(restAdapters.containsKey(restAdapterConfig)) {
            restAdapter = (RestAdapter)restAdapters.get(restAdapterConfig);
        } else {
            restAdapter = createRestAdapter(restAdapterConfig);
            restAdapters.put(restAdapterConfig, restAdapter);
        }
        return restAdapter;
    }
    private static RestAdapter createRestAdapter(RestAdapterConfig restAdapterConfig) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(new RestIntercepter());
        builder.setEndpoint(restAdapterConfig.getEndpoint());
        builder.setClient(new OkClient(httpClient));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setConverter(restAdapterConfig.getConverter());
        builder.setClient(restAdapterConfig.getClient());
        final OAuth oAuth = restAdapterConfig.getOAuth();
        if(oAuth != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                public void intercept(RequestFacade request) {
                    request.addQueryParam(oAuth.getQueryParamName(), oAuth.getAuth());
                    request.addHeader("Accept", "application/json");
                }
            });
        }
        return builder.build();
    }
}