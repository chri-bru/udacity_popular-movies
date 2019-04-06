package android.chribru.dev.popularmovies.api;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbMovieApi {

    @GET("/3/movie/popular")
    Call<MovieResults> popularMovieList(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<MovieResults> topRatedMovieList(@Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<Movie> movieDetails(@Path("movie_id") int id);

    @GET("/3/movie/{movie_id}/videos")
    Call<VideoResults> videos(@Path("movie_id") int id, @Query("language") String language);

    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewResults> reviews(@Path("movie_id") int id, @Query("page") int page);
}
