package pl.com.aay.Engine;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSeleniumEngine extends pl.com.aay.seleniumEngine.SeleniumEngine {

	public AdvancedSeleniumEngine (WebDriver driver){
		super(driver);
	}
	
	// R�norode elementy stron
	public void checkBox (WebElement checkBox, String  element){
		WebElement checkBoxElement = checkBox.findElement(By.xpath("(//input[@type='checkbox'])["+element+"]"));
		checkBoxElement.click();
	}
	
	public void closeModalWindow (WebElement window) throws InterruptedException{
		Thread.sleep(5000);
		//wait.waitForElementToBeVisible(driver, window, 5);
		WebElement closeButton = window.findElement(By.xpath("//p[text()='Close']"));
		closeButton.click();
	}
	
	public void closeModalWindowByLocator (String by, String lokator) throws InterruptedException{
		//Thread.sleep(5000);
		WebElement closeButton = this.findElement(by, lokator);
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), closeButton, 5);
		closeButton.click();
	}
	
	public void addElement (){
		WebElement addButton = findElement("xpath", "//button[@onclick='addElement()']");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), addButton, 3);
		addButton.click();
		WebElement deletedButton = findElement("xpath", "//button[@onclick='deleteElement()']");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), deletedButton, 3);
	}
	
	public void removeElement () {
		addElement();
		WebElement deletedButton = findElement("xpath", "//button[@onclick='deleteElement()']");
		getWaitForElement().waitForElementToBeInvisible(getWebDriver(), deletedButton, 3);
		deletedButton.click();
	}
	
	public void disabledInput (){
		pl.com.aay.seleniumEngine.JsExecutor js = new pl.com.aay.seleniumEngine.JsExecutor(getWebDriver());
		getWaitForElement().waitForBrowserReady(getWebDriver(), 5);
		WebElement input = findElement("xpath", "//input[@type='number']");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), input, 5);
		js.executeScript("arguments[0].disabled = true;", input);
	}
	
	public void enabledInput (){
		pl.com.aay.seleniumEngine.JsExecutor js = new pl.com.aay.seleniumEngine.JsExecutor(getWebDriver());
		getWaitForElement().waitForBrowserReady(getWebDriver(), 5);
		WebElement input = findElement("xpath", "//input[@type='number']");
		js.executeScript("arguments[0].disabled = false;", input);
	}
	
	public void floatingMenu  (){
		scrollPage();
		WebElement home = findElement("xpath", "//a[@href='#home']");
		WebElement news = findElement("xpath", "//a[@href='#news']");
		WebElement contact = findElement("xpath", "//a[@href='#contact']");
		WebElement about = findElement("xpath", "//a[@href='#about']");
		
		boolean homeDisplay = home.isDisplayed();
		boolean newsDisplay = news.isDisplayed();
		boolean contactDisplay = contact.isDisplayed();
		boolean aboutDisplay = about.isDisplayed();
		
		if (!homeDisplay){
			throw new ElementNotVisibleException("Element "+home+"nie jest wdoczny");
		}
		
		if (!newsDisplay){
			throw new ElementNotVisibleException("Element "+newsDisplay+"nie jest wdoczny");
		}
		
		if (!contactDisplay){
			throw new ElementNotVisibleException("Element "+contactDisplay+"nie jest wdoczny");
		}
		
		if (!aboutDisplay){
			throw new ElementNotVisibleException("Element "+aboutDisplay+"nie jest wdoczny");
		}
	}
	
	public void scrollPage (){
		pl.com.aay.seleniumEngine.JsExecutor js = new pl.com.aay.seleniumEngine.JsExecutor(getWebDriver());
		js.scrollPageJs("300");

	}
	
	public void toIFrame (){
		
		WebElement frame = findElement("xpath", "//iframe[@id='mce_0_ifr']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement p = findElement("xpath", "//body/p");
			getWaitForElement().waitForElementToBeClickable(getWebDriver(), p, 3);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
	}
	
	public void hoverElement (){
		checkMoveTo("xpath", "(//img[@alt='User Avatar'])[1]");
		WebElement user = findElement("xpath", "//h5[contains(text(),'name: user1')]");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), user, 3);
		user.click();
	}
	
	public void alertAccept (){
		WebElement alertButton = findElement("xpath", "//button[@onclick='jsAlert()']");
		alertButton.click();
		Alert alert = getWaitForElement().waitForAlert(getWebDriver(), 5);
		alert.accept();
	}
	
	public void alertConfirm (){
		WebElement alertButton = findElement("xpath", "//button[@onclick='jsConfirm()']");
		alertButton.click();
		Alert alert = getWaitForElement().waitForAlert(getWebDriver(), 5);
		alert.dismiss();
	}
	
	public void alertPrompt (){
		WebElement alertButton = findElement("xpath", "//button[@onclick='jsPrompt()']");
		alertButton.click();
		Alert alert = getWaitForElement().waitForAlert(getWebDriver(), 5);
		alert.sendKeys("TEST");
		alert.accept();
	}
	
	public void newBrowserTab (){
		WebElement newTab = findElement("xpath", "//a[contains(text(),'Click Here')]");
		newTab.click();
		String Tab1 = getWebDriver().getWindowHandle();
		ArrayList<String> availableWindows = new ArrayList<String>(getWebDriver().getWindowHandles());
		if (!availableWindows.isEmpty()) { 
			getWebDriver().switchTo().window(availableWindows.get(0));
			getWebDriver().switchTo().window(availableWindows.get(1));
		}
	}
	
	public void keyAction (){
		WebElement key = findElement("id", "target");
		getAction().keyDown(key,Keys.LEFT_CONTROL).build().perform();
		getAction().keyDown(key,Keys.LEFT_CONTROL).build().perform();
	}
	
	public void dynamicLoading (){
		getWaitForElement().waitForElementAttachDOM(getWebDriver(), "//a[text()='Example 1: Element on page that is hidden']", 10);
		WebElement example1 = findElement("xpath", "//a[text()='Example 1: Element on page that is hidden']");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), example1, 5);
		example1.click();
		WebElement header = findElement("xpath", "//h3[contains(text(),'Dynamically Loaded Page Elements')]");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), example1, 5);
		WebElement start = findElement("xpath", "//div[@id='start']/button");
		start.click();
		WebElement loading = findElement("xpath", "//div[@id='loading']");
		getWaitForElement().waitForElementToBeVisible (getWebDriver(), loading, 5);
		getWaitForElement().waitForElementAttachDOM(getWebDriver(), "//h4[contains(text(),'Hello World!')]", 3);
		WebElement hellowWord = findElement("xpath", "//h4[contains(text(),'Hello World!')]");
		getWaitForElement().waitForElementToBeVisible (getWebDriver(), hellowWord, 15);
		hellowWord.click();

	}
	
	public void searchPage (){
		WebElement input =  findElement("id", "input1");
		input.sendKeys("HouseRent");
		WebElement result = findElement("xpath", "//table/tbody/tr[1]/td[4]");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), result, 3);
		System.out.println("Result "+result.getText());
	}
	
	public void loginToWayToAutomation (){
		WebElement signin = findElement("xpath", "//div[@id='load_box']/..//a");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), signin, 3);
		signin.click();
		WebElement login = findElement("xpath", "//div[@id='login']/..//input[@name='username']");
		WebElement password = findElement("xpath", "//div[@id='login']/..//input[@name='password']");
		WebElement submit = findElement("xpath", "//div[@id='login']/..//input[@type='submit']");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), login, 3);
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), password, 3);
		
		login.sendKeys("maniek");
		password.sendKeys("maniek");
		submit.click();
	}
	
	public void dynamicHeader(){
		
		WebElement frame = findElement("xpath", "//iframe[@class='demo-frame' and @src='accordion/defult1.html']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement section2 = findElement("xpath", "//h3[contains(text(),'Section 2')]");
			section2.click();
			WebElement textSection2 = findElement("xpath", "//p[contains(text(),'Sed non urna')]");
			getWaitForElement().waitForElementToBeVisible(getWebDriver(), textSection2, 3);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
	}
	
	public void tooltip (){
		WebElement frame = findElement("xpath", "//iframe[@class='demo-frame' and @src='tooltip/defult1.html']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement input = findElement("id", "age");
			getWaitForElement().waitForElementToBeClickable(getWebDriver(), input, 3);
			System.out.println("title" + input.getAttribute("title"));
			input.click();
			System.out.println("title" + input.getAttribute("title"));
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
	}
	// TODO
	public void authorisationWindow (){
		getWebDriver().switchTo().alert();
		WebElement userName = findElement("xpath", "");
		WebElement password = findElement("xpath", "");
		
		userName.sendKeys("user");
		password.sendKeys("has�o");

		getWebDriver().switchTo().alert().accept();
		getWebDriver().switchTo().defaultContent();
	}
	
	public void slider (){
		WebElement slider = getWebDriver().findElement(By.xpath("//input"));
		dragAndDrop(slider);
	}
	
	public void getFrameAttribute (){
		
		WebElement frameLeft = findElement("name", "frame-left");
		WebElement frameMiddle = findElement("name", "frame-middle");
		WebElement frameRight = findElement("name", "frame-right");
		
		System.out.println("Left frame body "+ goToFrame (frameLeft));
		System.out.println("Left frame body "+ goToFrame (frameMiddle));
		System.out.println("Left frame body "+ goToFrame (frameRight));
		
	}
	
	public String goToFrame (WebElement frame){
		String frameBody="";
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement body = findElement("tagname", "body");
			frameBody = body.getText();
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
		return frameBody;
	}
	
	public void senKeyToIFrame (){
		
		WebElement frame = findElement("xpath", "//iframe[@id='mce_0_ifr']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement p = findElement("xpath", "//body/p");
			p.clear();
			p.sendKeys("TEST IFRAME");
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
	}		
	
	public void subMenu (){
		WebElement enabled = findElement("xpath", "//a[contains(text(),'Enabled')]");
		WebElement downloads = findElement("xpath", "//a[contains(text(),'Downloads')]");
		WebElement pdf = findElement("xpath", "//a[contains(text(),'PDF')]");

		getWaitForElement().waitForElementToBeVisible(getWebDriver(), enabled, 3);
		moveTo(enabled);
		enabled.click();
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), downloads, 3);
		moveTo(downloads);
		downloads.click();
		downloads.click();
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), pdf, 3);
		moveTo(pdf);
	}
	
	
	public void notificationMessages (){
		WebElement messagesButton =  findElement("xpath", "//a[text()='Click here']");
		messagesButton.click();
		WebElement messages =  findElement("id", "flash");
		getWaitForElement().waitForElementToBeVisible(getWebDriver(), messages, 3);
		System.out.println("Notification text: "+messages.getText());
	}	
	
	public void autocomplete (){
		String searchText = "Java";
		WebElement frame = findElement("xpath", "//iframe[@class='demo-frame' and @src='autocomplete/defult1.html']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement tags = findElement("id", "tags");
			getWaitForElement().waitForElementToBeClickable(getWebDriver(), tags, 3);
			tags.sendKeys(searchText);
			getWaitForElement().waitForElementAttachDOM(getWebDriver(), "//ul[@id='ui-id-1']/li[contains(text(),'"+searchText+"')][1]", 5);
			WebElement tips = findElement("xpath", "//ul[@id='ui-id-1']/li[contains(text(),'"+searchText+"')][1]");
			getWaitForElement().waitForElementToBeClickable(getWebDriver(), tips, 3);
			tips.click();
		} 
		catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
		getWebDriver().switchTo().defaultContent();
	}
	
	public void uploadFile (){
		WebElement upload = findElement("xpath", "//input[@id='file-upload']");
		String path= "C:\\Users\\mariusz\\Desktop\\TestTest.java";
		upload.sendKeys(path);
		WebElement uploadButton = findElement("xpath", "//input[@type='submit']");
		uploadButton.click();
	}
	
	public void downloadFile (){
		WebElement download = findElement("xpath", "//a[@href='download/TestTest.java']");
		download.click();
		getAction().keyDown(Keys.ENTER).build().perform();
	}
	
	public void resizable (){
		WebElement frame = findElement("xpath", "(//div[@class='freme_box']/iframe)[1]");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement resizableElement = findElement("xpath", "//div[@class='ui-resizable-handle ui-resizable-e']");
			dragAndDrop(resizableElement, 100, 0);
		}
		catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
	}
	
	public void selectable (){
		WebElement frame = findElement("xpath", "(//div[@class='freme_box']/iframe)[1]");
		
		try {
			getWebDriver().switchTo().frame(frame);
			WebElement element1 = findElement("xpath", "//li[text()='Item 1']");
			WebElement element3 = findElement("xpath", "//li[text()='Item 2']");
			WebElement element6 = findElement("xpath", "//li[text()='Item 3']");
			WebElement element7 = findElement("xpath", "//li[text()='Item 4']");
			
			getAction().keyDown(Keys.LEFT_CONTROL).build().perform();
			element1.click();
			element3.click();
			element6.click();
			element7.click();
		    getAction().keyDown(Keys.LEFT_CONTROL).build().perform();
		}
		catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
	}
	
	public void sortable (){
		WebElement frame = findElement("xpath", "//iframe[@src='sortable/default.html']");
		
		try {
			getWebDriver().switchTo().frame(frame);
			List<WebElement> sortList = findElements("xpath", "//ul[@id='sortable']/li");
			for (WebElement el : sortList){
				dragAndDrop(el, 0, 300);
			}
		}
		catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with element " + frame + e.getStackTrace());
		}
	}
	
	public void login (pl.com.aay.test_data.model.Login loginData){
		
		openWebSite(loginData.getUrl());
		
		WebElement signin = findElement("xpath", "//div[@id='load_box']/..//a");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), signin, 3);
		signin.click();
		WebElement login = findElement("xpath", "//div[@id='login']/..//input[@name='username']");
		WebElement password = findElement("xpath", "//div[@id='login']/..//input[@name='password']");
		WebElement submit = findElement("xpath", "//div[@id='login']/..//input[@type='submit']");
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), login, 3);
		getWaitForElement().waitForElementToBeClickable(getWebDriver(), password, 3);
		
		login.sendKeys(loginData.getLogin());
		password.sendKeys(loginData.getPassword());
		
		submit.click();
		
		openWebSite(loginData.getUrl());
	}
	
	public void registration (pl.com.aay.testData.model.Registration registration){
		WebElement name =findElement("xpath", "//input[@name='name']");
		name.clear();
		name.sendKeys(registration.firstName);
		
		WebElement lastName = findElement("xpath", "(//input[@type='text'])[2]");
		lastName.clear();
		lastName.sendKeys(registration.lastName);
		
		WebElement maritalStatus = findElement("xpath", "//label[contains(text(),'"+registration.maritalStatus+"')]");
		maritalStatus.click();
		
		WebElement hobby = findElement("xpath", "//label[contains(text(),'"+registration.hobby+"')]");
		hobby.click();
		
		//country
		selectByValue("xpath", "(//select)[1]", registration.country);
		
		//month
		selectByValue("xpath", "(//select)[2]", registration.month);
		
		//day
		selectByValue("xpath", "(//select)[3]", registration.day);
		
		//year
		selectByValue("xpath", "(//select)[4]", registration.year);
		
		WebElement phone =findElement("xpath", "//input[@name='phone']");
		phone.clear();
		phone.sendKeys(registration.phoneNumber);
		
		WebElement userName =findElement("xpath", "//input[@name='username']");
		userName.clear();
		userName.sendKeys(registration.userName);
		
		WebElement email =findElement("xpath", "//input[@name='email']");
		email.clear();
		email.sendKeys(registration.email);
		
		WebElement about =findElement("xpath", "//textarea");
		about.clear();
		about.sendKeys(registration.about);
		
		WebElement password =findElement("xpath", "//input[@name='password']");
		password.clear();
		password.sendKeys(registration.password);
		
		WebElement passwordConfirm =findElement("xpath", "//input[@name='c_password']");
		passwordConfirm.clear();
		passwordConfirm.sendKeys(registration.confirmPassword);

	}
	
}
