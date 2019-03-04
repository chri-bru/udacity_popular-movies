package android.chribru.dev.popularmovies.network;

import android.chribru.dev.popularmovies.api.TheMovieDbMovieApi;
import android.chribru.dev.popularmovies.models.Results;

import retrofit2.Call;

public class MovieClient {
    TheMovieDbMovieApi movieApi;

    public MovieClient(String apiKey) {
        movieApi = ApiClientBuilder.getBuilder().getClientWithApikey(apiKey).create(TheMovieDbMovieApi.class);
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
}
