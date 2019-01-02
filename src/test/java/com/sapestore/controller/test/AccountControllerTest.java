package com.sapestore.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sapestore.controller.AccountController;

/**
 * The class <code>UserLoginControllerTest</code> contains tests for the class
 * {@link <code>UserLoginController</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 7/12/14 11:26 AM
 *
 * @author kmedir
 *
 * @version $Revision$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class AccountControllerTest {
	
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

	/**
	 * The object that is being tested.
	 *
	 * @see com.sapestore.controller.UserLoginController
	 */
	private AccountController fixture = new AccountController();

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public AccountControllerTest() {
	}

	/**
	 * Launch the test.
	 *
	 * @param args String[]
	 */
	public static void main(String[] args) {
		// add code to run tests here
	}

	/**
	 * Return the object that is being tested.
	 *
	 * @return the test fixture
	 *
	 * @see com.sapestore.controller.UserLoginController
	 */
	public AccountController getFixture() {
		return fixture;
	}

	/**
	 * Set the object that is being tested.
	 *
	 * @param fixture the test fixture
	 */
	public void setFixture(AccountController fixture) {
		this.fixture = fixture;
	}

	/**
	 * Run the String beforeLogin(ModelMap) method test
	 */
	@Test
	public void testBeforeLogin() {
		try {
			System.out.println("entered testBeforeLogin");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(get("/beforelogin"))
			.andExpect(status().isOk());
			System.out.println("exited testBeforeLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Run the String login(UserLogin, ModelMap) method test
	 */
	@Test
	public void testLogin() {
		try {
			System.out.println("entered testLogin");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(post("/login").param("userId", "testLoginId").param("password", "testPassword"))
			.andExpect(status().isOk());
			System.out.println("exited testLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Run the String logout(WebRequest, SessionStatus) method test
	 */
	@Test
	public void testLogout() {
		try {
			System.out.println("entered testBeforeLogin");
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
			mockMvc.perform(get("/logout"))
			.andExpect(status().is(302));
			System.out.println("exited testBeforeLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}

/*$CPS$ This comment was generated by CodePro. Do not edit it.
 * patternId = com.instantiations.assist.eclipse.pattern.testCasePattern
 * strategyId = com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = 
 * assertTrue = false
 * callTestMethod = true
 * createMain = true
 * createSetUp = false
 * createTearDown = false
 * createTestFixture = true
 * createTestStubs = false
 * methods = beforeLogin(QModelMap;),login(QUserLogin;!QModelMap;)
 * package = com.sapestore.controller
 * package.sourceFolder = SapeStore/src/main/java
 * superclassType = junit.framework.TestCase
 * testCase = UserLoginControllerTest
 * testClassType = com.sapestore.controller.UserLoginController
 */