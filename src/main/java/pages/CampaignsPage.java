package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CampaignsPage extends BasePage {
    By categorySelector = new By.ByXPath("//ul[contains(@class, 'campPromTab')]/li[not(contains(@class, ' disabled '))]/span");
    By categoryAttributeSelector =new By.ByXPath("//ul[contains(@class, 'campPromTab')]/li[not(contains(@class, ' disabled '))]");
    By campaignSelector = new By.ByCssSelector("section");

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCategories(){return findAll(categorySelector);}
    public List<WebElement> getCategoryAttributes(){return findAll(categoryAttributeSelector);}
    public List<WebElement> getCampaigns(){
        return findAll(campaignSelector);
    }

    public WebElement selectCategory(String categoryName){
            return find(By.xpath("//li[@data-cat='"+categoryName+"']"));
    }
    public List<WebElement> selectCampaigns(String campaignName){
        return findAll(By.xpath("//section[@data-category='"+campaignName+"']/ul/li/a"));
    }
}
