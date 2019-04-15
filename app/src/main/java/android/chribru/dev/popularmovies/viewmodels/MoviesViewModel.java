package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class MoviesViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    public MoviesViewModel(Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    public LiveData<MovieResultsDto> getPopularMovies(int page) {
        return repository.getPopularMovies(page);
    }

    public LiveData<MovieResultsDto> getTopRatedMoviews(int page) {
        return repository.getTopRatedMovies(page);
    }

    public LiveData<MovieResultsDto> getFavorites() {
        LiveData<List<MovieDto>> movies = repository.getFavorites();
        return Transformations.map(movies, input -> new MovieResultsDto(-1, input, input.size(), -1));
    }
}
