package com.hart.samplehartapplication.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hart.samplehartapplication.HomeActivity;
import com.hart.samplehartapplication.R;
import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import retrofit.RequestInterceptor;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import timber.log.Timber;

/**
 * Created by markzepeda on 4/28/15.
 */
public class HartApiRestConfig extends RestAdapterConfig {

    private static final String LOGTAG = HartApiRestConfig.class.getSimpleName();

    @Override
    protected String createEndpoint() {
        return "";
//        return "http://prov-dev.hart.com/v2/";
//        return "http://192.168.10.82:3000/v2/";//for everything else
//        return "http://192.168.10.82:8686/";//for payment
    }

    @Override
    protected OAuth createOAuth() {
        return OAuth.oauthInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
//                if (UserUtil.getUserToken(HomeActivity.cacheManager) != null){
//                    request.addHeader("x-access-token", UserUtil.getUserToken(HomeActivity.cacheManager));
//                }
            }
        });
    }

    @Override
    protected Gson createGson() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(Object.class, new FalseObjectDeserializer())
                .create();
    }

    @Override
    protected Client createClient() {
        Client client = null;
        try {


            OkHttpClient okhttpclient = new OkHttpClient();
            okhttpclient.interceptors().add(new RestIntercepter());
//            okhttpclient.interceptors().add(new StethoInterceptor());
            okhttpclient.setReadTimeout(2, TimeUnit.MINUTES);
            okhttpclient.setConnectTimeout(2, TimeUnit.MINUTES);

            client = new OkClient(okhttpclient);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(client == null){
            Timber.e("Failed to create client falling back to super.createClient()");
            client = super.createClient();
        }

        return client;
    }

    protected Converter createConverter(){
        return new GsonConverter(getGson());
    }

    private static class FalseObjectDeserializer implements JsonDeserializer<Object> {
        Gson gson = new Gson();

        @Override
        public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonNull()) {
                return null;
            }
            try {
                String string = json.toString();

                if (string != null) {
                    if(string.equalsIgnoreCase("false")) {
                        return null;
                    }else{
                        String removedFalses = string.replace("\"false\"", "null");
                        return gson.fromJson(removedFalses, typeOfT);
                    }
                }
            } catch (Exception e) {
                Timber.e(json.toString());
                e.printStackTrace();
            }
            return null;
        }
    }
}