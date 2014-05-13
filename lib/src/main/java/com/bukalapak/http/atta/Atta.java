package com.bukalapak.http.atta;

import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;

import java.util.concurrent.Callable;

/**
 * Created by xinuc on 5/12/14.
 */
public class Atta {

    private final int memCache;
    private final int fileCache;
    private final AsyncHttpClient agent;

    public Atta(Context context, String baseUrl, int memCache, int fileCache) {
        this.memCache = memCache;
        this.fileCache = fileCache;
        this.agent = new AsyncHttpClient();
        WebviewCookieStore cookieStore = new WebviewCookieStore(context);
        cookieStore.setBaseUrl(baseUrl);
        this.agent.setCookieStore(cookieStore);
    }

    public String get(String url, Callable onCacheCallback, Callable onUpdateCallback, Callable onErrorCallback) {
        return null;
    }


}
