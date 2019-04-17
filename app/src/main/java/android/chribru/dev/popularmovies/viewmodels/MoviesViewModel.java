package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MoviesViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    public MoviesViewModel(Application application) {
        super(application);
        this.repository = MovieRepository.getInstance(application);
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
