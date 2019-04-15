package android.chribru.dev.popularmovies.api;

import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.chribru.dev.popularmovies.models.dto.ReviewResultsDto;
import android.chribru.dev.popularmovies.models.dto.VideoResultsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbMovieApi {

    @GET("/3/movie/popular")
    Call<MovieResultsDto> popularMovieList(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<MovieResultsDto> topRatedMovieList(@Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<MovieDto> movieDetails(@Path("movie_id") int id);

    @GET("/3/movie/{movie_id}/videos")
    Call<VideoResultsDto> videos(@Path("movie_id") int id, @Query("language") String language);

    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewResultsDto> reviews(@Path("movie_id") int id, @Query("page") int page);
}
