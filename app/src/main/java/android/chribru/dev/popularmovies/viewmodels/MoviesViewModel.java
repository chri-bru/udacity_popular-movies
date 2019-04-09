package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MoviesViewModel extends ViewModel {

    private final MovieRepository repository;

    public MoviesViewModel(Application application) {
        this.repository = new MovieRepository(application);
    }

    public LiveData<MovieResults> getPopularMovies(int page) {
        return repository.getPopularMovies(page);
    }

    public LiveData<MovieResults> getTopRatedMoviews(int page) {
        return repository.getTopRatedMovies(page);
    }
}
