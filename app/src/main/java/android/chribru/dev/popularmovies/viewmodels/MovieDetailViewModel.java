package android.chribru.dev.popularmovies.viewmodels;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.repositories.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private final MovieRepository repository;

    private LiveData<Movie> movie;

    public MovieDetailViewModel() {
        this.repository = MovieRepository.getInstance();
    }

    public LiveData<Movie> getMovieDetails(int id) {
        if (movie == null) {
            fetchMovieDetails(id);
        }
        return movie;
    }

    private void fetchMovieDetails(int id) {
        movie = repository.getMovieDetails(id);
    }
}
