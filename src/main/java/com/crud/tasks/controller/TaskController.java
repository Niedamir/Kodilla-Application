package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
	@Autowired
	private DbService service;
	@Autowired
	private TaskMapper taskMapper;

	@RequestMapping(method = RequestMethod.GET, value = "getTasks")
	public List<TaskDto> getTasks() {
		return taskMapper.mapToTaskDtoList(service.getAllTasks());
	}
	@RequestMapping(method = RequestMethod.PATCH, value = "updateTask")
	public TaskDto updateTask(TaskDto taskDto) {
		return new TaskDto( 1L, "Edited test title", "Editet_test_content");
	}
	@RequestMapping(method = RequestMethod.PUT, value = "createTask", consumes = APPLICATION_JSON_VALUE)
	public void createTask(@RequestBody TaskDto taskDto) {
		service.saveTask(taskMapper.mapToTask(taskDto));
	}

	@RequestMapping(method = RequestMethod.GET, value = "getTask")
	public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
		return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
	public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException {
		service.deleteTask(taskId);
	}
}