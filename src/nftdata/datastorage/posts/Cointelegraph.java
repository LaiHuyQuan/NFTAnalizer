package nftdata.datastorage.posts;

public class Cointelegraph extends Blog{
    private String view;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Cointelegraph(String author, String date, String hashtag, String title, String view) {
        super(author, date, hashtag, title);
        this.view = view;
    }
}
