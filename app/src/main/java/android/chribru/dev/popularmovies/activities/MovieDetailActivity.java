package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.Genre;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.chribru.dev.popularmovies.utils.TheMoviePathResolver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private MovieClient client;
    private Movie movie;

    // UI bindings
    private TextView title;
    private TextView releaseDate;
    private TextView length;
    private TextView genres;
    private TextView userRating;
    private TextView description;
    private ImageView backdrop;
    private ImageView poster;
    private Toolbar toolbar;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initBindings();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        client = new MovieClient(Constants.API_KEY);

        Intent intent = getIntent();

        if (intent.hasExtra(Constants.MOVIE_ID_PARCELABLE)) {
            int movieId = intent.getIntExtra(Constants.MOVIE_ID_PARCELABLE, 0);
            getMovieDetails(movieId);
        }

        this.setTitle(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MOVIE_PARCELABLE, movie);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movie = savedInstanceState.getParcelable(Constants.MOVIE_PARCELABLE);
    }

    private void initBindings() {
        errorMsg = findViewById(R.id.detail_error_msg);
        toolbar = findViewById(R.id.detail_toolbar);
        title = findViewById(R.id.detail_title);
        releaseDate = findViewById(R.id.detail_release_date);
        length = findViewById(R.id.detail_length);
        genres = findViewById(R.id.detail_genres);
        userRating = findViewById(R.id.detail_user_rating);
        description = findViewById(R.id.detail_description);
        backdrop = findViewById(R.id.detail_backdrop_img);
        poster = findViewById(R.id.detail_poster_img);
    }

    private void getMovieDetails(int id) {
        Call<Movie> call = client.getMovieDetails(id);
        call.enqueue(new MovieDetailActivity.MovieCallbackHandler());
    }

    private void populateUi() {
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        length.setText(getString(R.string.movie_runtime, movie.getRuntime().toString()));

        String genreNames = TextUtils.join(", ", getGenreNames());
        genres.setText(genreNames); // retrieve names

        userRating.setText(movie.getVoteAverage().toString());
        userRating.setVisibility(View.VISIBLE);
        description.setText(movie.getOverview());

        String posterUri = TheMoviePathResolver.getUrl(movie.getPosterPath(), TheMoviePathResolver.SIZE_W154);
        Glide.with(this)
                .load(posterUri)
                .placeholder(R.drawable.ic_img_placeholder)
                .into(poster);

        String backdropUri = TheMoviePathResolver.getUrl(movie.getBackdropPath(), TheMoviePathResolver.SIZE_W500);
        Glide.with(this)
                .load(backdropUri)
                .placeholder(R.drawable.ic_img_placeholder)
                .into(backdrop);
    }

    private List<String> getGenreNames() {
        List<String> result = new ArrayList<>();

        for (Genre genre : movie.getGenres()) {
            result.add(genre.getName());
        }

        return result;
    }

    private void displayErrorMessage() {
        setVisibilityOfOverview(false);
    }

    private void setVisibilityOfOverview(boolean visible) {
        if (visible) {
            toggleUiComponentVisibility(View.VISIBLE);
            errorMsg.setVisibility(View.GONE);
        } else {
            toggleUiComponentVisibility(View.GONE);
            errorMsg.setVisibility(View.VISIBLE);
        }
    }

    private void toggleUiComponentVisibility(int visibility) {
        errorMsg.setVisibility(visibility);
        toolbar.setVisibility(visibility);
        title.setVisibility(visibility);
        releaseDate.setVisibility(visibility);
        length.setVisibility(visibility);
        genres.setVisibility(visibility);
        userRating.setVisibility(visibility);
        description.setVisibility(visibility);
        backdrop.setVisibility(visibility);
        poster.setVisibility(visibility);
    }

    /**
     * Callback handler for handling async requests via Retrofit
     */
    private class MovieCallbackHandler implements Callback<Movie> {
        @Override
        public void onResponse(@NotNull Call<Movie> call, @NotNull Response<Movie> response) {
            Log.i(this.getClass().getName(), "Request was successful!");

            if (response.isSuccessful()) {
                movie = response.body();
                setVisibilityOfOverview(true);
                populateUi();
            } else {
                displayErrorMessage();
            }
        }

        @Override
        public void onFailure(@NotNull Call<Movie> call, @NotNull Throwable t) {
            Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
            movie = new Movie();
        }
    }
}
