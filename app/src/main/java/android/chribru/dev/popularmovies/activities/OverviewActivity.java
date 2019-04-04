package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.adapters.OverviewAdapter;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.interfaces.OverviewAdapterOnClickHandler;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Results;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.chribru.dev.popularmovies.viewmodels.MoviesViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.chribru.dev.popularmovies.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewActivity extends AppCompatActivity implements OverviewAdapterOnClickHandler {

    // UI bindings
    private RecyclerView rvOverview;

    private Results results;
    private int activityLabelId;
    MoviesViewModel viewModel;

    private OverviewAdapter adapter;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        createUiReferences();

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        if (savedInstanceState == null) {
            populateUi();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.RESULTS_PARCELABLE, results);
        outState.putInt(Constants.ACTIVITY_LABEL, activityLabelId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Results savedResults = savedInstanceState.getParcelable(Constants.RESULTS_PARCELABLE);
        setResults(savedResults);
        setActivityLabel(savedInstanceState.getInt(Constants.ACTIVITY_LABEL));
    }

    private void createUiReferences() {
        errorMsg = findViewById(R.id.overview_error_msg);
        rvOverview = findViewById(R.id.rv_overview);
        rvOverview.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
        adapter = new OverviewAdapter(this, this);
        rvOverview.setAdapter(adapter);
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

        return super.onOptionsItemSelected(item);
    }

    private void getPopularMovies(int page) {
        viewModel.getPopularMovies(page).observe(this, results -> {
            setResults(results);
            setActivityLabel(R.string.overview_title_popular);
        });
    }

    private void getTopRatedMovies(int page) {
        viewModel.getTopRatedMoviews(page).observe(this, results -> {
            setResults(results);
            setActivityLabel(R.string.overview_title_top_rated);
        });
    }

    private void displayErrorMessage() {
        setVisibilityOfOverview(false);
    }

    private void setVisibilityOfOverview(boolean visible) {
        if (visible) {
            rvOverview.setVisibility(View.VISIBLE);
            errorMsg.setVisibility(View.GONE);
        } else {
            rvOverview.setVisibility(View.GONE);
            errorMsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID_PARCELABLE, movie.getId());
        startActivity(intent);
    }

    private void setActivityLabel(int resourceId) {
        this.activityLabelId = resourceId;
        this.setTitle(getString(activityLabelId));
    }

    private void setResults(Results results) {
        if (results == null || results.getMovies().size() == 0) {
            displayErrorMessage();
            return;
        }

        setVisibilityOfOverview(true);
        adapter.setResults(null);
        this.results = results;
        adapter.setResults(results);
    }
}
