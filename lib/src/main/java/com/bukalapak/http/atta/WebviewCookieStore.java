package com.bukalapak.http.atta;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xinuc on 5/12/14.
 */
public class WebviewCookieStore implements CookieStore {

    private final Context context;
    private final CookieManager cookieManager;
    private final CookieSyncManager syncManager;
    private String baseUrl;

    public WebviewCookieStore(Context context) {
        this.context = context;
        this.cookieManager = CookieManager.getInstance();
        this.cookieManager.setAcceptCookie(true);
        CookieSyncManager.createInstance(context);
        this.syncManager = CookieSyncManager.getInstance();
    }

    @Override
    public synchronized void addCookie(Cookie cookie) {
        this.cookieManager.setCookie(this.getCookieUrl(cookie), this.encodeCookie(cookie));
        this.syncManager.sync();
    }

    @Override
    public List<Cookie> getCookies() {
       String cookieStr = this.cookieManager.getCookie(this.baseUrl);
       String[] cookieStrings = cookieStr.split("; ");

       ArrayList<Cookie> cookies = new ArrayList();

       for(int i = 0; i < cookieStrings.length; i++) {
           String str = cookieStrings[i];
           String[] arr = str.split("=");
           String name = arr[0];
           String value = arr[1];

           BasicClientCookie cookie = new BasicClientCookie(name, value);
           cookies.add(cookie);
       }

       return cookies;
    }

    @Override
    public synchronized boolean clearExpired(Date date) {
        String before = this.cookieManager.getCookie(this.baseUrl);
        this.cookieManager.removeExpiredCookie();
        this.syncManager.sync();
        String after = this.cookieManager.getCookie(this.baseUrl);
        return !before.equals(after);
    }

    @Override
    public synchronized void clear() {
        this.cookieManager.removeAllCookie();
        this.syncManager.sync();
    }

    private String getCookieUrl(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        if (cookie.isSecure()) {
            sb.append("https");
        } else {
            sb.append("http");
        }
        sb.append(cookie.getDomain());
        sb.append(cookie.getPath());

        return sb.toString();
    }

    private String encodeCookie(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getName());
        sb.append("=");
        sb.append(cookie.getValue());
        sb.append("; domain=");
        sb.append(cookie.getDomain());

        return sb.toString();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
