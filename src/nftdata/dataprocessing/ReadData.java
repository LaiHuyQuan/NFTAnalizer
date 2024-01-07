package nftdata.dataprocessing;

import nftdata.datastorage.nft.BinanceNFT;
import nftdata.datastorage.nft.OpenseaNFT;
import nftdata.datastorage.nft.RaribleNFT;
import nftdata.datastorage.posts.Cointelegraph;
import nftdata.datastorage.posts.Decrypt;
import nftdata.datastorage.posts.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

import static nftdata.dataprocessing.Database.*;

public class ReadData {
    private static final String DATA_PATH = "datacollection/";
    public static void readRaribleData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "rarible.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                // Lấy thông tin từ mỗi đối tượng
                String rank = (String) jsonObject.get("rank");
                String collection = (String) jsonObject.get("collection");
                String volume = (String) jsonObject.get("volume");
                String volumeChange = (String) jsonObject.get("volume change");
                String floorPrice = (String) jsonObject.get("floor price");
                String floorChange = (String) jsonObject.get("floor change");
                String owners = (String) jsonObject.get("owners");
                String items = (String) jsonObject.get("items");

                RaribleNFT newRNFT = new RaribleNFT(rank, collection, volume, volumeChange, floorPrice, floorChange, owners, items);
                itemsRaribleNFT.add(newRNFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readBinanceData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "binance.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String rank = (String) jsonObject.get("rank");
                String collection = (String) jsonObject.get("collection");
                String volume = (String) jsonObject.get("volume");
                String volumeChange = (String) jsonObject.get("volume change");
                String floorPrice = (String) jsonObject.get("floor price");
                String floorChange = (String) jsonObject.get("floor change");
                String owners = (String) jsonObject.get("owners");
                String items = (String) jsonObject.get("items");

                BinanceNFT newBNFT = new BinanceNFT(rank, collection, volume, volumeChange, floorPrice, floorChange, owners, items);
                itemsBinanceNFT.add(newBNFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readOpenseaData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "opensea.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String rank = (String) jsonObject.get("rank");
                String collection = (String) jsonObject.get("collection");
                String volume = (String) jsonObject.get("volume");
                String volumeChange = (String) jsonObject.get("volume change");
                String floorPrice = (String) jsonObject.get("floor price");
                String sales = (String) jsonObject.get("sales");
                String owners = (String) jsonObject.get("owners");
                String items = (String) jsonObject.get("items");
                OpenseaNFT newONFT = new OpenseaNFT(rank, collection, volume, volumeChange, floorPrice, sales, owners, items);
                itemsOpenseaNFT.add(newONFT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readTweetData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "twitter.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String author = (String) jsonObject.get("author");
                String date = (String) jsonObject.get("date");
                String replies = (String) jsonObject.get("replies");
                String reposts = (String) jsonObject.get("reposts");
                String likes = (String) jsonObject.get("likes");
                String views = (String) jsonObject.get("views");
                String hashtags = (String) jsonObject.get("hashtags");
                Tweet newTweet = new Tweet(author, date, hashtags, replies, reposts, likes, views);
                itemsTwitter.add(newTweet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDecryptData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "decrypt.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String title = (String) jsonObject.get("title");
                String author = (String) jsonObject.get("author");
                String date = (String) jsonObject.get("date");
                String tags = (String) jsonObject.get("tags");
                Decrypt newDecrypt = new Decrypt(author, date, tags, title);
                itemsDecrypt.add(newDecrypt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCointelegraphData() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(DATA_PATH + "cointelegraph.json")) {
            // Đọc và parse file JSON thành một đối tượng JSONArray
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            // Lặp qua mỗi đối tượng trong mảng JSON
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();
                String title = (String) jsonObject.get("title");
                String author = (String) jsonObject.get("author");
                String date = (String) jsonObject.get("date");
                String tags = (String) jsonObject.get("tags");
                String view = (String) jsonObject.get("views");
                Cointelegraph newCointelegraph = new Cointelegraph(author, date, tags, title, view);
                itemsCointelegraph.add(newCointelegraph);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
