package api;

import config.ConfigLoader;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class KakaoApi {

    private HttpRequest request;
    private HttpResponse<String> response;
    private final HttpClient httpClient = HttpClient.newBuilder().
            version(HttpClient.Version.HTTP_2).
            build();
    private String url;
    private static final String BASE_URL = "https://dapi.kakao.com/v3/search/book";
    private static final String REST_API_KEY = ConfigLoader.getApiKey();
    private List<JsonObject> extractedInfoList;

    public List<JsonObject> getExtractedInfoList() {
        return extractedInfoList;
    }

    public String searchBookInfo(String book) throws IOException, InterruptedException {

        url = BASE_URL + "?query=" + book;
        request = HttpRequest.newBuilder().
                GET().
                uri(URI.create(url)).
                header("Authorization", REST_API_KEY).
                build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public void extractInfo(String responseBody) {

        extractedInfoList = new ArrayList<>();

        JsonReader jsonReader = Json.createReader(new StringReader(responseBody));
        JsonObject jsonObject = jsonReader.readObject();

        JsonArray documents = jsonObject.getJsonArray("documents");
        int i = 0;
        while (i < documents.size()) {
            JsonObject document = documents.getJsonObject(i);
            extractedInfoList.add(document);
            i++;
        }


    }

}