package departments;

public class VisitedLink {
    private String link;
    private String title;
    private boolean available;

    public VisitedLink(String link, String title, boolean available) {
        this.link = link;
        this.title = title;
        this.available = available;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
