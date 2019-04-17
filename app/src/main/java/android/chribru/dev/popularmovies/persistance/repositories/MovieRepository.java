package android.chribru.dev.popularmovies.persistance.repositories;

import android.app.Application;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.Sorting;
import android.chribru.dev.popularmovies.models.VideoResults;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.chribru.dev.popularmovies.persistance.MovieDao;
import android.chribru.dev.popularmovies.persistance.MovieRoomDatabase;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.MovieCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.MovieResultsCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.ReviewsCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.VideosCallbackHandler;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class MovieRepository {
    // singleton
    private static MovieRepository repository = null;

    // data sources
    private final MovieClient client;
    private final MovieDao movieDao;

    // caching
    private final HashMap<Integer, MutableLiveData<Movie>> movieCache;
    private final HashMap<Sorting, MutableLiveData<MovieResults>> movieResultsCache;
    private final HashMap<Integer, MutableLiveData<VideoResults>> videoResultsCache;
    private final HashMap<Integer, MutableLiveData<ReviewResults>> reviewResultsCache;
    private LiveData<MovieResults> favoritesCache;

    public static MovieRepository getInstance(Application application) {
        if (repository == null) {
            repository = new MovieRepository(application);
        }
        return repository;
    }

    private MovieRepository(Application application) {
        client = new MovieClient();
        MovieRoomDatabase db = MovieRoomDatabase.getInstance(application);
        movieDao = db.movieDao();

        movieCache = new HashMap<>();
        movieResultsCache = new HashMap<>();
        videoResultsCache = new HashMap<>();
        reviewResultsCache = new HashMap<>();
    }

    /**
     * Tries to retrieve the movie details from cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     * @param id    movie ID for TheMovie DB
     */
    public LiveData<Movie> getMovieDetails(int id) {
        MutableLiveData<Movie> cachedData = movieCache.get(id);

        if (cachedData == null) {
            MutableLiveData<Movie> data = new MutableLiveData<>();
            client.getMovieDetails(id).enqueue(new MovieCallbackHandler(data));
            movieCache.put(id, data);
            cachedData = data;
        }
        return cachedData;
    }

    /**
     * Tries to retrieve the list of popular movies from the cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     */
    public LiveData<MovieResults> getPopularMovies() {
        MutableLiveData<MovieResults> cachedData = movieResultsCache.get(Sorting.MostPopular);

        if (cachedData == null) {
            MutableLiveData<MovieResults> data = new MutableLiveData<>();
            client.getPopularMovies().enqueue(new MovieResultsCallbackHandler(data));
            movieResultsCache.put(Sorting.MostPopular, data);
            cachedData = data;
        }
        return cachedData;
    }

    /**
     * Tries to retrieve the list of top rated movies from the cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     */
    public LiveData<MovieResults> getTopRatedMovies() {
        MutableLiveData<MovieResults> cachedData = movieResultsCache.get(Sorting.TopRated);

        if (cachedData == null) {
            final MutableLiveData<MovieResults> data = new MutableLiveData<>();
            client.getTopRatedMovies().enqueue(new MovieResultsCallbackHandler(data));
            movieResultsCache.put(Sorting.TopRated, data);
            cachedData = data;
        }
        return cachedData;
    }

    /**
     * Tries to retrieve the list of videos for a movie from the cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     * @param id        movie ID for TheMovie DB
     * @param language  the language of the videos
     */
    public LiveData<VideoResults> getVideosForMovie(int id, String language) {
        MutableLiveData<VideoResults> cachedData = videoResultsCache.get(id);

        if (cachedData == null) {
            final MutableLiveData<VideoResults> data = new MutableLiveData<>();
            client.getVideosForMovie(id, language).enqueue(new VideosCallbackHandler(data));
            videoResultsCache.put(id, data);
            cachedData = data;
        }

        return cachedData;
    }

    /**
     * Tries to retrieve the list of reviews for a movie from the cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     * @param id    movie ID for TheMovie DB
     */
    public LiveData<ReviewResults> getReviewsForMovie(int id) {
        MutableLiveData<ReviewResults> cachedData = reviewResultsCache.get(id);

        if (cachedData == null) {
            final MutableLiveData<ReviewResults> data = new MutableLiveData<>();
            client.getReviewsForMovie(id).enqueue(new ReviewsCallbackHandler(data));
            reviewResultsCache.put(id, data);
            cachedData = data;
        }

        return cachedData;
    }

    /**
     * Tries to retrieve the list of favorite movies from the cache.
     * If none is available, it's fetched from TheMovie DB
     * and inserted into the cache.
     */
    public LiveData<MovieResults> getFavorites() {
        if (isFavoriteCacheEmpty()) {
            LiveData<List<Movie>> movies =  movieDao.getAllMovies();
            favoritesCache = Transformations.map(movies, MovieResults::new);
        }
        return favoritesCache;
    }

    private boolean isFavoriteCacheEmpty() {
        return (favoritesCache == null ||
                favoritesCache.getValue() == null ||
                favoritesCache.getValue().getMovies() == null ||
                favoritesCache.getValue().getMovies().size() == 0);
    }

    /**
     * Inserts a movie into the favorite list.
     * @param movie the movie to insert
     */
    public void insertToFavorites(Movie movie) {
        movie.setUserFavorite(true);
        if (!isFavoriteCacheEmpty()) {
            favoritesCache.getValue().getMovies().add(movie);
        }
        new InsertAsyncTask(movieDao).execute(movie);
    }

    private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {
        private final MovieDao movieDao;

        InsertAsyncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }

    /**
     * Removes a movie from the favorite list.
     * @param movie the movie to remove
     */
    public void deleteFromFavorites(Movie movie) {
        if (!isFavoriteCacheEmpty()) {
            favoritesCache.getValue().getMovies().remove(movie);
        }
        new DeleteAsysncTask(movieDao).execute(movie);
    }

    private static class DeleteAsysncTask extends AsyncTask<Movie, Void, Void> {
        private final MovieDao movieDao;

        DeleteAsysncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }


}
