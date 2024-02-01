package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(xpath = "//*")
    public WebElement titlePage;

    @FindBy(xpath = "//textarea[@title='Search']")
    public WebElement searchBar;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
