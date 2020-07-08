package cn.lefer.tools.Net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class LeferNet {
    public static boolean isValid(String urlStr) throws MalformedURLException, IOException, SocketTimeoutException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(3000);
        boolean valid = connection.getResponseCode() < 400;
        connection.disconnect();
        return valid;
    }
}
