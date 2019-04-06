package android.chribru.dev.popularmovies.viewmodels;

import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.repositories.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {

    private final MovieRepository repository;

    private LiveData<Movie> movie;


    public MoviesViewModel() {
        this.repository = MovieRepository.getInstance();
    }

    public LiveData<MovieResults> getPopularMovies(int page) {
        return repository.getPopularMovies(page);
    }

    public LiveData<MovieResults> getTopRatedMoviews(int page) {
        return repository.getTopRatedMovies(page);
    }
}
