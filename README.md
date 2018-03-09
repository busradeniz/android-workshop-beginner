# Task 3 - Showing data on the screen
In task3, we'll list the articles coming from api on the screen, use RecyclerView for it.

**1 - Add RecyclerView in activity_main.xml** : 

 ```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.busradeniz.newsapplication.ui.MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</android.support.constraint.ConstraintLayout>
 ```
 
**2 - Create a new layout for list item - item_article.xml under res/layout directory** : 

 ```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <ImageView
        android:id="@+id/imgArticle"
        android:layout_width="180dp"
        android:layout_height="120dp" />

    <TextView
        android:id="@+id/txtTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="5dp"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_gravity="center_vertical"/>
</LinearLayout>
 ```

**3 - Create adapter for recyclerview - ArticleAdapter.java** : 

 ```
public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ArticleViewHolder> {

    private List<Article> articleList = new ArrayList<>();

    @NonNull
    @Override
    public ArticleRecyclerViewAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerViewAdapter.ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(article.getUrlToImage())
                .into(holder.imgArticle);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void updateDataSet(List<Article> list) {
        articleList.addAll(list);
    }

    protected class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgArticle;

        ArticleViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.txtTitle);
            imgArticle = view.findViewById(R.id.imgArticle);
        }
    }

}
 ```
 
 **4 - Initialize recyclerview in MainActivity.java** : 

 ```
public class MainActivity extends AppCompatActivity implements Callback<ArticleListApiResponse> {

    private ArticleService articleService = ArticleService.getInstance();
    private ArticleRecyclerViewAdapter adapter;
    private RecyclerView articleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();
        getArticles();
    }

    private void initRecyclerView() {
        articleRecyclerView = findViewById(R.id.recyclerView);

        adapter = new ArticleRecyclerViewAdapter();
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleRecyclerView.setAdapter(adapter);
    }

    private void getArticles() {
        articleService.getArticles()
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<ArticleListApiResponse> call, Response<ArticleListApiResponse> response) {
        Log.i("MainActivity", "response : " + response.body().toString());
        adapter.updateDataSet(response.body().getArticles());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<ArticleListApiResponse> call, Throwable t) {
        Log.e("MainActivity", "getArticle failed: " + t.getLocalizedMessage());
    }
}
 
 ```
 
**5 - Run application** : 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task3/img/1.png)


