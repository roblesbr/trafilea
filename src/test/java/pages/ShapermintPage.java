package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;

public class ShapermintPage {

    public class shapermintPage extends BasePage {
        public void navigateToShapermint() {
        }

        public void main(String[] args) {
            shapermintPage shapermintPage = new shapermintPage();
            shapermintPage.navigateToShapermint();
            shapermintPage.driver.quit();
        }
    }

}
