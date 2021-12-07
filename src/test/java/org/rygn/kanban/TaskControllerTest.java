package org.rygn.kanban;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.domain.Task;
import org.rygn.kanban.controller.TaskController;
import org.rygn.kanban.service.TaskService;
import org.rygn.kanban.utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
 
@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
@Slf4j
public class TaskControllerTest {
 

    @MockBean
    TaskService taskService;
 
    @Autowired
    MockMvc mockMvc;
 
    @Test
    public void testfindAllTasks() throws Exception {
    	Task task1 = new Task();
		task1.setCreated(LocalDate.now());
		task1.setTitle("task1");
		task1.setNbHoursForecast(0);
		task1.setNbHoursReal(0);
		task1.setType(null);
		task1.setStatus(null);  
		Collection<Task> tasks = Arrays.asList(task1);

        Mockito.when(taskService.findAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("task1")));
                
    }
    
    @Test
    public void testCreatetask() throws Exception {
    	Developer devRG = new Developer();
		devRG.setEmail("remy.girodon@gmail.com");
		devRG.setFirstname("Rémy");
		devRG.setLastname("Girodon");
		devRG.setPassword("abc123");
		devRG.setStartContract(LocalDate.of(2017, Month.NOVEMBER, 1));
    	Task task1 = new Task();
		task1.setCreated(LocalDate.now());
		task1.setTitle("task2");
		task1.setNbHoursForecast(0);
		task1.setNbHoursReal(0);
		task1.addDeveloper(devRG);
		task1.setType(null);
		task1.setStatus(null);  

        Mockito.when(taskService.createTask(task1)).thenReturn(task1);
        log.info("I am here");
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/tasks")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content(asJsonString(task1))
        		.characterEncoding("utf-8"))
                .andExpect(status().isCreated());
                
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
}