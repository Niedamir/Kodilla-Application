package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {
	@Autowired
	private DbService service;
	@Autowired
	private TaskMapper taskMapper;

	@RequestMapping(method = RequestMethod.GET, value = "/getTasks")
	public List<TaskDto> getTasks() {
		return taskMapper.mapToTaskDtoList(service.getAllTasks());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateTask")
	public TaskDto updateTask(TaskDto taskDto) {
		return new TaskDto( "2", "Edited test title", "Editet_test_content");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createTask", consumes = APPLICATION_JSON_VALUE)
	public TaskDto createTask(@RequestBody TaskDto taskDto) {
		service.saveTask(taskMapper.mapToTask(taskDto));
		return taskDto;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getTask")
	public TaskDto getTask(@RequestParam Long id) throws TaskNotFoundException {
		return taskMapper.mapToTaskDto(service.getTask(id).orElseThrow(TaskNotFoundException::new));
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteTask")
	public void deleteTask(@RequestParam Long id) throws TaskNotFoundException {
		service.deleteTask(id);
	}
}
