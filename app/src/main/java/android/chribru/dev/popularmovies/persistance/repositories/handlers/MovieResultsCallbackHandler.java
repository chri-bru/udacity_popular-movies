package android.chribru.dev.popularmovies.persistance.repositories.handlers;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback handler for handling async requests via Retrofit
 */
public class MovieResultsCallbackHandler implements Callback<MovieResults> {

    private final MutableLiveData data;

    public MovieResultsCallbackHandler(MutableLiveData<MovieResults> data) {
        this.data = data;

    }

    @Override
    public void onResponse(@NotNull Call<MovieResults> call, @NotNull Response<MovieResults> response) {
        Log.i(this.getClass().getName(), "Request was successful!");

        if (response.isSuccessful()) {
            data.setValue(response.body());
        }
    }

    @Override
    public void onFailure(@NotNull Call<MovieResults> call, @NotNull Throwable t) {
        Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
        data.setValue(new MovieResults());
    }
}
