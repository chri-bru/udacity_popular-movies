package android.chribru.dev.popularmovies.models;

public class Genre
{
    private Integer id;
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public Genre() {
    }

    /**
     * Constructor with all parameters
     * @param id genre ide
     * @param name genre name
     */
    public Genre(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}