package android.chribru.dev.popularmovies.network;

import android.chribru.dev.popularmovies.api.TheMovieDbMovieApi;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Results;

import retrofit2.Call;

public class MovieClient {
    private final TheMovieDbMovieApi movieApi;

    public MovieClient() {
        movieApi = ApiClientBuilder.getBuilder().getClientWithApiKey(Constants.API_KEY).create(TheMovieDbMovieApi.class);
    }

    /**
     * Returns the list of popular movies.
     * A single page holds 20 movies.
     * @param page the page of the results
     */
    public Call<Results> getPopularMovies(int page) {
        return movieApi.popularMovieList(page);
    }

    /**
     * Returns the list of top rated movies.
     * A single page holds 20 movies.
     * @param page the page of the results
     */
    public Call<Results> getTopRatedMovies(int page) {
        return movieApi.topRatedMovieList(page);
    }

    /**
     * Returns all known details of the movie.
     * @param id the id of the movie
     */
    public Call<Movie> getMovieDetails(int id) {
        return movieApi.movieDetails(id);
    }
}
