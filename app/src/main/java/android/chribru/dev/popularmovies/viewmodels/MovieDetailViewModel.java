package android.chribru.dev.popularmovies.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.chribru.dev.popularmovies.models.Movie;

public class MovieDetailViewModel extends ViewModel {

    private LiveData<Movie> movie;

    public LiveData<Movie> getMovieDetails(int id) {
        if (movie == null) {
            fetchMovieDetails(id);
        }
        return movie;
    }

    private void fetchMovieDetails(int id) {

    }
}
