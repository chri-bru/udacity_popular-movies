package android.chribru.dev.popularmovies.activities;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.data.Constants;
import android.chribru.dev.popularmovies.models.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView sampleText;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        sampleText = findViewById(R.id.tv_sample_text);

        movie = getIntent().getParcelableExtra(Constants.MOVIE_PARCELABLE);
        sampleText.setText(movie.getTitle() + " | " + movie.getOriginalTitle());
    }
}
