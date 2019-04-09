package android.chribru.dev.popularmovies.viewmodels;

import android.app.Application;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;
import android.chribru.dev.popularmovies.persistance.repositories.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailViewModel extends ViewModel {

    private final MovieRepository repository;

    private LiveData<Movie> movie;
    private LiveData<VideoResults> videos;
    private LiveData<ReviewResults> reviews;

    public MovieDetailViewModel(Application application) {
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
}
