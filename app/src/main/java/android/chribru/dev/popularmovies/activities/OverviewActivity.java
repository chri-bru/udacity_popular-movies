package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.adapters.OverviewAdapter;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.databinding.ActivityOverviewBinding;
import android.chribru.dev.popularmovies.interfaces.OverviewAdapterOnClickHandler;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.chribru.dev.popularmovies.viewmodels.MoviesViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.chribru.dev.popularmovies.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OverviewActivity extends AppCompatActivity implements OverviewAdapterOnClickHandler {

    private MovieResultsDto movieResultsDto;
    private int activityLabelId;
    private MoviesViewModel viewModel;
    private OverviewAdapter adapter;
    private ActivityOverviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview);

        // set recycler view
        binding.rvOverview.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        adapter = new OverviewAdapter(this, this);
        binding.rvOverview.setAdapter(adapter);

        // set toolbar
        binding.overviewToolbar.inflateMenu(R.menu.overview_menu);
        binding.overviewToolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        if (savedInstanceState == null) {
            populateUi();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.RESULTS_PARCELABLE, movieResultsDto);
        outState.putInt(Constants.ACTIVITY_LABEL, activityLabelId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        MovieResultsDto savedMovieResultsDto = savedInstanceState.getParcelable(Constants.RESULTS_PARCELABLE);
        setMovieResultsDto(savedMovieResultsDto);
        setActivityLabel(savedInstanceState.getInt(Constants.ACTIVITY_LABEL));
    }

    private void populateUi() {
        getPopularMovies(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItem = item.getItemId();

        if (selectedItem == R.id.menu_sorting_popular) {
            getPopularMovies(1);
            return true;
        }

        if (selectedItem == R.id.menu_sorting_top_rated) {
            getTopRatedMovies(1);
            return true;
        }

        if (selectedItem == R.id.menu_favorites) {
            getFavorites();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPopularMovies(int page) {
        viewModel.getPopularMovies(page).observe(this, results -> {
            setMovieResultsDto(results);
            setActivityLabel(R.string.overview_title_popular);
        });
    }

    private void getTopRatedMovies(int page) {
        viewModel.getTopRatedMoviews(page).observe(this, results -> {
            setMovieResultsDto(results);
            setActivityLabel(R.string.overview_title_top_rated);
        });
    }

    private void getFavorites() {
        viewModel.getFavorites().observe(this, results -> {
            setMovieResultsDto(results);
            setActivityLabel(R.string.favorites);
        });
    }

    private void displayErrorMessage() {
        setVisibilityOfOverview(false);
    }

    private void setVisibilityOfOverview(boolean visible) {
        if (visible) {
            binding.rvOverview.setVisibility(View.VISIBLE);
            binding.overviewErrorMsg.setVisibility(View.GONE);
        } else {
            binding.rvOverview.setVisibility(View.GONE);
            binding.overviewErrorMsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(MovieDto movieDto) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID_PARCELABLE, movieDto.getId());
        startActivity(intent);
    }

    private void setActivityLabel(int resourceId) {
        this.activityLabelId = resourceId;
        binding.overviewToolbar.setTitle(getString(activityLabelId));
    }

    private void setMovieResultsDto(MovieResultsDto movieResultsDto) {
        if (movieResultsDto == null || movieResultsDto.getMovieDtos().size() == 0) {
            displayErrorMessage();
            return;
        }

        setVisibilityOfOverview(true);
        adapter.setMovieResultsDto(null);
        this.movieResultsDto = movieResultsDto;
        adapter.setMovieResultsDto(movieResultsDto);
    }
}
