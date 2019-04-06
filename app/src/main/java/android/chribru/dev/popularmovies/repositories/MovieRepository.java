package android.chribru.dev.popularmovies.repositories;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private MovieClient client;
    private static MovieRepository instance;

    private MovieRepository() {
        client = new MovieClient();
    }

    /**
     * Singleton implementation
     * @return  an instance of this class
     */
    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }

        return instance;
    }

    public LiveData<Movie> getMovieDetails(int id) {
        final MutableLiveData<Movie> data = new MutableLiveData<>();
        client.getMovieDetails(id).enqueue(new MovieCallbackHandler<>(data));
        return data;
    }

    public LiveData<MovieResults> getPopularMovies(int page) {
        final MutableLiveData<MovieResults> data = new MutableLiveData<>();
        client.getPopularMovies(page).enqueue(new MovieCallbackHandler<>(data));
        return data;
    }

    public LiveData<MovieResults> getTopRatedMovies(int page) {
        final MutableLiveData<MovieResults> data = new MutableLiveData<>();
        client.getTopRatedMovies(page).enqueue(new MovieCallbackHandler<>(data));
        return data;
    }

    public LiveData<VideoResults> getVideosForMovie(int id, String language) {
        final MutableLiveData<VideoResults> data = new MutableLiveData<>();
        client.getVideosForMovie(id, language).enqueue(new VideosCallbackHandler(data));
        return data;
    }

    public LiveData<ReviewResults> getReviewsForMovie(int id, int page) {
        final MutableLiveData<ReviewResults> data = new MutableLiveData<>();
        client.getReviewsForMovie(id, page).enqueue(new ReviewsCallbackHandler(data));
        return data;
    }

    public LiveData<MovieResults> getFavorites() {
        return null;
    }


    /**
     * Callback handler for handling async requests via Retrofit
     */
    private class MovieCallbackHandler<T> implements Callback<T> {

        private final MutableLiveData data;

        public MovieCallbackHandler(MutableLiveData<T> data) {
            this.data = data;

        }

        @Override
        public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response) {
            Log.i(this.getClass().getName(), "Request was successful!");

            if (response.isSuccessful()) {
                data.setValue(response.body());
            }
        }

        @Override
        public void onFailure(@NotNull Call<T> call, @NotNull Throwable t) {
            Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
            data.setValue(new Movie());
        }
    }

    /**
     * Callback handler for handling async requests via Retrofit for videos
     */
    private class VideosCallbackHandler implements Callback<VideoResults> {

        private final MutableLiveData<VideoResults> data;

        public VideosCallbackHandler(MutableLiveData<VideoResults> data) {
            this.data = data;
        }

        @Override
        public void onResponse(@NotNull Call<VideoResults> call, @NotNull Response<VideoResults> response) {
            Log.i(this.getClass().getName(), "Request was successful!");

            if (response.isSuccessful()) {
                data.setValue(response.body());
            }
        }

        @Override
        public void onFailure(@NotNull Call<VideoResults> call, @NotNull Throwable t) {
            Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
            data.setValue(new VideoResults());
        }
    }

    /**
     * Callback handler for handling async requests via Retrofit for reviews
     */
    private class ReviewsCallbackHandler implements Callback<ReviewResults> {

        private final MutableLiveData<ReviewResults> data;

        public ReviewsCallbackHandler(MutableLiveData<ReviewResults> data) {
            this.data = data;
        }

        @Override
        public void onResponse(@NotNull Call<ReviewResults> call, @NotNull Response<ReviewResults> response) {
            Log.i(this.getClass().getName(), "Request was successful!");

            if (response.isSuccessful()) {
                data.setValue(response.body());
            }
        }

        @Override
        public void onFailure(@NotNull Call<ReviewResults> call, @NotNull Throwable t) {
            Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
            data.setValue(new ReviewResults());
        }
    }
}
