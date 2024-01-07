package nftdata.dataprocessing.datacollector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CointelegraphDataCollector extends DataCollector{
    public static void cointelegraphDataCollector(){
        WebDriver driver = openBrowser();

        String nftTag = "https://cointelegraph.com/tags/nft";
        String bitcoinTag = "https://cointelegraph.com/tags/bitcoin";
        String blockchainTag = "https://cointelegraph.com/tags/blockchain";
        String aiTag = "https://cointelegraph.com/tags/ai";
        String altcoinTag = "https://cointelegraph.com/tags/altcoin";
        String businessTag = "https://cointelegraph.com/tags/business";
        String defiTag = "https://cointelegraph.com/tags/defi";
        String ethereumTag = "https://cointelegraph.com/tags/ethereum";

        JSONArray jsonArray = new JSONArray();

        dataCollect(driver, nftTag, jsonArray, "NFT");
        dataCollect(driver, bitcoinTag, jsonArray, "Bitcoin");
        dataCollect(driver, blockchainTag, jsonArray, "Blockchain");
        dataCollect(driver, aiTag, jsonArray, "AI");
        dataCollect(driver, altcoinTag, jsonArray, "Altcoin");
        dataCollect(driver, businessTag, jsonArray, "Business");
        dataCollect(driver, defiTag, jsonArray, "DeFi");
        dataCollect(driver, ethereumTag, jsonArray, "Ethereum");

        //JSON File Create
        exportJSON(jsonArray, "cointelegraph.json");

        driver.quit();
    }

    private static void dataCollect(WebDriver webDriver, String website, JSONArray array, String tag){
        try{
            //Open Website
            webDriver.get(website);
            Thread.sleep(5000);

            //Data Collect
            List<WebElement> blogElements = webDriver.findElements(By.xpath("//li[@data-testid=\"posts-listing__item\"]"));
            for (WebElement blogElement : blogElements){
                String date = dateFormat(blogElement.findElement(By.xpath(".//time")).getAttribute("datetime"));
                String author = blogElement.findElement(By.xpath(".//p[@class=\"post-card-inline__author\"]")).getText().substring(3);
                String title = blogElement.findElement(By.xpath(".//span[@class=\"post-card-inline__title\"]")).getText();
                String tags = tag;
                String views;
                try {
                    views = blogElement.findElement(By.xpath(".//span[@class=\"post-card-inline__stats-item\"]")).getText().substring(1);
                }catch (org.openqa.selenium.NoSuchElementException e){
                    views = "0";
                }

                JSONObject cointelegraphObject = new JSONObject();
                cointelegraphObject.put("date", date);
                cointelegraphObject.put("author", author);
                cointelegraphObject.put("title", title);
                cointelegraphObject.put("views", views);
                cointelegraphObject.put("tags", tags);
                array.add(cointelegraphObject);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
