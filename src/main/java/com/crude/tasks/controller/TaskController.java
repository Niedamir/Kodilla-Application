package com.crude.tasks.controller;

import com.crude.tasks.domain.TaskDto;
import com.crude.tasks.mapper.TaskMapper;
import com.crude.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
	@RequestMapping(method = RequestMethod.GET, value = "getTask")
	public TaskDto getTask(Long taskId) {
		return new TaskDto(1L, "test title", "test_content");
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
	public void deleteTask (Long taskId) {}
	@RequestMapping(method = RequestMethod.PATCH, value = "updateTask")
	public TaskDto updateTask(TaskDto taskDto) {
		return new TaskDto( 1L, "Edited test title", "Editet_test_content");
	}
	@RequestMapping(method = RequestMethod.PUT, value = "createTask")
	public void createTask(TaskDto taskDto) {}
}
