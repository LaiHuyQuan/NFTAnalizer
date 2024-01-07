package screen.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nftdata.dataprocessing.ReadData;
import nftdata.dataprocessing.datacollector.DataCollector;
import nftdata.datastorage.nft.BinanceNFT;
import nftdata.datastorage.nft.NFTTokenComparator;
import nftdata.datastorage.nft.OpenseaNFT;
import nftdata.datastorage.nft.RaribleNFT;
import nftdata.datastorage.posts.Cointelegraph;
import nftdata.datastorage.posts.Decrypt;
import nftdata.datastorage.posts.PostsComparator;
import nftdata.datastorage.posts.Tweet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static nftdata.dataprocessing.Database.*;

public class MainScreenController {
    private static String curFilter = "Author";
    private static LocalDate curDatePicker;
    private TableView<?> curTable = new TableView<>();

    private FilteredList<Tweet> tweetFilteredList;
    private FilteredList<Decrypt> decryptFilteredList;
    private FilteredList<Cointelegraph> cointelegraphFilteredList;
    private FilteredList<RaribleNFT> raribleNFTFilteredList;
    private FilteredList<OpenseaNFT> openseaNFTFilteredList;
    private FilteredList<BinanceNFT> binanceNFTFilteredList;
    private SortedList<Tweet> tweetSortedList;
    private SortedList<Decrypt> decryptSortedList;
    private SortedList<Cointelegraph> cointelegraphSortedList;
    private SortedList<RaribleNFT> raribleNFTSortedList;
    private SortedList<OpenseaNFT> openseaNFTSortedList;
    private SortedList<BinanceNFT> binanceNFTSortedList;

    public MainScreenController() {
        this.tweetFilteredList = new FilteredList<>(itemsTwitter);
        this.decryptFilteredList = new FilteredList<>(itemsDecrypt);
        this.cointelegraphFilteredList = new FilteredList<>(itemsCointelegraph);
        this.raribleNFTFilteredList = new FilteredList<>(itemsRaribleNFT);
        this.openseaNFTFilteredList = new FilteredList<>(itemsOpenseaNFT);
        this.binanceNFTFilteredList = new FilteredList<>(itemsBinanceNFT);
        //Set up SortedList for ObservableList to enable sorting
        this.tweetSortedList = new SortedList<>(this.tweetFilteredList);
        this.decryptSortedList = new SortedList<>(this.decryptFilteredList);
        this.cointelegraphSortedList = new SortedList<>(this.cointelegraphFilteredList);
        this.raribleNFTSortedList = new SortedList<>(this.raribleNFTFilteredList);
        this.openseaNFTSortedList = new SortedList<>(this.openseaNFTFilteredList);
        this.binanceNFTSortedList = new SortedList<>(this.binanceNFTFilteredList);
    }

    @FXML
    private Button btnDetails;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Cointelegraph, String> colAuthorCointelegraph;

    @FXML
    private TableColumn<Decrypt, String> colAuthorDecrypt;

    @FXML
    private TableColumn<Tweet, String> colAuthorTwitter;

    @FXML
    private TableColumn<BinanceNFT, String> colCollectionBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colCollectionOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colCollectionRarible;

    @FXML
    private TableColumn<Cointelegraph, String> colDateCointelegraph;

    @FXML
    private TableColumn<Decrypt, String> colDateDecrypt;

    @FXML
    private TableColumn<Tweet, String> colDateTwitter;

    @FXML
    private TableColumn<BinanceNFT, String> colFloorChangeBinance;

    @FXML
    private TableColumn<RaribleNFT, String> colFloorChangeRarible;

    @FXML
    private TableColumn<BinanceNFT, String> colFloorPriceBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colFloorPriceOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colFloorPriceRarible;

    @FXML
    private TableColumn<Tweet, String> colHashtagsTwitter;

    @FXML
    private TableColumn<BinanceNFT, String> colItemsBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colItemsOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colItemsRarible;

    @FXML
    private TableColumn<Tweet, String> colLikesTwitter;

