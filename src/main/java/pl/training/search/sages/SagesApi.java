package pl.training.search.sages;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface SagesApi {

    @GET("api/storage/training?pagesize=1000")
    Observable<List<Training>> getTrainings();

}
