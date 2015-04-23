package org.belaran.belarquian.service;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RunWith(Arquillian.class)
public class ToDoServiceTest {

	private static final String ARTEFACT_NAME = "Belarquian.war";
	private static final String TARGET_DIR = "target";

	@ArquillianResource
	private URL baseURL;

	@Drone
	private WebDriver webDriver;

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(ZipImporter.class, ARTEFACT_NAME)
				.importFrom(new File(TARGET_DIR + "/" + ARTEFACT_NAME))
				.as(WebArchive.class);
	}

	@Test
	@RunAsClient
	@InSequence(4)
	public void getToDosAsHTML() throws IOException {
		 webDriver.get(baseURL.toString() + "/list");
		 System.out.println(webDriver.getPageSource());
	     WebElement element = webDriver.findElement(By.xpath("//a"));
	     assertEquals(element.getAttribute("name"),"1");
	}

	@Test
	@RunAsClient
	@InSequence(5)
	public void addByHtmlForm() throws IOException {
		 webDriver.get(baseURL.toString() + "/add");
		 System.out.println(webDriver.getPageSource()); 
		 webDriver.findElement(By.id("item_id")).sendKeys(String.valueOf(new Random().nextLong()));
		 webDriver.findElement(By.id("item_label")).sendKeys("label");
		 webDriver.findElement(By.id("item_submit")).click();
	}

	
	@Test
	@RunAsClient
	@InSequence(1)
	public void checkServiceStatus()
			throws IOException {
		 webDriver.get(baseURL.toString() + "rest/test");
	     String element = webDriver.getPageSource();
	     System.out.println(element);
	}

	@Test
	@RunAsClient
	@InSequence(2)
	public void addOneItemTest()
			throws IOException {
		printInputStream(baseURL.toString() + "rest/add/1/a-value");
	}

	@Test
	@RunAsClient
	@InSequence(3)
	public void listItems()
			throws IOException {
		printInputStream(baseURL.toString() + "rest/list");
	}

	private static void printInputStream(String urlAsString) throws IOException {
		System.out.println("Invoking URL:" + urlAsString);
		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(urlAsString).openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		    System.out.println(inputLine);
		in.close();
	}

}
