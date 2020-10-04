package com.word_searcher.service;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.word_searcher.exception.DocumentNotFoundException;

import java.io.IOException;
import java.net.HttpURLConnection;

public class DocumentLoader {

    private OkHttpClient okHttpClient = new OkHttpClient();


    private DocumentLoader() {
    }
    private static DocumentLoader documentLoader;

    public static DocumentLoader instance() {
        if (documentLoader == null) {
            documentLoader = new DocumentLoader();
        }
        return documentLoader;
    }

    public String getDocument(String path) throws DocumentNotFoundException {
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() == HttpURLConnection.HTTP_OK) {
                return response.body().string();
            } else {
                throw new DocumentNotFoundException(String.format("Document not found. Path: %s.", path));
            }
        } catch (IOException e) {
            throw new DocumentNotFoundException(e);
        }
    }
}
