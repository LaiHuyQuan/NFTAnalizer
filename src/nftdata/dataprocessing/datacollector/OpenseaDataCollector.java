package nftdata.dataprocessing.datacollector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OpenseaDataCollector extends DataCollector{
    public static void openseaDataCollector(){
        WebDriver driver = openBrowser();
        try{
            driver.get("https://opensea.io/rankings");
            Thread.sleep(5000);

            //Data Collect
            boolean[] visited = new boolean[101];
            JSONArray jsonArray = new JSONArray();
            int elementCount = 0;
            while(elementCount < MAX_ELEMENTS){
                List<WebElement> openseaElements = driver.findElements(By.xpath("//*[@id=\"main\"]/main/div/div/div[3]/div/div[4]/div"));

                for(WebElement openseaElement : openseaElements) {
                    String rank = openseaElement.findElement(By.xpath(".//div/a/div[1]/div[1]/span/div")).getText();
                    int rankInt = Integer.parseInt(rank);
                    if(!visited[rankInt]) {
                        visited[rankInt] = true;
                        String collection = openseaElement.findElement(By.xpath(".//div/a/div[1]/div[3]/div/div/span/div")).getText();
                        String volume = openseaElement.findElement(By.xpath(".//div/a/div[2]/div/span/div")).getText();
                        String volumeChange;
                        try {
                            volumeChange = openseaElement.findElement(By.xpath(".//div/a/div[3]/span/div")).getText();
                        } catch (org.openqa.selenium.NoSuchElementException e) {
                            volumeChange = "0";
                        }
                        String floorPrice = openseaElement.findElement(By.xpath(".//div/a/div[4]/div/span/div")).getText();
                        String sales = openseaElement.findElement(By.xpath(".//div/a/div[5]/span/div")).getText();
                        String owners = openseaElement.findElement(By.xpath(".//div/a/div[6]/div[2]/div/span")).getText().replaceAll("[^0-9,]", "").replaceAll(",", "").trim();
                        String items;
                        try{
                            String itemsString = openseaElement.findElement(By.xpath(".//div/a/div[7]/div[2]/div/span")).getText();
                            String[] parts = itemsString.split("\\s+of\\s+");
                            items = (parts[1].replaceAll(",", "")).trim();
                        } catch (org.openqa.selenium.NoSuchElementException e) {
                            items = "0";
                        }
                        if(floorPrice.equals("—")) floorPrice = "0";

                        JSONObject openseaObject = new JSONObject();
                        openseaObject.put("rank", rank);
                        openseaObject.put("collection", collection);
                        openseaObject.put("volume", volume);
                        openseaObject.put("volume change", volumeChange);
                        openseaObject.put("floor price", floorPrice);
                        openseaObject.put("sales", sales);
                        openseaObject.put("owners", owners);
                        openseaObject.put("items", items);
                        jsonArray.add(openseaObject);

                        elementCount++;
                    }
                }
                pixelScrollDown(driver, 700);
            }

            //Export JSON
            exportJSON(jsonArray, "opensea.json");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }


}
