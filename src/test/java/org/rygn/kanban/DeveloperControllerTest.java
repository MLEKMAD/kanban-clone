package org.rygn.kanban;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.controller.DeveloperController;
import org.rygn.kanban.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
 

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest {
 
    @MockBean
    DeveloperService developerService;
 
    @Autowired
    MockMvc mockMvc;
 
    @Test
    public void testfindAll() throws Exception {
    	Developer devRG = new Developer();
		devRG.setEmail("remy.girodon@gmail.com");
		devRG.setFirstname("Rémy");
		devRG.setLastname("Girodon");
		devRG.setPassword("abc123");
		devRG.setStartContract(LocalDate.of(2017, Month.NOVEMBER, 1));
        List<Developer> devs = Arrays.asList(devRG);
 
        Mockito.when(developerService.findAllDevelopers()).thenReturn(devs);
 
        mockMvc.perform(get("/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is("remy.girodon@gmail.com")));
    }
 
}