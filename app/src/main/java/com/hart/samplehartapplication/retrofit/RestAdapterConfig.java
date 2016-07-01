package com.hart.samplehartapplication.retrofit;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import retrofit.ErrorHandler;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
public abstract class RestAdapterConfig {
    private String endpoint;
    private Gson gson;
    private OAuth oAuth;
    private Converter converter;
    private Client client;
    private ErrorHandler errorHandler;
    public RestAdapterConfig() {
    }
    public final void reset() {
        this.endpoint = null;
        this.gson = null;
        this.oAuth = null;
        this.converter = null;
        this.errorHandler = null;
    }
    protected final String getEndpoint() {
        if(this.endpoint == null) {
            this.endpoint = this.createEndpoint();
        }
        return this.endpoint;
    }
    protected final Gson getGson() {
        if(this.gson == null) {
            this.gson = this.createGson();
        }
        return this.gson;
    }
    protected final OAuth getOAuth() {
        if(this.oAuth == null) {
            this.oAuth = this.createOAuth();
        }
        return this.oAuth;
    }
    protected Converter getConverter() {
        if(this.converter == null) {
            this.converter = this.createConverter();
        }
        return this.converter;
    }
    protected Client getClient() {
        if(this.client == null) {
            this.client = this.createClient();
        }
        return this.client;
    }
    protected ErrorHandler getErrorHandler() {
        if(this.errorHandler == null) {
            this.errorHandler = new RestErrorHandler();
        }
        return this.errorHandler;
    }
    protected abstract String createEndpoint();
    protected Gson createGson() {
        return new Gson();
    }
    protected abstract OAuth createOAuth();
    protected Client createClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new RestIntercepter());
        return new OkClient(okHttpClient);
    }
    protected Converter createConverter() {
        return new GsonConverter(this.getGson());
    }
}