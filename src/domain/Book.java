package domain;

public class Book {
    private long id;
    private String  title;
    private String  Author;

    public String getAuthor() {return Author;}

    public void setAuthor(String author) {Author = author;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
