package com.capputechino.psmqtplayer.utils;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DocumentService extends Service<JSONObject> {
    private static final String URL_STRING = "http://psmlive.psm.mv/quran/api/";
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected JSONObject call() throws Exception {
                URL url = new URL(URL_STRING);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/63.0.3239.84 Chrome/63.0.3239.84 Safari/537.36");
                conn.connect();

                InputStream bis = conn.getInputStream();
                Scanner scanner = new Scanner(bis);
                String source = new String();
                while (scanner.hasNext()) {
                    source += scanner.next();
                }
                JSONObject jo = new JSONObject(source);
                return jo;
            }
        };
    }
}
