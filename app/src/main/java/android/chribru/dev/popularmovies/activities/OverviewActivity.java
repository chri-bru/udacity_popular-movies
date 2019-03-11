package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.adapters.OverviewAdapter;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.interfaces.OverviewAdapterOnClickHandler;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Results;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.chribru.dev.popularmovies.R;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OverviewActivity extends AppCompatActivity implements OverviewAdapterOnClickHandler {

    // UI bindings
    private RecyclerView rvOverview;

    private MovieClient movieClient;
    private Results results;
    private int activityLabelId;

    private OverviewAdapter adapter;
    private Toolbar toolbar;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        createUiReferences();

        toolbar = findViewById(R.id.overview_toolbar);
        setSupportActionBar(toolbar);

        movieClient = new MovieClient(Constants.API_KEY);

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
        rvOverview.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
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
        Call<Results> call = movieClient.getPopularMovies(page);
        call.enqueue(new MovieCallbackHandler());
        setActivityLabel(R.string.overview_title_popular);
    }

    private void getTopRatedMovies(int page) {
        Call<Results> call = movieClient.getTopRatedMovies(page);
        call.enqueue(new MovieCallbackHandler());
        setActivityLabel(R.string.overview_title_top_rated);
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

    /**
     * Callback handler for handling async requests via Retrofit
     */
    private class MovieCallbackHandler implements Callback<Results> {
        @Override
        public void onResponse(Call<Results> call, Response<Results> response) {
            Log.i(this.getClass().getName(), "Request was successful!");

            if (response.isSuccessful()) {
                setResults(response.body());
            } else {
                displayErrorMessage();
            }
        }

        @Override
        public void onFailure(Call<Results> call, Throwable t) {
            Log.e(this.getClass().getName(), String.format("Request failed: %s", t.getLocalizedMessage()));
            results = new Results();
            displayErrorMessage();
        }
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
