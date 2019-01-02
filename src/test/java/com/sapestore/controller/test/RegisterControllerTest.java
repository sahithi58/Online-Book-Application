package com.sapestore.controller.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.sapestore.controller.RegisterController;
import com.sapestore.service.CustomerRegService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class RegisterControllerTest {
       
       private MockMvc mockMvc;

           @Autowired
           private WebApplicationContext wac;
              
              
              @Autowired
              private CustomerRegService customerService;
              
              
              /**
              * The object that is being tested.
              *
              * @see com.sapestore.controller.RegisterController
              */
              private RegisterController fixture = new RegisterController();

              /**
              * Construct new test instance
              *
              * @param name the test name
              */
              public RegisterControllerTest() {
              }

              /**
              * Launch the test.
              *
              * @param args String[]
              */
              public static void main(String[] args) {
              }

              /**
              * Return the object that is being tested.
              *
              * @return the test fixture
              *
              * @see com.sapestore.controller.RegisterController
              */
              public RegisterController getFixture() {
                     return fixture;
              }

              /**
              * Set the object that is being tested.
              *
              * @param fixture the test fixture
              */
              public void setFixture(RegisterController fixture) {
                     this.fixture = fixture;
              }

              
              /**
              * Run the String testBeforeRegister(ModelMap) method test
              */
       

              
              /*Ensure that the HTTP status code 200 is returned.
              Ensure that the name of the returned view is same as given in controller.*/
       @Test
       public void testBeforeRegister() {
              try {
                     mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                     mockMvc.perform(post("/beforeRegister"))
                     .andExpect(status().is(200)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("CustomerRegistration"));
                     
              } catch (Exception e) {
                     e.printStackTrace();
              }      
       }
              
       /**
       * Run the String testRegister(ModelMap) method test
       */
              @Test
              public void testRegister() {
                     try {
                           mockMvc = MockMvcBuilders.webAppContextSetup(wac).build() ;
                           mockMvc.perform(post("/register"))
                            .andExpect(status().is(200)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("CustomerRegistration"));
                           //.andExpect(model().attribute(“attr1”, “value”)
                     } catch (Exception e) {
                           e.printStackTrace();
                     }      
              
                     
       }
              
              /**
              * Run the String testafterEdit(ModelMap) method test
              */
              @Test
              public void testbeforeEdit() {
                     try {
                           mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
                           mockMvc.perform(post("/afterEdit"))
                            .andExpect(status().is(200)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.view().name("editProfile"));
                     } catch (Exception e) {
                           e.printStackTrace();
                     }      
              
              
       }

}

