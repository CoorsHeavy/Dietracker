package com.hart.samplehartapplication.retrofit;

import android.util.Base64;

import retrofit.RequestInterceptor;

public class OAuth
{
    public enum OAuthMode
    {
        None, Basic, Bearer, QueryToken, Interceptor
    }

    private final OAuthMode oAuthMode;
    private String auth;
    private String queryParam;
    private RequestInterceptor interceptor;

    /**
     * Constructs an OAuth object that will not alter any data sent from a RestAdapter.
     * @return
     */
    public static OAuth oauthNone()
    {
        return new OAuth("", OAuthMode.None);
    }

    /**
     * Constructs an OAuth object that will append an
     * "Authorization: Basic [Base64Encoded credentials]" header to all of a RestAdapter's calls.
     * @param clientId
     * @param clientSecret
     * @return
     */
    public static OAuth oauthBasic(String clientId, String clientSecret)
    {
        return new OAuth(String.format("Basic %s", Base64.encodeToString(String.format("%s:%s", clientId, clientSecret).getBytes(), Base64.NO_WRAP|Base64.NO_PADDING)), OAuthMode.Basic);
    }
    /**
     * Constructs an OAuth object that will append an
     * "Authorization: Basic [encodedSecret]" header to all of a RestAdapter's calls.
     * @param encodedSecret
     * @return
     */
    public static OAuth oauthBasic(String encodedSecret)
    {
        return new OAuth(String.format("Basic %s", encodedSecret), OAuthMode.Basic);
    }

    /**
     * Constructs an OAuth object that will append an
     * "Authorization: Bearer [token]" header to all of a RestAdapter's calls.
     * @param token
     * @return
     */
    public static OAuth oauthBearer(String token)
    {
        return new OAuth(String.format("Bearer %s", token), OAuthMode.Bearer);
    }
    public static OAuth oauthInterceptor(RequestInterceptor interceptor){
        return new OAuth(interceptor);
    }

    /**
     * Constructs an OAuth object that will add a query parameter to
     * all of a RestAdapter's calls.
     *
     * Will add [queryParamName]=token to a calls query parameters.
     * @param token
     * @param queryParamName
     * @return
     */
    public static OAuth oauthQueryToken(String token, String queryParamName)
    {
        return new OAuth(token, queryParamName);
    }

    private OAuth(String token, OAuthMode mode)
    {
        this.auth = token;
        this.oAuthMode = mode;
    }

    private OAuth(String token, String queryParamName)
    {
        this.auth = token;
        this.oAuthMode = OAuthMode.QueryToken;
        this.queryParam = queryParamName;
    }
    private OAuth(RequestInterceptor interceptor){
        this.interceptor = interceptor;
        this.oAuthMode = OAuthMode.Interceptor;
    }

    protected final String getAuth()
    {
        return auth;
    }

    protected OAuthMode getAuthMode()
    {
        return oAuthMode;
    }

    protected String getQueryParamName()
    {
        return queryParam;
    }
    public RequestInterceptor getInterceptor() {
        return interceptor;
    }
}