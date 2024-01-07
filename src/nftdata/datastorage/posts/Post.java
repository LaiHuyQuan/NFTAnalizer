package nftdata.datastorage.posts;

public class Post {
    private String author;
    private String date;
    private String hashtag;

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getHashtag() {
        return hashtag;
    }
    public Post(String author, String date, String hashtag) {
        this.author = author;
        this.date = date;
        this.hashtag = hashtag;
    }
}
