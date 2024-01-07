package nftdata.datastorage.nft;

public class OpenseaNFT extends  NFTToken{
    private String sales;

    public String getSales() {
        return sales;
    }

    public OpenseaNFT(String rank, String collection, String volume, String volumeChange, String floorPrice, String sales, String owners, String items) {
        super(rank, collection, volume, volumeChange, floorPrice, owners, items);
        this.sales = sales;
    }
}
