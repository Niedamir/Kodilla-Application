package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TaskController taskController;

	@Test
	public void shouldGetTasks() throws Exception {
		//Given
		List<TaskDto> taskList = new ArrayList<>();
		taskList.add(new TaskDto(new Long(1), "Test Task", "Test Content"));
		when(taskController.getTasks()).thenReturn(taskList);
		//when & Then
		mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			//Tasks List
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].title", is("Test Task")))
			.andExpect(jsonPath("$[0].content", is("Test Content")));
	}
	@Test
	public void shouldUpdateTask() throws Exception {
		//Given
		TaskDto taskDto = new TaskDto(new Long(1), "Test", "Test Content");
		when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

		Gson gson = new Gson();
		String jsonContent = gson.toJson(taskDto);

		//when & Then
		mockMvc.perform(put("/v1/task/updateTask")
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(jsonContent))
			.andExpect( jsonPath("$.id", is(1)))
			.andExpect( jsonPath("$.title", is("Test")))
			.andExpect( jsonPath("$.content", is("Test Content")));
	}
	@Test
	public void shouldCreateTask() throws Exception {
		//Given
		TaskDto taskDto = new TaskDto(new Long(1), "Test", "Test Content");
		when(taskController.createTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

		Gson gson = new Gson();
		String jsonContent = gson.toJson(taskDto);

		//when & Then
		mockMvc.perform(post("/v1/task/createTask")
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(jsonContent))
			.andExpect( jsonPath("$.id", is(1)))
			.andExpect( jsonPath("$.title", is("Test")))
			.andExpect( jsonPath("$.content", is("Test Content")));
	}

	@Test
	public void shouldGetTask() throws Exception {
		//Given
		List<TaskDto> taskList = new ArrayList<>();
		TaskDto taskDto = new TaskDto(new Long(1), "Test", "Test Content");
		taskList.add(taskDto);
		Long id = new Long(1);
		when(taskController.getTask(id)).thenReturn(taskDto);

		Gson gson = new Gson();
		String jsonContent = gson.toJson(taskDto);

		//when & Then
		mockMvc.perform(get("/v1/task/getTask?id=1").param("id", "id")
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(jsonContent))
			.andExpect(jsonPath("$.title", is("Test")))
			.andExpect(jsonPath("$.content", is("Test Content")));
	}
	@Test
	public void shouldDeleteTask() throws Exception {
		//Given
		TaskDto taskDto = new TaskDto(new Long(1), "Test", "Test Content");
		Long id = new Long(1);
		taskController.createTask(taskDto);

		Gson gson = new Gson();
		String jsonContent = gson.toJson(taskDto);

		//when & Then
		mockMvc.perform(delete("/v1/task/deleteTask?id=1").param("id", "id")
			.contentType(MediaType.APPLICATION_JSON)
			.characterEncoding("UTF-8")
			.content(jsonContent))
			.andExpect(status().isOk());
	}
}