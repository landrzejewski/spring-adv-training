package pl.training.search.github;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("search/repositories")
    Observable<QueryResult> getRepositories(@Query("q") String query);

}
