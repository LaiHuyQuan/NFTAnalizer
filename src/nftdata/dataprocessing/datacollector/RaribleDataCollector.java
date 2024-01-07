package nftdata.dataprocessing.datacollector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RaribleDataCollector extends DataCollector{
    public static void raribleDataCollector(){
        WebDriver driver = openBrowser();
        try{
            driver.get("https://rarible.com/explore/all/collections");
            Thread.sleep(8000);

            //Data Collect
            boolean[] visited = new boolean[101];
            JSONArray jsonArray = new JSONArray();
            outer: while(true){
                List<WebElement> raribleElements = driver.findElements(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div/div"));

                for(WebElement raribleElement : raribleElements) {
                    String rank = raribleElement.findElement(By.xpath(".//a/div/div/div[1]/span")).getText();
                    int rankInt = Integer.parseInt(rank);
                    if (rankInt > MAX_ELEMENTS) break outer;
                    if(!visited[rankInt]) {
                        visited[rankInt] = true;
                        String collection = raribleElement.findElement(By.xpath(".//a/div/div/div[2]/a/div/div[2]/span")).getText();
                        String volume = raribleElement.findElement(By.xpath(".//a/div/div/div[5]/span")).getText();
                        String volumeChange = raribleElement.findElement(By.xpath(".//a/div/div/div[6]/span")).getText();
                        String floorPrice = raribleElement.findElement(By.xpath(".//a/div/div/div[3]/span")).getText();
                        String floorChange = raribleElement.findElement(By.xpath(".//a/div/div/div[4]/span")).getText();
                        String owners = valueConvert(raribleElement.findElement(By.xpath(".//a/div/div/div[8]/span")).getText());
                        String items = valueConvert(raribleElement.findElement(By.xpath(".//a/div/div/div[7]/span")).getText());
                        if(floorPrice.equals("—")) floorPrice = "0";
                        if(floorChange.equals("—")) floorChange = "0";
                        if(volumeChange.equals("—")) volumeChange = "0";

                        //JSON Object
                        JSONObject raribleObject = new JSONObject();
                        raribleObject.put("rank", rank);
                        raribleObject.put("collection", collection);
                        raribleObject.put("volume", volume);
                        raribleObject.put("volume change", volumeChange);
                        raribleObject.put("floor price", floorPrice);
                        raribleObject.put("floor change", floorChange);
                        raribleObject.put("owners", owners);
                        raribleObject.put("items", items);

                        jsonArray.add(raribleObject);

                    }
                }
                pixelScrollDown(driver, 1800);
            }

            //Export JSON
            exportJSON(jsonArray, "rarible.json");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }
}
