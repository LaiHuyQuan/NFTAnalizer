package nftdata.datastorage.posts;

public class Blog extends Post {
    private String title;

    public String getTitle() {
        return title;
    }

    public Blog(String author,String date, String hashtag,String title) {
        super(author, date, hashtag);
        this.title=title;
    }
}
