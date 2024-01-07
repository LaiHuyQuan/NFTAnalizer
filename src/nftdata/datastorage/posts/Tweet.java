package nftdata.datastorage.posts;

public class Tweet extends Post {
    private String reply;
    private String retweet;
    private String like;
    private String view;

    public String getReply() {
        return reply;
    }

    public String getRetweet() {
        return retweet;
    }

    public String getLike() {
        return like;
    }

    public String getView() {
        return view;
    }

    public Tweet(String author, String date, String hashtag, String reply, String retweet, String like, String view) {
        super(author, date, hashtag);
        this.reply = reply;
        this.retweet = retweet;
        this.like = like;
        this.view = view;
    }
}
