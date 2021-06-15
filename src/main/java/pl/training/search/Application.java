package pl.training.search;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.training.search.github.GitHubApi;
import pl.training.search.github.QueryResult;
import pl.training.search.github.Repository;
import pl.training.search.wikipedia.Article;
import pl.training.search.wikipedia.Response;
import pl.training.search.wikipedia.WikipediaApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;

@Log
public class Application {

    private final GitHubApi gitHubApi = retrofitBuilder("https://api.github.com/").create(GitHubApi.class);
    private final WikipediaApi wikipediaApi = retrofitBuilder("https://en.wikipedia.org/w/").create(WikipediaApi.class);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Retrofit retrofitBuilder(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(new OkHttpClient().newBuilder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(BASIC))
                    .build())
                .build();
    }

    private List<Article> toArticles(Response response) {
        return response.getQuery().getSearch();
    }

    private List<String> toList(List<String> result, String value) {
        var newResult = new ArrayList<>(result);
        newResult.add(value);
        return  newResult;
    }

    private List<String> toResultList(List<String> result, List<String> otherResult) {
        var newResult = new ArrayList<>(result);
        newResult.addAll(otherResult);
        return newResult;
    }

    private Observable<List<String>> wikipediaQuery(String query) {
        return wikipediaApi.getArticles(query)
                .map(this::toArticles)
                .flatMap(Observable::fromIterable)
                .map(Article::getTitle)
                .reduce(new ArrayList<>(), this::toList)
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    private Observable<List<String>> gitHubQuery(String query) {
        return gitHubApi.getRepositories(query)
                .map(QueryResult::getItems)
                .flatMap(Observable::fromIterable)
                .map(Repository::getName)
                .reduce(new ArrayList<>(), this::toList)
                .toObservable()
                .subscribeOn(Schedulers.io());
    }

    private void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(compositeDisposable::dispose));

        /*var request = wikipediaQuery("java")
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));*/

        /*ObservableReader.from(System.in)
                .map(String::toUpperCase)
                .debounce(5, TimeUnit.SECONDS)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));*/

        /*var subscription = ObservableReader.from(System.in)
                .debounce(5, TimeUnit.SECONDS)
                .flatMap(this::wikipediaQuery)
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));*/

        var subscription = ObservableReader.from(System.in)
                .debounce(5, TimeUnit.SECONDS)
                .flatMap(query -> Observable.zip(wikipediaQuery(query), gitHubQuery(query), this::toResultList))
                .flatMap(Observable::fromIterable)
                .map(String::toLowerCase)
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println, System.out::println, () -> System.out.println("Completed"));


        compositeDisposable.addAll(subscription);
    }

    @SneakyThrows
    public static void main(String[] args) {
        new Application().start();
        log.info("Waiting...");
        Thread.sleep(100_000);
    }

}
