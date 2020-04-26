package test;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class testcase extends excelDriven {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		driver = initializeDriver();
		launchbrowser();
		tc01();
		tc02();
		tc03();
		tc04a();
		tc04b();
		tc05();
		tc06();
		tc07();
		tc08();
		tc09();
		tc10();
		tc11();
		tc12();
		tc13();
		tc14();
		tc15();
		tc16();
		tc17();
		tc18();
		tc19();
		tc20();
		tc21();
		tc22();
		tc23();
		tc24();
		tc25();
		tc26();
		tc27();
		tc28();
		tc29();
		tc30();
		tc31();
		tc32();
		tc33();
		tc34();
		tc35();
		tc36();
		tc37();

	}

	public static void launchbrowser() throws IOException {
		String url = getData("Url");
		driver.get(url);
		driver.manage().window().maximize();

	}

	public static WebDriver initializeDriver() throws IOException {
		String projectpath = System.getProperty("user.dir");
		System.out.println(projectpath);
		String browserName = getData("browser");
		System.out.println(browserName);
		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", projectpath + "\\driver\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public static void tc01() throws IOException {
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("password")).clear();
		System.out.println("Password field is cleared");
		driver.findElement(By.id("Login")).click();
		String ErrorMessage = driver.findElement(By.id("error")).getText();
		String Expected = "Please enter your password.";
		if (ErrorMessage.equalsIgnoreCase(Expected)) {
			System.out.println("Error message os as expected");
		} else
			System.out.println("Error message is not as expected");

		System.out.println("**********************************");

		driver.quit();
	}

	public static void tc02() throws IOException, InterruptedException {
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("password")).sendKeys(getData("password"));
		System.out.println("Entered correct password and username");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		System.out.println("Navigated to home page...");
		System.out.println("************************************");
		driver.quit();

	}

	public static void tc03() throws IOException, InterruptedException {
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("password")).sendKeys(getData("password"));
		System.out.println("Entered correct password and username");
		driver.findElement(By.xpath("//input[@id='rememberUn']")).click();
		System.out.println("Clicked Remember Me button");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Thread.sleep(5000);
		WebElement userValidate = driver.findElement(By.id("idcard-identity"));
		String username = userValidate.getText();
		String expectedname = getData("Username");
		if (username.equalsIgnoreCase(expectedname)) {
			System.out.println("Username is displayed as expected");
		} else
			System.out.println("Username is not displayed as expected!!");

		System.out.println("**********************************");
		driver.quit();

	}

	public static void tc04a() throws IOException, InterruptedException {
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("forgot_password_link")).click();
		System.out.println("Clicked on forgot password:");
		driver.findElement(By.id("un")).sendKeys(getData("Username"));
		driver.findElement(By.id("continue")).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//div[@class='message']//p[1]")).getText());
		System.out.println("Email sent successfully!");
		driver.quit();
	}

	public static void tc04b() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("123");
		driver.findElement(By.id("password")).sendKeys("34251");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(4000);
		String ErrorMessage = driver.findElement(By.id("error")).getText();
		String Expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		if (ErrorMessage.equalsIgnoreCase(Expected)) {
			System.out.println("Error message is as expected");
		} else
			System.out.println("Error message is not as expected");

		System.out.println("**********************************");
		driver.quit();

	}

	public static void tc05() throws IOException, InterruptedException {
		login();
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(4000);
		String menu = driver.findElement(By.id("userNav-menuItems")).getText();
		System.out.println(menu);
		boolean flag = false;
		String[] menu1 = menu.split("\n");
		String[] expected = { "My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience",
				"Logout" };
		for (int i = 0; i < menu1.length; i++) {
			if (menu1[i].equals(expected[i])) {
				flag = true;
			} else {
				flag = false;
				System.out.println("Elements are not matching");
			}
		}
		if (flag == true)
			System.out.println("Dropdown is as expected");
		driver.quit();
	}

	public static void tc06() throws IOException, InterruptedException {
		login();
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'My Profile')]")).click();
		Thread.sleep(8000);
		System.out.println("Clicked my profile successfully");
		driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img")).click();
		Thread.sleep(2000);
		System.out.println("Clicked edit profile");
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(1000);
		System.out.println("Switched to popup");
		String contact = driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).getText();
		System.out.println(contact);
		String expected1 = "Contact";
		if (contact.equals(expected1)) {
			System.out.println("Contacts tab is present");
		} else {
			System.out.println("Contacts s not as expected");
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
		Thread.sleep(2000);
		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.clear();
		lastName.sendKeys(getData("name"));
		System.out.println("Changed last name");
		driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']")).click();
		System.out.println("Clicked save all");
		Thread.sleep(3000);
		String newuser = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
		String expected2 = "Anu Jayanthi Shan ";
		if (newuser.equals(expected2)) {
			System.out.println("Username is modified");
		} else {
			System.out.println("Username is not updated");
		}
		WebElement post = driver
				.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(post));
		post.click();
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("/html[1]/body[1]")).sendKeys(getData("post"));
		driver.switchTo().parentFrame();
		driver.findElement(By.name("publishersharebutton")).click();
		Thread.sleep(3000);
		String post1 = driver.findElement(By.xpath("//p[contains(text(),'My very first')]")).getText();
		System.out.println(post1);
		System.out.println("Made first post");
		WebElement file = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		wait.until(ExpectedConditions.elementToBeClickable(file));
		file.click();
		driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']")).click();
		driver.findElement(By.xpath("//input[@id='chatterFile']")).sendKeys(getData("file"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='publishersharebutton']")).click();
		String newfile = driver.findElement(By.xpath("//span[contains(text(),'posted a file.')]")).getText();
		System.out.println(newfile);
		System.out.println("File uploaded");
		Thread.sleep(4000);
		WebElement link1 = driver.findElement(By.xpath("//a[@id='uploadLink']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", link1);
		driver.switchTo().frame("uploadPhotoContentId");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']")).sendKeys(getData("photo"));
		Thread.sleep(5000);
		WebElement link2 = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		executor.executeScript("arguments[0].click();", link2);
		Thread.sleep(4000);
		WebElement crop=driver.findElement(By.xpath("//div[contains(@class,'imgCrop_marqueeVert imgCrop_marqueeWest')]"));
		Actions action=new Actions(driver);
		action.dragAndDropBy(crop,30,220).build().perform();
		driver.findElement(By.id("j_id0:j_id7:save")).click();
		Thread.sleep(8000);
		System.out.println("Photo uploaded");
		driver.quit();
	}

	public static void tc07() throws IOException, Exception {
		login();
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//a[contains(text(),'My Settings')]")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@id='PersonalInfo']//a[@class='header setupFolder']")).click();
		driver.findElement(By.xpath("//span[@id='LoginHistory_font']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]")).click();
		System.out.println("Login history downloaded");
		driver.findElement(By.xpath("//div[@id='PersonalInfo']//a[@class='header setupFolder']")).click();
		driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']")).click();
		System.out.println("Clicked on Display and Layout");
		driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']")).click();
		WebElement custom1 = driver.findElement(By.name("p4"));
		Select sel = new Select(custom1);
		sel.selectByVisibleText("Salesforce Chatter");
		Thread.sleep(4000);
		WebElement available = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		Select sel1 = new Select(available);
		sel1.selectByValue("report");
		driver.findElement(By.xpath("//img[@class='rightArrowIcon']")).click();
		driver.findElement(By.name("save")).click();
		Thread.sleep(4000);
		System.out.println(driver.findElement(By.xpath("//a[contains(text(),'Reports')]")).getText());
		System.out.println("Reports tab added to the links in the page");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='EmailSetup']//a[@class='header setupFolder']")).click();
		driver.findElement(By.xpath("//a[@id='EmailSettings_font']")).click();
		WebElement user1 = driver.findElement(By.id("sender_name"));
		user1.clear();
		user1.sendKeys(getData("fullname"));
		WebElement email = driver.findElement(By.id("sender_email"));
		email.clear();
		email.sendKeys(getData("email"));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@name='save']")).click();
		Thread.sleep(7000);
		System.out.println("Changes made");
		driver.findElement(By.xpath("//div[@id='CalendarAndReminders']//a[@class='header setupFolder']")).click();
		driver.findElement(By.xpath("//a[@id='Reminders_font']")).click();
		driver.findElement(By.xpath("//input[@id='testbtn']")).click();
		Thread.sleep(8000);
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		System.out.println("No of windows:" + getAllWindows.size());
		driver.switchTo().window(getWindow[1]);
		driver.findElement(By.name("dismiss_all")).click();
		System.out.println("Popup window handled");
		driver.quit();

	}

	public static void tc08() throws IOException, Exception {
		login();
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//a[@class='debugLogLink menuButtonMenuLink']")).click();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(3000);
		System.out.println("Developer console window is opened");
		driver.manage().window().maximize();
		driver.close();
		System.out.println("Window is closed");
		driver.switchTo().window(getWindow[0]);
		driver.quit();

	}

	public static void tc09() throws IOException, InterruptedException {
		login();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Thread.sleep(5000);
		System.out.println(driver.getCurrentUrl());
		System.out.println("Navigated to login page");
		driver.quit();

	}

	public static void tc10() throws IOException, InterruptedException {
		login();
		clickonAccounts();
		Thread.sleep(4000);
		driver.findElement(By.name("new")).click();
		driver.findElement(By.xpath("//input[@id='acc2']")).sendKeys("Test Purpose");
		WebElement type = driver.findElement(By.id("acc6"));
		Select sl = new Select(type);
		sl.selectByValue("Technology Partner");
		Thread.sleep(2000);
		WebElement priority = driver.findElement(By.id("00N3h000003Stm8"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", priority);
		Select sl1 = new Select(priority);
		sl1.selectByValue("High");
		driver.findElement(By.name("save")).click();
		Thread.sleep(4000);
		String newacc = driver.findElement(By.xpath("//h2[contains(text(),'Account Detail')]")).getText();
		String expected1 = "Account Detail";
		if (newacc.equals(expected1)) {
			System.out.println("Account created and details are displayed");
		} else
			System.out.println("Account details not diaplayed");
		driver.quit();

	}

	public static void tc11() throws IOException, InterruptedException {
		login();
		clickonAccounts();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		driver.findElement(By.name("fname")).sendKeys(getData("viewname"));
		WebElement viewName = driver.findElement(By.name("devname"));
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(4000);
		driver.findElement(By.name("save")).click();
		String acc = driver.findElement(By.name("fcf")).getText();
		String accarray[] = acc.split("\n");
		for (int i = 0; i < accarray.length; i++) {
			if (accarray[i].equals("New Contract")) {
				System.out.println("New view link created");
				break;

			}
		}
		driver.quit();

	}

	public static void tc12() throws IOException, InterruptedException {
		login();
		clickonAccounts();
		driver.findElement(By.xpath("//span[@class='fFooter']//a[contains(text(),'Edit')]")).click();
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(getData("updateview"));
		System.out.println("Updated view name");
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='fcol1']"));
		Thread.sleep(3000);
		Select name = new Select(dropdown);
		name.selectByVisibleText("Account Name");
		WebElement operator = driver.findElement(By.xpath("//select[@id='fop1']"));
		Thread.sleep(3000);
		Select selop = new Select(operator);
		selop.selectByVisibleText("contains");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='fval1']")).sendKeys("a");
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//select[@id='colselector_select_0']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
		Select available = new Select(element);
		available.selectByVisibleText("Last Modified Date");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@class='rightArrowIcon']")).click();
		driver.findElement(By.name("save")).click();
		Thread.sleep(3000);
		String added = driver.findElement(By.xpath("//div[@title='Last Modified Date']")).getText();
		String expec1 = "Last Modified Date";
		if (added.equals(expec1)) {
			System.out.println("Last modified date added to list");
		}
		driver.quit();

	}

	public static void tc13() throws InterruptedException, IOException {
		login();
		clickonAccounts();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]")).click();
		driver.findElement(By.name("srch")).sendKeys(getData("merge"));
		driver.findElement(By.xpath("//input[contains(@name,'srchbutton')]")).click();
		driver.findElement(By.xpath("//input[@value=' Next ']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value=' Merge ']")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println("Switched to alert");
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.xpath("//a[contains(text(),'Test Purpose')]")).getText());
		System.out.println("Merged account is displayed");
		driver.quit();

	}

	public static void tc14() throws IOException, InterruptedException {
		login();
		clickonAccounts();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@name='dateColumn']")).click();
		System.out.println("Clicked on created date");
		driver.findElement(By.xpath("//div[contains(text(),'Created Date')]")).click();
		driver.findElement(By.xpath("//img[@id='ext-gen152']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Today')]")).click();
		System.out.println("Today is selected in from");
		driver.findElement(By.xpath("//img[@id='ext-gen154']")).click();
		driver.findElements(By.xpath("//button[contains(text(),'Today')]")).get(1).click();
		System.out.println("Today is selected in to");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(text(),'Anu Jayanthi Shan')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		System.out.println("Save button is clicked");
		WebElement name = driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(name));
		name.sendKeys(getData("reportname"));
		System.out.println("Report name is given");
		WebElement unique = driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
		unique.clear();
		unique.sendKeys(getData("repunique"));
		Thread.sleep(4000);
		WebElement save = driver.findElement(By.xpath("//table[@id='dlgSaveAndRun']"));
		wait.until(ExpectedConditions.elementToBeClickable(save));
		save.click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//div[@id='status']")).getText());
		System.out.println("Report name is validated");
		driver.quit();

	}

	public static void tc15() throws IOException, InterruptedException {
		login();
		clickOpportunities();
		String opdd = driver.findElement(By.xpath("//select[@id='fcf']")).getText();
		String[] opdd1 = opdd.split("\n");
		boolean flag = false;
		String[] expected = { "All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities",
				"New Last Week", "New This Week", "Opportunity Pipeline", "Private", "Recently Viewed Opportunities",
				"Won" };
		for (int i = 0; i < opdd1.length; i++) {
			if (opdd1[i].equals(expected[i])) {
				flag = true;
			} else {
				flag = false;
				System.out.println("Elements are not matching");
			}
		}
		if (flag == true)
			System.out.println("Dropdown is as expected");
		driver.quit();
	}

	public static void tc16() throws IOException, InterruptedException {
		login();
		clickOpportunities();
		driver.findElement(By.xpath("//input[@name='new']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("//input[@id='opp3']")).sendKeys(getData("oppname"));
		System.out.println("Entered opportunity name");
		driver.findElement(By.xpath("//img[@class='lookupIcon']")).click();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		System.out.println("No of windows:" + getAllWindows.size());
		driver.switchTo().window(getWindow[1]);
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.switchTo().frame("resultsFrame");
		Thread.sleep(3000);
		WebElement acc = driver.findElement(By.xpath("//a[contains(text(),'ReportTest')]"));
		acc.click();
		driver.switchTo().window(getWindow[0]);
		driver.findElement(By.xpath("//input[@id='opp9']")).click();
		driver.findElement(By.xpath("//td[contains(text(),'31')]")).click();
		System.out.println("Selected date");
		WebElement style1 = driver.findElement(By.xpath("//select[@id='opp11']"));
		Select sel = new Select(style1);
		sel.selectByVisibleText("Needs Analysis");
		System.out.println("Dropdown selected");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@alt='Primary Campaign Source Lookup (New Window)']")).click();
		Thread.sleep(2000);
		Set<String> getAllWindows1 = driver.getWindowHandles();
		String[] getWindow1 = getAllWindows1.toArray(new String[getAllWindows1.size()]);
		// System.out.println("No of windows:"+getAllWindows1.size());
		driver.switchTo().window(getWindow1[1]);
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.switchTo().frame("resultsFrame");
		Thread.sleep(3000);
		WebElement camp = driver.findElement(By.xpath("//a[contains(text(),'AutomationTest')]"));
		camp.click();
		driver.switchTo().window(getWindow[0]);
		System.out.println("Primary campaign details entered");
		driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']")).click();
		Thread.sleep(7000);
		String newop = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		String expected1 = getData("oppname");
		if (newop.equals(expected1)) {
			System.out.println("New opportunity created");
		} else {
			System.out.println("Not as expected");
		}
		driver.quit();

	}

	public static void tc17() throws IOException, InterruptedException {
		login();
		clickOpportunities();
		driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]")).click();
		String header = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		System.out.println(header);
		System.out.println(driver.findElement(By.xpath("//div[@id='status']")).getText());
		String expected1 = "Opportunity Pipeline";
		if (header.equals(expected1)) {
			System.out.println("Opportunity Pipeline is displayed");
		} else {
			System.out.println("Not as expected");
		}
		driver.quit();

	}

	public static void tc18() throws IOException, InterruptedException {
		login();
		clickOpportunities();
		driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]")).click();
		String header = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		System.out.println(header);
		System.out.println(driver.findElement(By.xpath("//div[@id='status']")).getText());
		String expected1 = "Stuck Opportunities";
		if (header.equals(expected1)) {
			System.out.println("Stuck Opportunities page is displayed");
		} else {
			System.out.println("Not as expected");
		}
		driver.quit();
	}

	public static void tc19() throws InterruptedException, IOException {
		login();
		clickOpportunities();
		WebElement opp = driver.findElement(By.id("quarter_q"));
		Select sc = new Select(opp);
		sc.selectByVisibleText("Current and Next FQ");
		System.out.println("Current and NextFQ selected");
		driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']")).click();
		System.out.println("Clicked on Go Button");
		String header = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		System.out.println(header);
		String expected1 = "Opportunity Report";
		if (header.equals(expected1)) {
			System.out.println("Opportunity report page is displayed");
		} else {
			System.out.println("Not as expected");
		}
		WebElement opp1 = driver.findElement(By.id("quarter_q"));
		Select sc1 = new Select(opp1);
		System.out.println(sc1.getFirstSelectedOption().getText());
		System.out.println("Expected quarterly report is generated");
		driver.quit();
	}

	public static void tc20() throws IOException, InterruptedException {
		login();
		clickedLeads();
		String homelead = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		System.out.println(homelead);
		String expected1 = "Home";
		if (homelead.equals(expected1)) {
			System.out.println("Leads home page is displayed");
		} else {
			System.out.println("Not as expected");
		}

		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc21() throws IOException, InterruptedException {
		login();
		clickedLeads();
		String lead_dd = driver.findElement(By.xpath("//select[@id='fcf']")).getText();
		System.out.println(lead_dd);
		String[] lead = lead_dd.split("\n");
		boolean flag = false;
		String[] expectd = { "All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads",
				"View - Custom 1", "View - Custom 2" };
		for (int i = 0; i < lead.length; i++) {
			if (lead[i].equals(expectd[i])) {
				flag = true;
			} else {
				flag = false;
				System.out.println("Elements are not matching");
			}
		}
		if (flag == true)
			System.out.println("Dropdown is as expected");
		driver.quit();

	}

	public static void tc22() throws IOException, InterruptedException {
		login();
		clickedLeads();
		WebElement dd = driver.findElement(By.id("fcf"));
		Select sec = new Select(dd);
		sec.selectByVisibleText("Today's Leads");
		System.out.println("Unread Leads selected from dropdown");
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Thread.sleep(2000);
		System.out.println("Logged out of the application");
		Thread.sleep(3000);
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("password")).sendKeys(getData("password"));
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		Thread.sleep(3000);
		System.out.println("Logged into the application");
		driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		Thread.sleep(2000);
		Select select = new Select(driver.findElement(By.id("fcf")));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		String expected = "Today's Leads";
		if (defaultItem.equals(expected)) {
			System.out.println("Today's Leads is displayed as expected");
		} else
			System.out.println("Not as expected..");
		driver.quit();

	}

	public static void tc23() throws IOException, InterruptedException {
		login();
		clickedLeads();
		WebElement lead_dd = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select dd = new Select(lead_dd);
		dd.selectByVisibleText("Today's Leads");
		System.out.println("Selected Today's Leads");
		Thread.sleep(2000);
		driver.findElement(By.name("go")).click();
		Select select = new Select(driver.findElement(By.name("fcf")));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println(defaultItem);
		String expected = "Today's Leads";
		if (defaultItem.equals(expected)) {
			System.out.println("Today's Leads is displayed as expected");
		} else
			System.out.println("Not as expected..");

		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc24() throws IOException, InterruptedException {
		login();
		clickedLeads();
		driver.findElement(By.xpath("//input[@name='new']")).click();
		driver.findElement(By.xpath("//input[@id='name_lastlea2']")).sendKeys(getData("lname"));
		System.out.println("Entered last name");
		driver.findElement(By.xpath("//input[@id='lea3']")).sendKeys(getData("comname"));
		System.out.println("Entered company name");
		driver.findElement(By.name("save")).click();
		String header = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		System.out.println(header);
		String expected = "ABCD";
		if (header.equals(expected)) {
			System.out.println("New Lead is created and displayed");
		} else
			System.out.println("Not as expected..");

		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc25() throws IOException, InterruptedException {
		login();
		clickContacts();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='new']")).click();
		System.out.println("Clicked on new Contact button");
		driver.findElement(By.xpath("//input[@id='name_lastcon2']")).sendKeys(getData("lastname"));
		System.out.println("Entered last name");
		driver.findElement(By.xpath("//input[@id='con4']")).sendKeys(getData("accname"));
		System.out.println("Entered account name");
		Thread.sleep(2000);
		driver.findElement(By.name("save")).click();
		System.out.println("Clicked on save");
		Thread.sleep(3000);
		String header = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		String expected = "Rahavi";
		if (header.equals(expected)) {
			System.out.println("New contact created and displayed");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();

	}

	public static void tc26() throws IOException, InterruptedException {
		login();
		clickedLeads();
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys(getData("viewname"));
		System.out.println("Entered view name");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='devname']")).click();
		System.out.println("Entered view unique name");
		driver.findElement(By.name("save")).click();
		System.out.println("Clicked on save");
		Select select = new Select(driver.findElement(By.name("fcf")));
		WebElement option = select.getFirstSelectedOption();
		String Item = option.getText();
		System.out.println(Item);
		String expected = getData("viewname");
		if (Item.equals(expected)) {
			System.out.println("New contact view created and displayed in the dropdown");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc27() throws IOException, InterruptedException {
		login();
		clickContacts();
		WebElement dd = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		Select select = new Select(dd);
		select.selectByVisibleText("Recently Created");
		System.out.println("Recently created selected from dropdown");
		String contact = driver.findElement(By.xpath("//a[contains(text(),'Rahavi')]")).getText();
		String expected = "Rahavi";
		if (contact.equals(expected)) {
			System.out.println("Recently created contact is displayes as expected");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();

	}

	public static void tc28() throws IOException, InterruptedException {
		login();
		clickContacts();
		WebElement dd = driver.findElement(By.name("fcf"));
		Select select = new Select(dd);
		select.selectByVisibleText("My Contacts");
		Thread.sleep(3000);
		WebElement option = select.getFirstSelectedOption();
		String Item = option.getText();
		System.out.println(Item);
		String expected = "My Contacts";
		if (Item.equals(expected)) {
			System.out.println("My Contacts is displayed as expected");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc29() throws InterruptedException, IOException {
		login();
		clickContacts();
		WebElement dd = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		Select select = new Select(dd);
		select.selectByVisibleText("Recently Created");
		System.out.println("Recently created selected from dropdown");
		driver.findElement(By.xpath("//a[contains(text(),'Rahavi')]")).click();
		System.out.println("Recently created contact 'Rahavi' is selected");
		String header = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		String expected = "Rahavi";
		if (header.equals(expected)) {
			System.out.println("The selected contact details are displayed");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();

	}

	public static void tc30() throws IOException, InterruptedException {
		login();
		clickContacts();
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		System.out.println("Create new view link is selected");
		System.out.println("New link is selected");
		driver.findElement(By.xpath("//input[@id='devname']")).sendKeys(getData("contactview"));
		System.out.println("Unique view name is entered");
		driver.findElement(By.name("save")).click();
		String error = driver.findElement(By.xpath("//div[contains(text(),'You must enter a value')]")).getText();
		String expected = "Error: You must enter a value";
		if (error.equals(expected)) {
			System.out.println("Expected error message is displayed");
		} else
			System.out.println("Not as expected..");
		Thread.sleep(3000);
		driver.quit();
	}

	public static void tc31() throws IOException, InterruptedException {
		login();
		clickContacts();
		driver.findElement(By.xpath("//a[contains(text(),'Create New View')]")).click();
		System.out.println("Create new view link is selected");
		System.out.println("New link is selected");
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Test Purpose");
		driver.findElement(By.xpath("//input[@id='devname']")).sendKeys(getData("contactview"));
		driver.findElement(By.name("cancel")).click();
		String homepage = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
		System.out.println(homepage);
		String contacts_dd = driver.findElement(By.name("fcf")).getText();
		String[] contacts = contacts_dd.split("/n");
		for (int i = 0; i < contacts.length; i++) {
			if ("Test Purpose".equals(contacts[i])) {
				System.out.println("Not as expected");
			} else
				System.out.println("The view is not displayed as it is not saved");
		}
		Thread.sleep(3000);
		driver.quit();

	}

	public static void tc32() throws IOException, InterruptedException {
		login();
		clickContacts();
		driver.findElement(By.xpath("//input[@name='new']")).click();
		Thread.sleep(3000);
		System.out.println("Clicked on new");
		driver.findElement(By.xpath("//input[@id='name_lastcon2']")).sendKeys(getData("contactlast"));
		driver.findElement(By.xpath("//input[@id='con4']")).sendKeys(getData("acc1"));
		System.out.println("Entered the last name and account name");
		driver.findElement(By.name("save_new")).click();
		System.out.println("Clicked on save and new");
		String newcontact = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
		System.out.println(newcontact);
		String expected = "Contact Edit";
		if (newcontact.equals(expected)) {
			System.out.println("New contact edit page is diaplayed");
		} else {
			System.out.println("Not as expected");
		}
		Thread.sleep(2000);

	}

	public static void tc33() throws IOException, InterruptedException {
		tc02();
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h1[@class='currentStatusUserName']//a[contains(text(),'Anu Jayanthi Shan')]"))
				.click();
		Thread.sleep(4000);
		String header = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
		String expected = "Anu Jayanthi Shan ";
		if (header.equals(expected)) {
			System.out.println("Name is displayed as in Profile");
		} else
			System.out.println("It is not as expected");
	}

	public static void tc34() throws IOException, InterruptedException {
		login();
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		System.out.println("Clicked on home button");
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//h1[@class='currentStatusUserName']//a[contains(text(),'Anu Jayanthi Shan')]"))
				.click();
		System.out.println("Clickedon User firstname lastname");
		driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img")).click();
		Thread.sleep(5000);
		System.out.println("Clicked edit profile");
		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(5000);
		System.out.println("Switched to popup");
		String contact = driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).getText();
		System.out.println(contact);
		System.out.println("Focus is on Contacts tab");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
		System.out.println("Clicked on About tab");
		Thread.sleep(2000);
		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.clear();
		lastName.sendKeys(getData("lname"));
		System.out.println("Edited lastName key");
		driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']")).click();
		System.out.println("Clicked save all");
		String newuser = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
		String expected2 = "Anu ABCD ";
		System.out.println(newuser);
		Thread.sleep(2000);
		if (newuser.equals(expected2)) {
			System.out.println("User last name is updated");
		} else
			System.out.println("Not as expected");

	}

	public static void tc35() throws IOException, InterruptedException {
		login();
		driver.findElement(By.xpath("//img[@class='allTabsArrow']")).click();
		System.out.println("Clicking on +");
		driver.findElement(By.xpath("//input[@name='customize']")).click();
		Thread.sleep(2000);
		WebElement dd = driver.findElement(By.xpath("//select[@id='duel_select_1']"));
		Select select = new Select(dd);
		select.selectByVisibleText("Opportunities");
		driver.findElement(By.xpath("//img[@class='leftArrowIcon']")).click();
		System.out.println("Removing opportunities tab");
		driver.findElement(By.xpath("//input[@name='save']")).click();
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		Thread.sleep(8000);
		System.out.println("Logged out of application");
		tc02();
		boolean display = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]")).isDisplayed();
		System.out.println(display);
		if (display == false) {
			System.out.println("Opportunities tab is not present when logged in again");
		} else
			System.out.println("Opportunities tab is present");
	}

	public static void tc36() throws IOException, InterruptedException {
		login();
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		System.out.println("Clicked on home button");
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Saturday April 25, 2020')]")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//h1[contains(@class,'pageType')]")).getText());
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		System.out.println("Clicked on 8:00PM");
		driver.findElement(By.xpath("//img[@class='comboboxIcon']")).click();
		Thread.sleep(3000);
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		System.out.println("Switched to window");
		driver.findElement(By.xpath("//a[contains(text(),'Other')]")).click();
		driver.switchTo().window(getWindow[0]);
		Thread.sleep(2000);
		driver.findElement(By.id("EndDateTime_time")).click();
		driver.findElement(By.id("timePickerItem_42")).click();
		System.out.println("End Time is selected as 9:00PM");
		driver.findElement(By.name("save")).click();
		System.out.println("Clicked on Save");
		WebElement element1 = driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(500);
		WebElement element2 = driver.findElement(By.xpath("//span[contains(text(),'Other')]"));
		String event = element2.getText();
		String expected2 = "Other";
		if (event.equals(expected2)) {
			System.out.println("new event is created");
		} else {
			System.out.println("Event not created");
		}
	}

	public static void tc37() throws IOException, InterruptedException {
		tc02();
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		System.out.println("Clicked on home button");
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Saturday April 25, 2020')]")).click();
		System.out.println(driver.findElement(By.xpath("//h1[contains(@class,'pageType')]")).getText());
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'4:00 PM')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		element.click();
		System.out.println("Clicked on 4:00PM");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@class='comboboxIcon']")).click();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		driver.findElement(By.xpath("//a[contains(text(),'Other')]")).click();
		System.out.println("Clicked on Other");
		driver.switchTo().window(getWindow[0]);
		// driver.findElement(By.id("EndDateTime_time")).click();
		// Thread.sleep(1000);
		// driver.findElement(By.id("//div[@id='timePickerItem_38']")).click();
		// System.out.println("Selected end time as 7PM");
		driver.findElement(By.xpath("//input[@id='IsRecurrence']")).click();
		System.out.println("Enabled recuurence");
		driver.findElement(By.xpath("//input[@id='rectypeftw']")).click();
		System.out.println("....");
		WebElement element1 = driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Calculate Latest Date')]")).click();
		Thread.sleep(7000);
		System.out.println(element1.getText());
		System.out.println("Selected on recurrence end date");
		driver.findElement(By.name("save")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//img[@class='monthViewIcon']")).click();
		WebElement element2 = driver.findElement(By.xpath("//a[contains(text(),'Other')]"));
		String event = element2.getText();
		String expected2 = "Other";
		Assert.assertEquals(event, expected2);
	}

	public static void clickContacts() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
	}

	public static void clickedLeads() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();

	}

	public static void clickOpportunities() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
		System.out.println("Clicked on opportunities");

	}

	public static void clickonAccounts() throws InterruptedException {
		driver.findElement(By.xpath("//li[@id='Account_Tab']//a[contains(text(),'Accounts')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='No Thanks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("checkbox_faux")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("lexSubmit")).click();
	}

	public static void login() throws IOException, InterruptedException {
		driver.findElement(By.id("username")).sendKeys(getData("Username"));
		driver.findElement(By.id("password")).sendKeys(getData("password"));
		System.out.println("Entered correct password and username");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);

	}
}