    @FXML
    private TableColumn<BinanceNFT, String> colOwnersBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colOwnersOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colOwnersRarible;

    @FXML
    private TableColumn<BinanceNFT, String> colRankBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colRankOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colRankRarible;

    @FXML
    private TableColumn<Tweet, String> colRepliesTwitter;

    @FXML
    private TableColumn<Tweet, String> colRepostsTwitter;

    @FXML
    private TableColumn<OpenseaNFT, String> colSalesOpenSea;

    @FXML
    private TableColumn<Cointelegraph, String> colTagsCointelegraph;

    @FXML
    private TableColumn<Decrypt, String> colTagsDecrypt;

    @FXML
    private TableColumn<Cointelegraph, String> colTitleCointelegraph;

    @FXML
    private TableColumn<Decrypt, String> colTitleDecrypt;

    @FXML
    private TableColumn<Cointelegraph, String> colViewsCointelegraph;

    @FXML
    private TableColumn<Tweet, String> colViewsTwitter;

    @FXML
    private TableColumn<BinanceNFT, String> colVolumeBinance;

    @FXML
    private TableColumn<BinanceNFT, String> colVolumeChangeBinance;

    @FXML
    private TableColumn<OpenseaNFT, String> colVolumeChangeOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colVolumeChangeRarible;

    @FXML
    private TableColumn<OpenseaNFT, String> colVolumeOpenSea;

    @FXML
    private TableColumn<RaribleNFT, String> colVolumeRarible;

    @FXML
    private DatePicker datePicker;

    @FXML
    private MenuButton menuButtonSearch;

    @FXML
    private MenuButton menuButtonSource;

    @FXML
    private MenuButton menuButtonSourceType;

    @FXML
    private MenuItem menuItemAuthor;

    @FXML
    private MenuItem menuItemBinance;

    @FXML
    private MenuItem menuItemCointelegraph;

    @FXML
    private MenuItem menuItemCollection;

    @FXML
    private MenuItem menuItemDate;

    @FXML
    private MenuItem menuItemDecrypt;

    @FXML
    private MenuItem menuItemHashtag;

    @FXML
    private MenuItem menuItemOpenSea;

    @FXML
    private MenuItem menuItemRarible;

    @FXML
    private MenuItem menuItemTitle;

    @FXML
    private MenuItem menuItemTwitter;

    @FXML
    private TableView<BinanceNFT> tblBinance = new TableView<>(binanceNFTFilteredList);

    @FXML
    private TableView<Cointelegraph> tblCointelegraph = new TableView<>(cointelegraphFilteredList);

    @FXML
    private TableView<Decrypt> tblDecrypt = new TableView<>(decryptFilteredList);

    @FXML
    private TableView<OpenseaNFT> tblOpenSea = new TableView<>(openseaNFTFilteredList);

    @FXML
    private TableView<RaribleNFT> tblRarible = new TableView<>(raribleNFTFilteredList);

    @FXML
    private TableView<Tweet> tblTwitter = new TableView<>(tweetFilteredList);

    @FXML
    private TextField tfSearch;

    @FXML
    void btnDetailsPressed(ActionEvent event) {

    }

