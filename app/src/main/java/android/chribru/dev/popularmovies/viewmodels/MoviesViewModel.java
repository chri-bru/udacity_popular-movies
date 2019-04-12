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

    public LiveData<MovieResults> getPopularMovies(int page) {
        return repository.getPopularMovies(page);
    }

    public LiveData<MovieResults> getTopRatedMoviews(int page) {
        return repository.getTopRatedMovies(page);
    }

    public LiveData<MovieResults> getFavorites() {
        LiveData<List<Movie>> movies = repository.getFavorites();
        return Transformations.map(movies, input -> new MovieResults(-1, input, input.size(), -1));
    }
}
