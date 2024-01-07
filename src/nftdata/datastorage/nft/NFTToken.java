package nftdata.datastorage.nft;

public class NFTToken {
    private String rank;
    private String collection;
    private String volume;
    private String volumeChange;
    private String floorPrice;
    private String floorChange;
    private String owners;
    private String items;

    public String getRank() {
        return rank;
    }

    public String getCollection() {
        return collection;
    }

    public String getVolume() {
        return volume;
    }

    public String getVolumeChange() {
        return volumeChange;
    }

    public String getFloorPrice() {
        return floorPrice;
    }

    public String getFloorChange() {
        return floorChange;
    }

    public String getOwners() {
        return owners;
    }

    public String getItems() {
        return items;
    }

    public NFTToken(String rank, String collection, String volume, String volumeChange, String floorPrice, String floorChange, String owners, String items) {
        this.rank = rank;
        this.collection = collection;
        this.volume = volume;
        this.volumeChange = volumeChange;
        this.floorPrice = floorPrice;
        this.floorChange = floorChange;
        this.owners = owners;
        this.items = items;
    }

    public NFTToken(String rank, String collection, String volume, String volumeChange, String floorPrice, String owners, String items) {
        this.rank = rank;
        this.collection = collection;
        this.volume = volume;
        this.volumeChange = volumeChange;
        this.floorPrice = floorPrice;
        this.owners = owners;
        this.items = items;
    }
}
