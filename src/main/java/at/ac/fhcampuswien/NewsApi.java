package at.ac.fhcampuswien;

import at.ac.fhcampuswien.Response.NewsResponse;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.intellij.lang.annotations.Language;

public class NewsApi {
    private static final String apiKey ="2a096bd8d7cc4bc194d33596a9d31cfd";
    private static final String url = "https://newsapi.org/v2";
    private static NewsApi instance;
    private OkHttpClient client;
    public enum Language {ar, de, en, es, fr, he, it, nl, no, pt, ru, se, ud, zh}
    public enum Category {business, entertainment, general, health, science, sports, technology}
    public enum Country {ae, ar, at, au, be, bg, br, ca, ch, cn, co, cu, cz, de, eg, fr, gb, gr,
        hk, hu, id, ie, il, in, it, jp, kr, lt, lv, ma, mx, my, ng, nl, no, nz, ph, pl, pt, ro, rs, ru,
        sa, se, sg, si, sk, th, tr, tw, ua, us, ve, za }
    public enum SortBy {relevancy, popularity, publishedAt}


    private NewsApi() {
        client = new OkHttpClient();
    }

    public static NewsApi getInstance() {
        if (instance == null) {
            instance = new NewsApi();
        }
        return instance;
    }

    private NewsResponse request(HttpUrl.Builder urlBuilder) {
        urlBuilder.addQueryParameter("apiKey", apiKey);

        Request request = new Request.Builder().url(urlBuilder.build()).build();

        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            String responseString = response.body().string();
            NewsResponse newsResponse = gson.fromJson(responseString, NewsResponse.class);
            return newsResponse;
        } catch (Exception e) {
            System.out.println("Something went wrong!");
            return null;
        }
    }
    public NewsResponse getTopHeadlines(String country, Category category, Country choice) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addEncodedPathSegment("top-headlines");

        urlBuilder.addQueryParameter("category", category.toString());
        urlBuilder.addQueryParameter("country", choice.toString());

        return request(urlBuilder);
    }


    public NewsResponse getAllNews(String query, Language language, SortBy sortBy) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment("everything");

        urlBuilder.addQueryParameter("q", query);
        urlBuilder.addQueryParameter("language", language.toString());
        urlBuilder.addQueryParameter("SortBy", sortBy.toString());

        return request(urlBuilder);
    }








}
