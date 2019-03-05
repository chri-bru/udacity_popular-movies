package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.utils.TheMoviePathResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initBindings();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(Constants.MOVIE_PARCELABLE);
        } else {
            movie = getIntent().getParcelableExtra(Constants.MOVIE_PARCELABLE);
        }

        setBindings();

        this.setTitle(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.MOVIE_PARCELABLE, movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void initBindings() {
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

    private void setBindings() {
        title.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate());
        // length.setText(movie); // not available
        genres.setText(movie.getGenreIds().toString()); // retrieve names
        userRating.setText(movie.getVoteAverage().toString());
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
}
