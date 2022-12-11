package com.hyperctrl.jenkins.shared.library.utils

import com.google.gson.JsonObject

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

class HttpUtils {

    private HttpUtils() {
    }

    /**
     * httpClient
     */
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build()

    /**
     * post
     * @param url
     * @param obj
     * @return
     */
    static HttpResponse<JsonObject> post(String url, Object obj) {
        String json = JsonUtils.toJson(obj)
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .build()

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return response
    }

    /**
     * put
     * @param url
     * @param obj
     * @return
     */
    static HttpResponse<JsonObject> put(String url, Object obj) {
        String json = JsonUtils.toJson(obj)
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .build()

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return response
    }

    /**
     * get
     * @param url
     * @param obj
     * @return
     */
    static HttpResponse<JsonObject> get(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .build()

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return response
    }

    /**
     * delete
     * @param url
     * @param obj
     * @return
     */
    static HttpResponse<JsonObject> delete(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "application/json")
                .build()

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        return response
    }
}
