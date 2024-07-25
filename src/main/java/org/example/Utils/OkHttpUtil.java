package org.example.Utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OkHttpUtil {
    private static final OkHttpClient client;
    public static final MediaType MEDIA_TYPE_JSON;


    public static String doGet(String url) {
        Call call = createGetCall(url, new HashMap());
        return execute(call);
    }

    public static String doGet(String url, Map<String, Object> params) {
        Call call = createGetCall(url, params);
        return execute(call);
    }

    public static InputStream doGetFileStream(String url, Map<String, Object> params) {
        Call call = createGetCall(url, params);
        return executeAndReturnStream(call);
    }

    public static String doPost(String url, Map<String, Object> params) {
        Call call = createPostCall(url, params);
        return execute(call);
    }

    public static String doPost(String url) {
        Call call = createPostJsonCall(url, "");
        return execute(call);
    }

    public static String doPost(String url, String json) {
        Call call = createPostJsonCall(url, json);
        return execute(call);
    }

    public static void doGetAsync(String url, Map<String, Object> params, Callback callback) {
        Call call = createGetCall(url, params);
        call.enqueue(callback);
    }

    public static void doPostAsync(String url, Map<String, Object> params, Callback callback) {
        Call call = createPostCall(url, params);
        call.enqueue(callback);
    }

    public static void doPostAsync(String url, String json, Callback callback) {
        Call call = createPostJsonCall(url, json);
        call.enqueue(callback);
    }

    private static Call createGetCall(String url, Map<String, Object> params) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        HttpUrl httpUrl = createHttpUrl(request, params);
        builder.url(httpUrl).build();
        return client.newCall(builder.build());
    }

    private static Call createPostCall(String url, Map<String, Object> params) {
        Request request = (new Request.Builder()).post(createFormBody(params)).url(url).build();
        return client.newCall(request);
    }

    private static Call createPostJsonCall(String url, String json) {
        Request request = (new Request.Builder()).post(RequestBody.create(MEDIA_TYPE_JSON, json)).url(url).build();
        return client.newCall(request);
    }

    private static HttpUrl createHttpUrl(Request request, Map<String, Object> params) {
        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        if (params != null && params.size() > 0) {
            Iterator var3 = params.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                urlBuilder.addQueryParameter((String)entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        return urlBuilder.build();
    }

    private static FormBody createFormBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            Iterator var2 = params.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var2.next();
                builder.add((String)entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        return builder.build();
    }

    public static String execute(Call call) {
        String respStr = "";

        try {
            ResponseBody body = call.execute().body();
            if (body != null) {
                respStr = body.string();
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return respStr;
    }

    public static InputStream executeAndReturnStream(Call call) {
        InputStream respStr = null;

        try {
            ResponseBody body = call.execute().body();
            if (body != null) {
                return body.byteStream();
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return (InputStream)respStr;
    }

    static {
        client = (new OkHttpClient.Builder()).readTimeout(60L, TimeUnit.SECONDS).build();
        MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");
    }
}
