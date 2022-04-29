package at.ac.fhcampuswien;

import at.ac.fhcampuswien.Response.NewsResponse;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;

public class NewsApi {
    NewsResponse newsResponse = new NewsResponse();

    private static final String apiKey ="2a096bd8d7cc4bc194d33596a9d31cfd";
    private static final String url = "https://newsapi.org/v2";
    private static final OkHttpClient client = new OkHttpClient();

    private static String endpoint;
    private static String q;
    private static String category;
    private static String country;
    private static String language;
    private static String sortBy;

    // Setter for these values:


    public static void setEndpoint(String endpoint) {
        NewsApi.endpoint = endpoint;
    }

    public static void setQ(String q) {
        NewsApi.q = q;
    }

    public static void setCategory(String category) {
        NewsApi.category = category;
    }

    public static void setCountry(String country) {
        NewsApi.country = country;
    }

    public static void setLanguage(String language) {
        NewsApi.language = language;
    }

    public static void setSortBy(String sortBy) {
        NewsApi.sortBy = sortBy;
    }

    // Getter for the values:

    // "&" is the needed prefix to make a URL out of these values.
    public static String getEndpoint() {
        return endpoint;
    }

    public static String getQ() {
        return "&q" + q;
    }

    public static String getCategory() {
        return "&category" + category;
    }

    public static String getCountry() {
        return "&country" + country;
    }

    public static String getLanguage() {
        return "&language" + language;
    }

    public static String getSortBy() {
        return "&sortBY" + sortBy;
    }


    public String generateUrl() {
        String URL1;
        if (endpoint.equals("top-headlines")) {
            if (country.equals("all")) {
                setCountry("");
            }
            URL1 = url + getEndpoint() + apiKey + getQ() + getCountry() + getCategory();
        }else {
            if (language.equals("all")) {
                setLanguage("");
            }
            URL1 = url + getEndpoint() + apiKey + getQ() + getSortBy() + getLanguage();
        }

        setEndpoint("");
        setQ("");
        setCategory("");
        setCountry("");
        setLanguage("");
        setSortBy("");

        return URL1;
    }

    public String buildUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public NewsResponse deserializeArticles(String url) throws IOException {
        Gson gson = new Gson();

        newsResponse = gson.fromJson(buildUrl(url), NewsResponse.class);

        return newsResponse;
    }























}
