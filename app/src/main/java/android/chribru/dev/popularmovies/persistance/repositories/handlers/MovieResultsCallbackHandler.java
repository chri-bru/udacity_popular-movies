package android.chribru.dev.popularmovies.persistance.repositories.handlers;

import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback handler for handling async requests via Retrofit
 */
public class MovieResultsCallbackHandler implements Callback<MovieResultsDto> {

    private final MutableLiveData data;

    public MovieResultsCallbackHandler(MutableLiveData<MovieResultsDto> data) {
        this.data = data;

    }

    @Override
    public void onResponse(@NotNull Call<MovieResultsDto> call, @NotNull Response<MovieResultsDto> response) {
        Log.i(this.getClass().getName(), "Request was successful!");

        if (response.isSuccessful()) {
            data.setValue(response.body());
        }
    }

    @Override
    public void onFailure(@NotNull Call<MovieResultsDto> call, @NotNull Throwable t) {
        Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
        data.setValue(new MovieResultsDto());
    }
}
