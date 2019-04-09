package android.chribru.dev.popularmovies.persistance.repositories.handlers;

import android.chribru.dev.popularmovies.models.VideoResults;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback handler for handling async requests via Retrofit for videos
 */
public class VideosCallbackHandler implements Callback<VideoResults> {

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
