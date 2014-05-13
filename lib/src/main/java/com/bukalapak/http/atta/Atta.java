package com.bukalapak.http.atta;

import java.util.concurrent.RunnableFuture;

/**
 * Created by xinuc on 5/12/14.
 */
public class Atta {

    private final int memCache;
    private final int fileCache;

    public Atta(int memCache, int fileCache) {
        this.memCache = memCache;
        this.fileCache = fileCache;
    }

    public String get(String url, RunnableFuture future) {
        return null;
    }


}
