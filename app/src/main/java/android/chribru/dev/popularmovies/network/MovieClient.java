package android.chribru.dev.popularmovies.network;

import android.chribru.dev.popularmovies.api.TheMovieDbMovieApi;
import android.chribru.dev.popularmovies.models.Results;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieClient {
    TheMovieDbMovieApi movieApi;

    public MovieClient(String apiKey) {
        movieApi = ApiClientBuilder.getBuilder().getClientWithApikey(apiKey).create(TheMovieDbMovieApi.class);
    }

    public void getPopularMovies() {
        Call<Results> call = movieApi.popularMovieList(1);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                Log.d("RESPONSE", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Log.d("RESPONSE", t.getMessage());
            }
        });
    }
}
