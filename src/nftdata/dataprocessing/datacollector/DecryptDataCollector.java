package nftdata.dataprocessing.datacollector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DecryptDataCollector extends DataCollector{
    public static void decryptDataCollector(){
        WebDriver driver = openBrowser();

        try{
            driver.get("https://decrypt.co/news");
            Thread.sleep(5000);

            //Click Button "Load More" for more Data
            WebElement buttonLoadMore = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/main/div/div[3]/div/article/div[2]/button"));
            for (int i = 0; i < SCROLL_TURNS; i++){
                try {
                    buttonLoadMore.click();
                }catch (org.openqa.selenium.ElementNotInteractableException e){
                    WebElement closeButton = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[2]/p/span"));
                    closeButton.click();
                }
                Thread.sleep(5000);
            }
            //Data Collect
            JSONArray jsonArray = new JSONArray();
            List<WebElement> decryptElements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div/div[1]/div/main/div/div[3]/div/article/article"));

            for (WebElement decryptElement : decryptElements){
                String title = decryptElement.findElement(By.xpath(".//div[2]/div/article/div[2]/h3/a/span")).getText();
                String author = decryptElement.findElement(By.xpath(".//div[2]/div/article/div[2]/footer/div/p/span[1]")).getText().replace("by ","").trim();
                String date = dateFormat(decryptElement.findElement(By.xpath(".//div[1]/h4")).getText());
                String tags = decryptElement.findElement(By.xpath(".//div[2]/div/article/div[2]/p[1]")).getText();

                JSONObject decryptObject = new JSONObject();
                decryptObject.put("title", title);
                decryptObject.put("author", author);
                decryptObject.put("date", date);
                decryptObject.put("tags", tags);
                jsonArray.add(decryptObject);
            }

            //Export JSON
            exportJSON(jsonArray, "decrypt.json");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
