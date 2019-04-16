package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    public MoviesViewModel(Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    public LiveData<MovieResults> getPopularMovies() {
        return repository.getPopularMovies();
    }

    public LiveData<MovieResults> getTopRatedMoviews() {
        return repository.getTopRatedMovies();
    }

    public LiveData<MovieResults> getFavorites() {
        return repository.getFavorites();
    }
}
