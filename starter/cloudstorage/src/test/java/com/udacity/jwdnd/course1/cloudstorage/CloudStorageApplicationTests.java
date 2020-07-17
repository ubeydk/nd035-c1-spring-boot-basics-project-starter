package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void signUpThenLogoutTest(){
		String username = "loginTestuser";
		String password = "loginTestpassword";
		//signup
		driver.get("http://localhost:" + this.port + "/signup");
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("signUpButton")));
		driver.findElement(By.id("inputFirstName")).sendKeys("Test");
		driver.findElement(By.id("inputLastName")).sendKeys("User");
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("signUpButton")).click();
		wait.until(webDriver -> webDriver.findElement(By.id("signSuccess")));
		//login
		driver.get("http://localhost:" + this.port + "/login");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("loginButton")).click();
		//logout
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		driver.findElement(By.id("logoutButton")).click();
		//unauthorized home page
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
		Assertions.assertNotEquals("Home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void noteCreateViewEditadnDeleteTest(){
		String username = "loginNoteuser";
		String password = "loginNotepassword";
		String title = "test title";
		String description = "test description";
		String descriptionAddition = "addition";
		//signup
		driver.get("http://localhost:" + this.port + "/signup");
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("signUpButton")));
		driver.findElement(By.id("inputFirstName")).sendKeys("Test");
		driver.findElement(By.id("inputLastName")).sendKeys("User");
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("signUpButton")).click();
		wait.until(webDriver -> webDriver.findElement(By.id("signSuccess")));
		//login
		driver.get("http://localhost:" + this.port + "/login");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("loginButton")).click();
		//navigate to note
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
		//add note
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-info")));
		driver.findElement(By.className("btn-info")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
		driver.findElement(By.id("note-title")).sendKeys(title);
		driver.findElement(By.id("note-description")).sendKeys(description);
		driver.findElement(By.className("btn-primary")).click();
		//navigate to note
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
		//validate added note
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-success")));
		driver.findElement(By.className("btn-success")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
		Assertions.assertEquals(title, driver.findElement(By.id("note-title")).getAttribute("value"));
		Assertions.assertEquals(description, driver.findElement(By.id("note-description")).getAttribute("value"));
		//edit note
		driver.findElement(By.id("note-description")).sendKeys(descriptionAddition);
		driver.findElement(By.className("btn-primary")).click();
		//navigate to note
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
		//validate edited note
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-success")));
		driver.findElement(By.className("btn-success")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
		Assertions.assertEquals(description + descriptionAddition, driver.findElement(By.id("note-description")).getAttribute("value"));
		//navigate to note
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
		//delete note
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-danger")));
		driver.findElement(By.className("btn-danger")).click();
		//navigate to note
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
		driver.findElement(By.id("nav-notes-tab")).click();
		//validate note doesn't exist
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-info")));
		Assertions.assertTrue(driver.findElements(By.className("btn-success")).isEmpty());
	}

	@Test
	public void credentialAddViewEditDeleteTest(){
		String username = "loginCredentialuser";
		String password = "loginCredentialpassword";
		String credentialUrl = "ubersuperdrive.com";
		String credentialUsername = "ubeyd";
		String credentialPassword = "weakpass";
		String credentialUrlAddition = "/login";
		//signup
		driver.get("http://localhost:" + this.port + "/signup");
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("signUpButton")));
		driver.findElement(By.id("inputFirstName")).sendKeys("Test");
		driver.findElement(By.id("inputLastName")).sendKeys("User");
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("signUpButton")).click();
		wait.until(webDriver -> webDriver.findElement(By.id("signSuccess")));
		//login
		driver.get("http://localhost:" + this.port + "/login");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.id("loginButton")).click();
		//navigate to credential
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();
		//add credential
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addCredentialButton")));
		driver.findElement(By.id("addCredentialButton")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialSaveButton")));
		driver.findElement(By.id("credential-url")).sendKeys(credentialUrl);
		driver.findElement(By.id("credential-username")).sendKeys(credentialUsername);
		driver.findElement(By.id("credential-password")).sendKeys(credentialPassword);
		driver.findElement(By.id("credentialSaveButton")).click();
		//navigate to credential
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();
		//validate encrypted password is shown on list
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-success")));
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assertions.assertTrue(!bodyText.contains(credentialPassword));
		//validate added credential
		driver.findElement(By.className("btn-success")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialSaveButton")));
		Assertions.assertEquals(credentialUrl, driver.findElement(By.id("credential-url")).getAttribute("value"));
		Assertions.assertEquals(credentialUsername, driver.findElement(By.id("credential-username")).getAttribute("value"));
		Assertions.assertEquals(credentialPassword, driver.findElement(By.id("credential-password")).getAttribute("value"));
		//edit credential
		driver.findElement(By.id("credential-url")).sendKeys(credentialUrlAddition);
		driver.findElement(By.id("credentialSaveButton")).click();
		//navigate to credential
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();
		//validate edited credential
		wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-success")));
		bodyText = driver.findElement(By.tagName("body")).getText();
		Assertions.assertTrue(bodyText.contains(credentialUrl + credentialUrlAddition));
		//delete credential
		driver.findElement(By.className("btn-danger")).click();
		//navigate to credential
		driver.get("http://localhost:" + this.port + "/home");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
		driver.findElement(By.id("nav-credentials-tab")).click();
		//validate credential doesn't exist
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addCredentialButton")));
		Assertions.assertTrue(driver.findElements(By.className("btn-success")).isEmpty());
	}

}
