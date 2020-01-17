package com.example.daggerimplementation.di.component;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {
    private Context context;
    private String intentAction = "com.netree.foop.SESSION_LISTENER";

    public ResponseInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        // todo deal with the issues the way you need to
        if (response.code() == 401) {
           /* Intent intent = new Intent(context, SessionListener.class);
            intent.setAction(intentAction);
            context.sendBroadcast(intent);*/
//                    return response;
        }




        return response;
    }
}
