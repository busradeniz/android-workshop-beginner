# Task 2 - Getting Data from API
In task 2, we will fetch the top business headlines in the UK by using [newsapi](https://newsapi.org/) and convert to our own objects. 

To call Rest Api, we'll use Retrofit which is a HTTP Client for Android and one of the most common libraries and to convert the json response into objects defined by us, we'll use Retrofit gson converter. 

We added these two dependencies in task1 :

 ```
  implementation 'com.squareup.retrofit2:retrofit:2.3.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
 
 ```
To be able get data from API, we need :

**API KEY** :  `6b195c9d219f4487a12a02ea8085d798`  

**BASE_URL** : `https://newsapi.org/v2/`

**CATEGORY** : `business`

**COUNTRY** : `gb`


An example response coming from API : 

 ```
{
	"status": "ok",
	"totalResults": 20,
	"articles": [{
		"source": {
			"id": "bloomberg",
			"name": "Bloomberg"
		},
		"author": "Robert Burgess",
		"title": "The Daily Prophet: Draghi Couldn't Have Played It Any Better",
		"description": "Connecting the dots in global markets.",
		"url": "https://www.bloomberg.com/news/articles/2018-03-08/the-daily-prophet-draghi-couldn-t-have-played-it-any-better-jej1055k",
		"urlToImage": "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iuCICv_61skU/v0/1200x678.png",
		"publishedAt": "2018-03-08T22:30:23Z"
	}, {
		"source": {
			"id": null,
			"name": "Gizmodo.com"
		},
		"author": null,
		"title": "Congress Will Make Credit Freezes Free for All in Belated Response to Equifax Breach",
		"description": null,
		"url": "https://gizmodo.com/congress-will-make-credit-freezes-free-for-all-in-belat-1823624494",
		"urlToImage": null,
		"publishedAt": "2018-03-08T22:15:00Z"
	}]
}
 ```
 
## Creating API Layer

* Create `api` package under `com.[domain_name].newsapplication`  
* Create data models 

**Create Article.java** : 

 ```
package com.busradeniz.newsapplication.api;

public class Article {

    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;


    public Article(String author, String title, String description, String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }
}
 ```
 
 **Create ArticleListApiResponse.java** :
 
```
 package com.busradeniz.newsapplication.api;

import java.util.List;

public class ArticleListApiResponse {

    private final String status;
    private final int totalResults;
    private final List<Article> articles;

    public ArticleListApiResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return "ArticleListApiResponse{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
 ```
 
**Create Retrofit Interface - ArticleApi.java** :

```
package com.busradeniz.newsapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleApi {

    @GET("top-headlines")
    Call<ArticleListApiResponse> getArticleList(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);

}
 ```
 
 **Create Api Service - ArticleService.java** :  
 
 ```
 package com.busradeniz.newsapplication.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleService {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String COUNTRY = "gb";
    private static final String CATEGORY = "business";
    private static final String API_KEY = "6b195c9d219f4487a12a02ea8085d798";
    private static ArticleService instance = null;

    private ArticleApi articleApi;

    public static ArticleService getInstance() {
        if (instance == null) {
            instance = new ArticleService();
        }
        return instance;
    }

    private ArticleService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        articleApi = retrofit.create(ArticleApi.class);

    }

    public Call<ArticleListApiResponse> getArticles() {
        return articleApi.getArticleList(COUNTRY, CATEGORY, API_KEY);
    }
}
  ```
  
  **Call api from MainActivity.java** :  
  
  ``` 
  public class MainActivity extends AppCompatActivity implements Callback<ArticleListApiResponse> {

    private ArticleService articleService = ArticleService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getArticles();
    }

    private void getArticles() {
        articleService.getArticles()
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ArticleListApiResponse> call, Response<ArticleListApiResponse> response) {
        Log.i("MainActivity", "response : " + response.body().toString());
    }

    @Override
    public void onFailure(Call<ArticleListApiResponse> call, Throwable t) {
        Log.e("MainActivity", "getArticle failed: " + t.getLocalizedMessage());
    }
}
  ```
