package android.chribru.dev.popularmovies.network;

import android.chribru.dev.popularmovies.api.TheMovieDbMovieApi;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;

import retrofit2.Call;

public class MovieClient {
    private final TheMovieDbMovieApi movieApi;

    public MovieClient() {
        movieApi = ApiClientBuilder.getBuilder().getClientWithApiKey(Constants.API_KEY).create(TheMovieDbMovieApi.class);
    }

    /**
     * Returns the list of popular movies.
     * A single page holds 20 movies.
     */
    public Call<MovieResults> getPopularMovies() {
        return movieApi.popularMovieList();
    }

    /**
     * Returns the list of top rated movies.
     * A single page holds 20 movies.
     */
    public Call<MovieResults> getTopRatedMovies() {
        return movieApi.topRatedMovieList();
    }

    /**
     * Returns all known details of the movie.
     * @param id the id of the movie
     */
    public Call<Movie> getMovieDetails(int id) {
        return movieApi.movieDetails(id);
    }

    /**
     * Returns videos for a given movie.
     * @param id the id of the movie
     * @param language the language of the requested videos
     */
    public Call<VideoResults> getVideosForMovie(int id, String language) {
        return movieApi.videos(id, language);
    }

    /**
     * Returns reviews for a given movie.
     * @param id the id of the movie
     * @param page the page of the results
     */
    public Call<ReviewResults> getReviewsForMovie(int id, int page) {
        return movieApi.reviews(id, page);
    }
}
