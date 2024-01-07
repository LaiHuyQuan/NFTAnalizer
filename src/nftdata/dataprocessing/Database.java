package nftdata.dataprocessing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nftdata.dataprocessing.datacollector.*;
import nftdata.datastorage.nft.BinanceNFT;
import nftdata.datastorage.nft.OpenseaNFT;
import nftdata.datastorage.nft.RaribleNFT;
import nftdata.datastorage.posts.Cointelegraph;
import nftdata.datastorage.posts.Decrypt;
import nftdata.datastorage.posts.Tweet;

public class Database {
    public static ObservableList<RaribleNFT> itemsRaribleNFT  = FXCollections.observableArrayList();
    public static ObservableList<BinanceNFT> itemsBinanceNFT  = FXCollections.observableArrayList();
    public static ObservableList<OpenseaNFT> itemsOpenseaNFT   = FXCollections.observableArrayList();
    public static ObservableList<Tweet> itemsTwitter = FXCollections.observableArrayList();
    public static ObservableList<Cointelegraph> itemsCointelegraph  = FXCollections.observableArrayList();
    public static ObservableList<Decrypt> itemsDecrypt  = FXCollections.observableArrayList();

    public static void clearData(String sourceName){
        switch (sourceName.toLowerCase()) {
            case "twitter":
                itemsTwitter.clear();
                break;
            case "decrypt":
                itemsDecrypt.clear();
                break;
            case "cointelegraph":
                itemsCointelegraph.clear();
                break;
            case "opensea":
                itemsOpenseaNFT.clear();
                break;
            case "binance":
                itemsBinanceNFT.clear();
                break;
            case "rarible":
                itemsRaribleNFT.clear();
                break;
        }
    }
}