    @FXML
    void btnUpdatePressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screen/view/UpdateBox.fxml"));
            Parent root = fxmlLoader.load();
            Stage updateBoxStage = new Stage();
            updateBoxStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    updateBoxStage.close();
                }
            });
            updateBoxStage.setScene(new Scene(root));
            updateBoxStage.setTitle("Update");
            UpdateBoxController updateBoxController = fxmlLoader.getController();
            updateBoxController.setSubStage(updateBoxStage);
            updateBoxStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuItemNFTChosse(ActionEvent event) {
        changeSourceType("NFT");
    }

    @FXML
    void menuItemPostBlogChoose(ActionEvent event) {
        changeSourceType("Post/Blog");
    }

    @FXML
    void menuItemAuthorChoose(ActionEvent event) {
        changeFilterBy("Author");
    }

    @FXML
    void menuItemCollectionChoose(ActionEvent event) {
        changeFilterBy("Collection");
    }

    @FXML
    void menuItemDateChoose(ActionEvent event) {
        changeFilterBy("Date");
    }


    @FXML
    void menuItemTitleChoose(ActionEvent event) {
        changeFilterBy("Title");
    }

    @FXML
    void menuItemHashtagChoose(ActionEvent event) {
        changeFilterBy("Hashtag/Tag");
    }

    @FXML
    void menuItemBinanceChoose(ActionEvent event) {
        changeSource("Binance", tblBinance);
    }

    @FXML
    void menuItemCointelegraphChoose(ActionEvent event) {
        changeSource("Cointelegraph", tblCointelegraph);
    }

    @FXML
    void menuItemDecryptChoose(ActionEvent event) {
        changeSource("Decrypt", tblDecrypt);
    }


    @FXML
    void menuItemOpenSeaChoose(ActionEvent event) {
        changeSource("OpenSea", tblOpenSea);
    }

    @FXML
    void menuItemRaribleChoose(ActionEvent event) {
        changeSource("Rarible", tblRarible);
    }

    @FXML
    void menuItemTwitterChoose(ActionEvent event) {
        changeSource("Twitter", tblTwitter);
    }

    @FXML
    public void initialize(){
        //Twitter
        colAuthorTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("author"));
        colDateTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("date"));
        colRepliesTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("reply"));
        colRepostsTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("retweet"));
        colLikesTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("like"));
        colViewsTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("view"));
        colHashtagsTwitter.setCellValueFactory(new PropertyValueFactory<Tweet, String>("hashtag"));
        //Set Comparator for Column
        colDateTwitter.setComparator(new PostsComparator.DateComparator());
        colRepliesTwitter.setComparator(new PostsComparator.numStatComparator());
        colRepostsTwitter.setComparator(new PostsComparator.numStatComparator());
        colLikesTwitter.setComparator(new PostsComparator.numStatComparator());
        colViewsTwitter.setComparator(new PostsComparator.numStatComparator());
        tblTwitter.setItems(tweetSortedList);
        tweetSortedList.comparatorProperty().bind(tblTwitter.comparatorProperty());

        //Cointelegraph
        colDateCointelegraph.setCellValueFactory(new PropertyValueFactory<Cointelegraph, String>("date"));
        colAuthorCointelegraph.setCellValueFactory(new PropertyValueFactory<Cointelegraph, String>("author"));
        colTitleCointelegraph.setCellValueFactory(new PropertyValueFactory<Cointelegraph, String>("title"));
        colViewsCointelegraph.setCellValueFactory(new PropertyValueFactory<Cointelegraph, String>("view"));
        colTagsCointelegraph.setCellValueFactory(new PropertyValueFactory<Cointelegraph, String>("hashtag"));
        //Set Comparator for Column
        colDateCointelegraph.setComparator(new PostsComparator.DateComparator());
        colViewsCointelegraph.setComparator(new PostsComparator.numStatComparator());
        tblCointelegraph.setItems(cointelegraphSortedList);
        cointelegraphSortedList.comparatorProperty().bind(tblCointelegraph.comparatorProperty());

        //Decrypt
        colTitleDecrypt.setCellValueFactory(new PropertyValueFactory<Decrypt, String>("title"));
        colAuthorDecrypt.setCellValueFactory(new PropertyValueFactory<Decrypt, String>("author"));
        colDateDecrypt.setCellValueFactory(new PropertyValueFactory<Decrypt, String>("date"));
        colTagsDecrypt.setCellValueFactory(new PropertyValueFactory<Decrypt, String>("hashtag"));
        //Set Comparator for Column
        colDateDecrypt.setComparator(new PostsComparator.DateComparator());
        tblDecrypt.setItems(decryptSortedList);
        decryptSortedList.comparatorProperty().bind(tblDecrypt.comparatorProperty());

        //BinanceNFT
        colRankBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("rank"));
        colCollectionBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("collection"));
        colVolumeBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("volume"));
        colVolumeChangeBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("volumeChange"));
        colFloorPriceBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("floorPrice"));
        colFloorChangeBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("floorChange"));
        colOwnersBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("owners"));
        colItemsBinance.setCellValueFactory(new PropertyValueFactory<BinanceNFT, String>("items"));
        //Set Comparator for Column
        colRankBinance.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colVolumeBinance.setComparator(new NFTTokenComparator.PriceComparator());
        colVolumeChangeBinance.setComparator(new NFTTokenComparator.ChangeComparator());
        colFloorPriceBinance.setComparator(new NFTTokenComparator.PriceComparator());
        colFloorChangeBinance.setComparator(new NFTTokenComparator.ChangeComparator());
        colOwnersBinance.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colItemsBinance.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        tblBinance.setItems(binanceNFTSortedList);
        binanceNFTSortedList.comparatorProperty().bind(tblBinance.comparatorProperty());
        tblBinance.getSortOrder().add(colRankBinance);
        tblBinance.sort();

        //OpenSeaNFT
        colRankOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("rank"));
        colCollectionOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("collection"));
        colVolumeOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("volume"));
        colVolumeChangeOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("volumeChange"));
        colFloorPriceOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("floorPrice"));
        colSalesOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("sales"));
        colOwnersOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("owners"));
        colItemsOpenSea.setCellValueFactory(new PropertyValueFactory<OpenseaNFT, String>("items"));
        //Set Comparator for Column
        colRankOpenSea.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colVolumeOpenSea.setComparator(new NFTTokenComparator.PriceComparator());
        colVolumeChangeOpenSea.setComparator(new NFTTokenComparator.ChangeComparator());
        colFloorPriceOpenSea.setComparator(new NFTTokenComparator.PriceComparator());
        colSalesOpenSea.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colOwnersOpenSea.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colItemsOpenSea.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        tblOpenSea.setItems(openseaNFTSortedList);
        openseaNFTSortedList.comparatorProperty().bind(tblOpenSea.comparatorProperty());
        tblOpenSea.getSortOrder().add(colRankOpenSea);
        tblOpenSea.sort();

        //RaribleNFT
        colRankRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("rank"));
        colCollectionRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("collection"));
        colVolumeRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("volume"));
        colVolumeChangeRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("volumeChange"));
        colFloorPriceRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("floorPrice"));
        colFloorChangeRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("floorChange"));
        colOwnersRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("owners"));
        colItemsRarible.setCellValueFactory(new PropertyValueFactory<RaribleNFT, String>("items"));
        //Set Comparator for Column
        colRankRarible.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colVolumeRarible.setComparator(new NFTTokenComparator.PriceComparator());
        colVolumeChangeRarible.setComparator(new NFTTokenComparator.ChangeComparator());
        colFloorPriceRarible.setComparator(new NFTTokenComparator.PriceComparator());
        colFloorChangeRarible.setComparator(new NFTTokenComparator.ChangeComparator());
        colOwnersRarible.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        colItemsRarible.setComparator(new NFTTokenComparator.RankSalesOwnersItemsComparator());
        tblRarible.setItems(raribleNFTSortedList);
        raribleNFTSortedList.comparatorProperty().bind(tblRarible.comparatorProperty());
        tblRarible.getSortOrder().add(colRankRarible);
        tblRarible.sort();

        //Searchbox
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredResult(newValue);
            }
        });

        //Date Picker
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            curDatePicker = newValue;
            showFilteredResult("Date");
        });

        //Read data from JSON file
        ReadData.readRaribleData();
        ReadData.readBinanceData();
        ReadData.readOpenseaData();
        ReadData.readTweetData();
        ReadData.readDecryptData();
        ReadData.readCointelegraphData();

        //Other set up
        menuItemTitle.setVisible(false);
    }

    private void changeSource(String sourceName, TableView<?> nextTable){
        menuButtonSource.setText(sourceName);
        curTable.setVisible(false);
        nextTable.setVisible(true);
        curTable = nextTable;
        if(sourceName.equals("Twitter") || menuButtonSourceType.getText().equals("NFT")) menuItemTitle.setVisible(false);
        else menuItemTitle.setVisible(true);
    }

    void showFilteredResult(String string){
        String filterText = string.toLowerCase();
        switch (curFilter){
            case "Author":
                tweetFilteredList.setPredicate(item -> item.getAuthor().toLowerCase().contains(filterText));
                decryptFilteredList.setPredicate(item -> item.getAuthor().toLowerCase().contains(filterText));
                cointelegraphFilteredList.setPredicate(item -> item.getAuthor().toLowerCase().contains(filterText));
                break;
            case "Title":
                decryptFilteredList.setPredicate(item -> item.getTitle().toLowerCase().contains(filterText));
                cointelegraphFilteredList.setPredicate(item -> item.getTitle().toLowerCase().contains(filterText));
                break;
            case "Date":
                String tempFormatDate;
                try {
                    tempFormatDate = curDatePicker.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
                }catch (NullPointerException e){
                    tempFormatDate = "";
                }
                String formatDate =tempFormatDate;
                tweetFilteredList.setPredicate(item -> item.getDate().toLowerCase().contains(formatDate));
                decryptFilteredList.setPredicate(item -> item.getDate().toLowerCase().contains(formatDate));
                cointelegraphFilteredList.setPredicate(item -> item.getDate().toLowerCase().contains(formatDate));
                break;
            case "Hashtag/Tag":
                tweetFilteredList.setPredicate(item -> item.getHashtag().toLowerCase().contains(filterText));
                decryptFilteredList.setPredicate(item -> item.getHashtag().toLowerCase().contains(filterText));
                cointelegraphFilteredList.setPredicate(item -> item.getHashtag().toLowerCase().contains(filterText));
                break;
            case "Collection":
                openseaNFTFilteredList.setPredicate(item -> item.getCollection().toLowerCase().contains(filterText));
                binanceNFTFilteredList.setPredicate(item -> item.getCollection().toLowerCase().contains(filterText));
                raribleNFTFilteredList.setPredicate(item -> item.getCollection().toLowerCase().contains(filterText));
                break;
        }
    }

    private void changeFilterBy(String filter){
        tfSearch.clear();
        datePicker.setValue(null);
        menuButtonSearch.setText(filter);
        if(filter.equals("Date")){
            tfSearch.setVisible(false);
            datePicker.setVisible(true);
        }else{
            tfSearch.setVisible(true);
            datePicker.setVisible(false);
        }
        curFilter = filter;
    }

    private void changeSourceType(String sourceType){
        switch (sourceType){
            case "NFT":
                menuButtonSourceType.setText("NFT");
                menuItemTwitter.setVisible(false);
                menuItemDecrypt.setVisible(false);
                menuItemCointelegraph.setVisible(false);
                menuItemOpenSea.setVisible(true);
                menuItemBinance.setVisible(true);
                menuItemRarible.setVisible(true);
                menuItemHashtag.setVisible(false);
                menuItemAuthor.setVisible(false);
                menuItemTitle.setVisible(false);
                menuItemDate.setVisible(false);
                menuItemCollection.setVisible(true);
                changeSource("OpenSea", tblOpenSea);
                changeFilterBy("Collection");
                break;
            case "Post/Blog":
                menuButtonSourceType.setText("Post/Blog");
                menuItemTwitter.setVisible(true);
                menuItemDecrypt.setVisible(true);
                menuItemCointelegraph.setVisible(true);
                menuItemOpenSea.setVisible(false);
                menuItemBinance.setVisible(false);
                menuItemRarible.setVisible(false);
                menuItemHashtag.setVisible(true);
                menuItemAuthor.setVisible(true);
                menuItemTitle.setVisible(true);
                menuItemDate.setVisible(true);
                menuItemCollection.setVisible(false);
                changeSource("Twitter", tblTwitter);
                changeFilterBy("Author");
                break;
        }
    }
}

