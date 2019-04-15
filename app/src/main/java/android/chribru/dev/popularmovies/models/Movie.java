package android.chribru.dev.popularmovies.models;

import java.util.List;

public class Movie {

    private Integer id;

    private String title;

    private List<Genre> genres;

    private List<Review> reviews;

    private List<Video> videos;

    private Boolean hasVideos;

    private Boolean isUserFavourite;

    private Boolean adult;

    private String backdropPath;

    private String description;

    private String posterPath;

    private String releaseDate;

    private Integer runtime;

    private Double voteAverage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Boolean getIsUserFavourite() {
        return isUserFavourite;
    }

    public void setIsUserFavourite(Boolean isUserFavourite) {
        this.isUserFavourite = isUserFavourite;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getHasVideos() {
        return hasVideos;
    }

    public void setHasVideos(Boolean hasVideos) {
        this.hasVideos = hasVideos;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
