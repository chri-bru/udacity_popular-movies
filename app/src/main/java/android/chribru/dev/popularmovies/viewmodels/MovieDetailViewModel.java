package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    private LiveData<Movie> movie;
    private LiveData<VideoResults> videos;
    private LiveData<ReviewResults> reviews;

    public MovieDetailViewModel(Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    public LiveData<Movie> getMovieDetails(int id) {
        if (movie == null) {
            movie = repository.getMovieDetails(id);
        }
        return movie;
    }

    public LiveData<VideoResults> getVideos(int movieId, String language) {
        if (videos == null) {
            videos = repository.getVideosForMovie(movieId, language);
        }
        return videos;
    }

    public LiveData<ReviewResults> getReviews(int movieId, int page) {
        if (reviews == null) {
            reviews = repository.getReviewsForMovie(movieId, page);
        }
        return reviews;
    }

    public void addToFavorites(Movie movie) {
        movie.setUserFavorite(true);
        repository.insertToFavorites(movie);
    }

    public void removeFromFavorites(Movie movie) {
        repository.deleteFromFavorites(movie);
    }
}
