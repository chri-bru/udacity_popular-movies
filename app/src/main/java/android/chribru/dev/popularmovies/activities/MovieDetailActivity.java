package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.adapters.ReviewAdapter;
import android.chribru.dev.popularmovies.adapters.VideoAdapter;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.databinding.ActivityMovieDetailBinding;
import android.chribru.dev.popularmovies.interfaces.VideoOnClickHandler;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Video;
import android.chribru.dev.popularmovies.viewmodels.MovieDetailViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailActivity extends AppCompatActivity implements VideoOnClickHandler {

    private MovieDetailViewModel viewModel;
    private Movie movie;

    private ActivityMovieDetailBinding binding;
    private ReviewAdapter reviewAdapter;
    private VideoAdapter videoAdapter;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        setUpToolbar();
        handleIntent();
        setUpAdapters();
        setUpFab();

        this.setTitle(null);
    }

    private void handleIntent() {
        Intent intent = getIntent();

        if (intent.hasExtra(Constants.MOVIE_ID_PARCELABLE)) {
            int movieId = intent.getIntExtra(Constants.MOVIE_ID_PARCELABLE, 0);
            isFavorite = intent.getBooleanExtra(Constants.MOVIE_FAV_PARCELABLE, false);
            getMovieDetails(movieId);
            getVideos(movieId);
            getReviews(movieId);
        }
    }

    private void setUpAdapters() {
        // set adapters
        // review
        binding.rvReviews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        reviewAdapter = new ReviewAdapter(this);
        binding.rvReviews.setAdapter(reviewAdapter);

        // videos
        binding.rvTrailers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        videoAdapter = new VideoAdapter(this, this);
        binding.rvTrailers.setAdapter(videoAdapter);
    }

    private void setUpToolbar() {
        // set toolbar
        binding.detailToolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);
        this.setSupportActionBar(binding.detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpFab() {
        binding.favoriteFab.setOnClickListener(v -> {
            if (movie == null) {
                return;
            }

            if (movie.getUserFavorite()) {
                removeFromFavorites();
            } else {
                addToFavorites();
            }

            toggleFabIcon();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.MOVIE_PARCELABLE, movie.getId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int id = savedInstanceState.getInt(Constants.MOVIE_PARCELABLE);
        getMovieDetails(id);
    }

    private void getMovieDetails(int id) {
        viewModel.getMovieDetails(id).observe(this, movie1 -> {
                movie = movie1;
                movie.setUserFavorite(isFavorite);
                binding.setMovie(movie);
                populateUi();
            }
        );
    }

    private void getVideos(int id) {
        String locale = Locale.getDefault().getLanguage();
        viewModel.getVideos(id, locale).observe(this, videoResults -> {
            videoAdapter.setResults(videoResults);
        });
    }

    private void getReviews(int id) {
        viewModel.getReviews(id, 1).observe(this, reviewResults -> {
            reviewAdapter.setResults(reviewResults);
        });
    }

    private void addToFavorites() {
        movie.setUserFavorite(true);
        viewModel.addToFavorites(movie);
    }

    private void removeFromFavorites() {
        movie.setUserFavorite(false);
        viewModel.removeFromFavorites(movie);
    }

    private void populateUi() {
        if (movie == null) {
            displayErrorMessage();
            return;
        }
        setVisibilityOfOverview(true);

        // set fab image depending on movie favorite status
        toggleFabIcon();
    }

    private void toggleFabIcon() {
        int image = movie.getUserFavorite() ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp;
        binding.favoriteFab.setImageDrawable(getResources().getDrawable(image, this.getTheme()));
    }

    private void displayErrorMessage() {
        setVisibilityOfOverview(false);
    }

    private void setVisibilityOfOverview(boolean visible) {
        if (visible) {
            toggleUiComponentVisibility(View.VISIBLE);
            binding.detailErrorMsg.setVisibility(View.GONE);
        } else {
            toggleUiComponentVisibility(View.GONE);
            binding.detailErrorMsg.setVisibility(View.VISIBLE);
        }
    }

    private void toggleUiComponentVisibility(int visibility) {
        binding.detailErrorMsg.setVisibility(visibility);
        binding.detailTitle.setVisibility(visibility);
        binding.detailReleaseDate.setVisibility(visibility);
        binding.detailLength.setVisibility(visibility);
        binding.detailGenres.setVisibility(visibility);
        binding.detailUserRating.setVisibility(visibility);
        binding.detailDescription.setVisibility(visibility);
        binding.detailBackdropImg.setVisibility(visibility);
        binding.detailPosterImg.setVisibility(visibility);
        binding.detailUserRating.setVisibility(visibility);
    }

    // on click for videos
    @Override
    public void onClick(Video video) {
        Snackbar.make(binding.movieScrollview, video.getName(), Snackbar.LENGTH_LONG);
    }
}
