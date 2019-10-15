/*
 * Copyright 2014-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import sample.pages.HomePage;
import sample.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Pool Dolorier
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityTests {
	@LocalServerPort
	int port;

	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("app.port", String.valueOf(this.port));
		this.driver = new HtmlUnitDriver();
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}


	@Test
	public void logOutSuccess() {
		LoginPage loginPage = LoginPage.go(this.driver);
		HomePage homePage = loginPage.login("user", "password");
		LoginPage successLogoutPage = homePage.logout();
		successLogoutPage.assertAt();
	}

}
