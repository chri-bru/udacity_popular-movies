package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.ReviewResultsDto;
import android.chribru.dev.popularmovies.models.dto.VideoResultsDto;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    private LiveData<MovieDto> movie;
    private LiveData<VideoResultsDto> videos;
    private LiveData<ReviewResultsDto> reviews;

    public MovieDetailViewModel(Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    public LiveData<MovieDto> getMovieDetails(int id) {
        if (movie == null) {
            movie = repository.getMovieDetails(id);
        }
        return movie;
    }

    public LiveData<VideoResultsDto> getVideos(int movieId, String language) {
        if (videos == null) {
            videos = repository.getVideosForMovie(movieId, language);
        }
        return videos;
    }

    public LiveData<ReviewResultsDto> getReviews(int movieId, int page) {
        if (reviews == null) {
            reviews = repository.getReviewsForMovie(movieId, page);
        }
        return reviews;
    }

    public void addToFavorites(MovieDto movieDto) {
        movieDto.setFavorited(true);
        repository.insertToFavorites(movieDto);
    }

    public void removeFromFavorites(MovieDto movieDto) {
        repository.deleteFromFavorites(movieDto);
    }
}
