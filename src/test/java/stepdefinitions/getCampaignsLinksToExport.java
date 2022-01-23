package stepdefinitions;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CampaignsPage;
import stepdefinitions.BaseTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getCampaignsLinksToExport extends BaseTest {
    CampaignsPage campaignsPage;
    List<WebElement> campaignLinks;
    List<WebElement> categoryAttributes;
    List<String> categoryNames;
    List<WebElement> categories;
    List<WebElement> campaigns;
    List<Integer> campaignsSizes;
    ArrayList[][] table;
    @Test
    @Order(1)
    public void click_to_categories_and_get_links() {
        campaignsPage= new CampaignsPage(driver);
        campaignLinks= new ArrayList<>();
        categoryNames= new ArrayList<>();
        categoryAttributes= new ArrayList<>();
        campaignsSizes= new ArrayList<>();
        categoryAttributes= campaignsPage.getCategoryAttributes();
        categories= campaignsPage.getCategories();
        campaigns=campaignsPage.getCampaigns();
        table = new ArrayList[categories.size()][100];
        categoryAttributes.remove(0);
        campaigns.remove(0);
        Integer i=0,j=0;
        for (WebElement category : categories){
            categoryNames.add(category.getText());
            campaignsPage.selectCategory(categoryAttributes.get(i).getAttribute("data-cat")).click();
            for (WebElement campaign : campaigns) {
                if(i==j) {
                    Integer listSize=campaignsPage.selectCampaigns(campaign.getAttribute("data-category")).size();
                    campaignLinks=campaignsPage.selectCampaigns(campaign.getAttribute("data-category"));
                    campaignsSizes.add(listSize);
                    for(Integer k =0;k<listSize;k++){
                        table[j][k] = new ArrayList();
                        table[i][k].add(campaignLinks.get(k).getAttribute("href"));
                    }
                }
                j++;
            }
            j=0;
            i++;
        }
    }

    @Test
    @Order(2)
    public void get_campaigns_links_to_csv() throws IOException {
        String csv = "src/output.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        String [] country = new String[0];

        for(Integer i =0;i<campaigns.size();i++) {
            for(Integer j=0;j<campaignsSizes.get(i);j++) {
                if(table[i][j]!=null) 
                    country = (categoryNames.get(i)+" : "+table[i][j].toString()).split(",");
                    writer.writeNext(country);
            }
        }
        writer.close();
    }
}
