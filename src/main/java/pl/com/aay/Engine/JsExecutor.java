package pl.com.aay.seleniumEngine;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsExecutor extends pl.com.aay.seleniumEngine.SeleniumEngine {
		
	private JavascriptExecutor js; 
	
	public JsExecutor (WebDriver driver){
		super(driver);
		js = (JavascriptExecutor) driver;  
	}

	private JavascriptExecutor getJsExecutor (){
		return js;
	}
	
	public void generatePopUp (String message) {
		js.executeScript("alert('hello world');");
	}
	
	public void clickJs(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	public void refreshBrowserJs (){
		js.executeScript("history.go(0)");
	}
	
	public String getInnerTextJs (String element){
		String innerText = "";
		innerText = js.executeScript("return document."+element+".innerText;").toString();
		return innerText;
	}
	
	public String getTitleWebPageJs (){
		String title = "";
		title = js.executeScript("return document.title;").toString();
		return title;
	}
	
	public void scrollPageJs(){
		  js.executeScript("window.scrollBy(0,150)");
	}
	
	public void scrollPageJs(String px){
		  js.executeScript("window.scrollBy(0,"+px+")");
	}
	
	public void scrollElementDisplayJs(WebElement element){
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void hideElementJs (String elementId){
		js.executeScript("document.getElementsById('"+elementId+"').style.display='none'");
	}
	
	public void showElementJs (String elementId){
		js.executeScript("document.getElementsById('"+elementId+"').display='block'");
	}
	
	public void enterTextJs(String elementId, String text){
		js.executeScript("document.getElementById('"+elementId+"').value = '"+text+"';");
	}
	
	public void navigateJs(String url){
		js.executeScript("window.location = '"+url+"'");
	}
	
	public void executeScript (String script, WebElement element){
		js.executeScript(script,element);
	}
}
