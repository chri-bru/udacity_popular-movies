package android.chribru.dev.popularmovies.network;

import android.chribru.dev.popularmovies.api.TheMovieDbMovieApi;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.chribru.dev.popularmovies.models.dto.ReviewResultsDto;
import android.chribru.dev.popularmovies.models.dto.VideoResultsDto;

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
    public Call<MovieResultsDto> getPopularMovies(int page) {
        return movieApi.popularMovieList(page);
    }

    /**
     * Returns the list of top rated movies.
     * A single page holds 20 movies.
     * @param page the page of the results
     */
    public Call<MovieResultsDto> getTopRatedMovies(int page) {
        return movieApi.topRatedMovieList(page);
    }

    /**
     * Returns all known details of the movie.
     * @param id the id of the movie
     */
    public Call<MovieDto> getMovieDetails(int id) {
        return movieApi.movieDetails(id);
    }

    /**
     * Returns videos for a given movie.
     * @param id the id of the movie
     * @param language the language of the requested videos
     */
    public Call<VideoResultsDto> getVideosForMovie(int id, String language) {
        return movieApi.videos(id, language);
    }

    /**
     * Returns reviews for a given movie.
     * @param id the id of the movie
     * @param page the page of the results
     */
    public Call<ReviewResultsDto> getReviewsForMovie(int id, int page) {
        return movieApi.reviews(id, page);
    }
}
