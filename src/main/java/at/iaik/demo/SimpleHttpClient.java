package at.iaik.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SimpleHttpClient {
    
    private final OkHttpClient client;
    
    public SimpleHttpClient() {
        client = new OkHttpClient();
    }
    
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        
        try(Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
