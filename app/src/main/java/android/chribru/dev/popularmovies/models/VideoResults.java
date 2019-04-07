package android.chribru.dev.popularmovies.models;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoResults implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Video> results = null;
    public final static Parcelable.Creator<VideoResults> CREATOR = new Creator<VideoResults>() {


    @SuppressWarnings({
            "unchecked"
    })
    public VideoResults createFromParcel(Parcel in) {
        return new VideoResults(in);
    }

    public VideoResults[] newArray(int size) {
            return (new VideoResults[size]);
        }
    };

    private final static long serialVersionUID = 7972801271982786517L;

    protected VideoResults(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (android.chribru.dev.popularmovies.models.VideoResults.class.getClassLoader()));
    }

    public VideoResults() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
