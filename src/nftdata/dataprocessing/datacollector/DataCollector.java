package nftdata.dataprocessing.datacollector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCollector {
    public static final String SEARCH_HASHTAG = "#NFT";
    public static final int MAX_ELEMENTS = 100;
    public static final int SCROLL_TURNS = 10;

    //Extract Hashtags
    static String extractHashtags(WebElement postElement) {
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(postElement.getText());
        StringBuilder hashtagsStringBuilder = new StringBuilder();
        while (matcher.find()) {
            String hashtag = matcher.group();
            hashtagsStringBuilder.append(hashtag).append(" ");
        }
        return hashtagsStringBuilder.toString().trim();
    }

    //Scroll to end Page
    static void endPageScrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Scroll by pixels
    static void pixelScrollDown(WebDriver driver, int pixels){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "window.scrollBy(0, arguments[0]);";
        js.executeScript(script, pixels);
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Format Date
    static String dateFormat(String inputDate){
        LocalDate date;
        String outputFormat = "dd/MM/yyyy";
        try {
            date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
        } catch (Exception e1) {
            try {
                date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH));
            } catch (Exception e2){
                date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
            }
        }
        return date.format(DateTimeFormatter.ofPattern(outputFormat));
    }

    //Open Browser
    static WebDriver openBrowser(){
        System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--force-device-scale-factor=1");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--headless");
        WebDriver driver = new EdgeDriver(options);
        return driver;
    }

    //Export JSON
    static void exportJSON(JSONArray jsonArray, String filename){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String formattedJson = gson.toJson(JsonParser.parseString(jsonArray.toString()));
        try (FileWriter fileWriter = new FileWriter("datacollection/" + filename)) {
            fileWriter.write(formattedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Convert value
    static String valueConvert(String input) {
        if(input.isEmpty()){
            return "0";
        }else {
            double result;
            if (input.toUpperCase().endsWith("K")) {
                String numericPart = input.substring(0, input.length() - 1);
                result = Double.parseDouble(numericPart) * 1000;
            } else if (input.toUpperCase().endsWith("M")) {
                String numericPart = input.substring(0, input.length() - 1);
                result = Double.parseDouble(numericPart) * 1e6;
            } else {
                result = Double.parseDouble(input);
            }
            String formatedResult = String.format("%.0f", result);
            return formatedResult;
        }
    }
}
