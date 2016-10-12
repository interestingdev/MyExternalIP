package com.leftdog.android.tools.myexternalip;

import android.util.Patterns;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leftdog
 */
public class MyExternalIp {

    private static final Pattern IPV4_PATTERN = Pattern.compile(
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
    private static final String DEFAULT_URL = "http://whatismyip.org/";
    private static String mCachedIp = null;

    /**
     *
     * @return the external IP(v4 only) received from default server,
     * or null if no response or error occurred.
     */
    public static String getIp() {
        return getIpFrom(DEFAULT_URL);
    }

    /**
     * @param url the server's url that is able to give response and
     *            have IP(v4) included.
     *
     * @return the external IP(v4 only) received from provided server,
     * or null if no response or error .
     * WARNING: it is not defined if server gives unexpected response.
     */
    public static String getIpFrom(String url) {
        if (url != null
                && Patterns.WEB_URL.matcher(url).matches()) {

            mCachedIp = getExternalIpFromUrl(url);
        }
        return mCachedIp;
    }

    /**
     *
     * @return the external IP(v4 only) that is ever cached from the most
     * recently succeeded request, or null if no succeeded response ever.
     */
    public static String getCachedIp() {
        return mCachedIp;
    }

    private static String getExternalIpFromUrl(String fromUrl) {
        Scanner s = null;
        try {
            URL url = new URL(fromUrl);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Protocol", "Http/1.1");
            connection.addRequestProperty("Connection", "keep-alive");
            connection.addRequestProperty("Keep-Alive", "1000");
            connection.addRequestProperty("User-Agent", "Web-Agent");

            s = new Scanner(connection.getInputStream());
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String ipAddr = findIpAddr(line);
                if (ipAddr != null) {
                    return ipAddr;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                s.close();
        }
        return null;
    }

    private static String findIpAddr(final String ipString) {
        Matcher matcher = IPV4_PATTERN.matcher(ipString);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}

