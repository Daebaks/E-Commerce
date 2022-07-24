package com.revature.config.interceptor;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * OkHttp interceptor that adds basic authentication
 */
public class BasicAuthInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final String authToken;

    public BasicAuthInterceptor(String username, String password) {
        this.authToken = Credentials.basic(username, password);
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header(AUTHORIZATION_HEADER, authToken)
                .build();
        return chain.proceed(request);
    }
}
