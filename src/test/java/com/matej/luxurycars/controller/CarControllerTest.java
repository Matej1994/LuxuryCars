package com.matej.luxurycars.controller;

import com.matej.luxurycars.Application;
import com.matej.luxurycars.services.CarServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarServices carServices;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void getAllCar_With_Admin() throws Exception {

        mockMvc.perform(
                get("/car"))
                .andExpect(status().isOk());
    }


//    @Test
//    public void getCar() throws Exception {
//    }
//
//    @Test
//    public void addCar() throws Exception {
//    }

//    @Test
//    @WithMockUser(roles = {"ADMIN"})
//    public void deleteById() throws Exception {
//        mockMvc.perform(delete("/users/{id}", user.getId()))
//                .andExpect(status().isOk();
//    }

}