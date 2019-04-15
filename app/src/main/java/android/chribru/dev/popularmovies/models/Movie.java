package android.chribru.dev.popularmovies.models;

import android.chribru.dev.popularmovies.models.converters.GenreConverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName="movie_table")
@TypeConverters({GenreConverter.class})
public class Movie
{
    // will be serialized using a type converter
    @Expose
    private List<Genre> genres = null;

    @Expose
    private Boolean isUserFavorite;

    @Expose
    private Boolean adult;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @PrimaryKey(autoGenerate = false)
    private Integer id;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

    /**
     * No args constructor for use in serialization
     *
     */
    @Ignore
    public Movie() {
    }

    /**
     * Constructor with all parameters
     * @param genres list of movie genres
     * @param runtime movie duration
     * @param backdropPath relative path to backdrop image
     * @param voteCount number of votes
     * @param id movie id
     * @param title movie title
     * @param releaseDate release date of movie
     * @param posterPath relative path to poster image
     * @param voteAverage average voting
     * @param adult flag to indicate adult rating
     * @param overview plot synopsis
     */
    public Movie(Boolean adult, String backdropPath, List<Genre> genres, Integer id, String overview, String posterPath, String releaseDate, Integer runtime, String title, Double voteAverage, Integer voteCount) {
        super();
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.id = id;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getUserFavorite() {
        return isUserFavorite == null ? false : isUserFavorite;
    }

    public void setUserFavorite(Boolean favorited) {
        isUserFavorite = favorited;
    }
}

