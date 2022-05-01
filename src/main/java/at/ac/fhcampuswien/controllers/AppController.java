package at.ac.fhcampuswien.controllers;

import at.ac.fhcampuswien.NewsApi;
import at.ac.fhcampuswien.models.NewsResponse;
import at.ac.fhcampuswien.models.Article;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private List<Article> articles;
    private  NewsResponse newsResponse;

    public AppController() {
        articles = new ArrayList<Article>();
        newsResponse = new NewsResponse();
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
    }

    public List<Article> getArticles(){
        return articles;
    }

    /**
     * gets the size of article list
     * if the list is null it should return 0
     * @return size of article list
     */
    public int getArticleCount(){
        return NewsApi.getInstance().getTotalNews().getTotalResults();
    }

    /**
     * at the moment only returns the article list
     * not implemented yet
     * @return article list
     */

    // GetTopHeadlines Methode aus NewsApi wird gerufen wo die URL gebaut wird.
    // Es wird überprüft ob es zu einer Antwort von NewsResponse kommt, wenn
    // nicht wird eine leere Liste zurückgeliefert, wenn schon werden die Artikel zurpckgegeben.
    public List<Article> getTopHeadlinesAustria() {
        NewsResponse newsResponse = NewsApi.getInstance().getTopHeadlines("AT", NewsApi.Category.general, NewsApi.Country.at);

        if (newsResponse == null) {
            return new ArrayList<>();
        }else  {
            return newsResponse.getArticles();
        }
    }

    /**
     * returns all articles that do contain "bitcoin"
     * in their title
     * @return filtered list
     */
    public List<Article> getAllNewsBitcoin() {
        NewsResponse newsResponse = NewsApi.getInstance().getAllNews("bitcoin", NewsApi.Language.de, NewsApi.SortBy.relevancy);

        if (newsResponse == null) {
            return new ArrayList<>();
        }else {
            return newsResponse.getArticles();
        }
    }


    /**
     * method to generate a mocking list of articles
     * @return list of generated articles
     */
    /*
    private static List<Article> generateMockList(){
        List<Article> articles = new ArrayList<>();

        Article article1 = new Article("New York Times", "Eric Adams, a Bitcoin Booster, Is Taking First Paycheck in Crypto","da", "haha", "aa", "28.04", "articles");
       // Article article2 = new Article("News Sky", "Irishman held against his will in China for 3 years reunited with 'unbelievably happy' family");
        //Article article3 = new Article("News Sky", "Mother who won £127,000 tells how she still ended up homeless");

        articles.add(article1);
        //articles.add(article2);
        //articles.add(article3);

        return articles;
    }
     */

    /*
    /**
     * filters a given article list based on a query
     * @param query to filter by
     * @param articles  list to filter
     * @return filtered list


    protected static List<Article> filterList(String query, List<Article> articles){
        if(query != null && articles != null) {
            List<Article> filtered = new ArrayList<>();
            for(Article article : articles){
                if(article.getTitle().toLowerCase().contains(query.toLowerCase())){
                    filtered.add(article);
                }
            }
            return filtered;
        } else {
            return new ArrayList<>();
            //throw new IllegalArgumentException();
        }
    }
    */
}
