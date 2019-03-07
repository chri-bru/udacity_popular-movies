package android.chribru.dev.popularmovies.api;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbMovieApi {

    @GET("/3/movie/popular")
    Call<Results> popularMovieList(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<Results> topRatedMovieList(@Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<Movie> movieDetails(@Path("movie_id") int id);
}
