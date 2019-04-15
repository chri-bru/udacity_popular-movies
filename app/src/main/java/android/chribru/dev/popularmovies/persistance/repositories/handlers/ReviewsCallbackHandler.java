package android.chribru.dev.popularmovies.persistance.repositories.handlers;

import android.chribru.dev.popularmovies.models.dto.ReviewResultsDto;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback handler for handling async requests via Retrofit for reviews
 */
public class ReviewsCallbackHandler implements Callback<ReviewResultsDto> {

    private final MutableLiveData<ReviewResultsDto> data;

    public ReviewsCallbackHandler(MutableLiveData<ReviewResultsDto> data) {
        this.data = data;
    }

    @Override
    public void onResponse(@NotNull Call<ReviewResultsDto> call, @NotNull Response<ReviewResultsDto> response) {
        Log.i(this.getClass().getName(), "Request was successful!");

        if (response.isSuccessful()) {
            data.setValue(response.body());
        }
    }

    @Override
    public void onFailure(@NotNull Call<ReviewResultsDto> call, @NotNull Throwable t) {
        Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
        data.setValue(new ReviewResultsDto());
    }
}
