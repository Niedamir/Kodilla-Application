package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {
	public TaskMapper taskMapper = new TaskMapper();

	@Test
	public void mapToTask() {
		//Given
		TaskDto taskDto = new TaskDto("1", "test", "test");
		//When
		Task testTask = taskMapper.mapToTask(taskDto);
		//Then
		assertEquals("1", testTask.getId());
		assertEquals("test", testTask.getTitle());
		assertEquals("test", testTask.getContent());
	}
	@Test
	public void mapToTaskDto() {
		//Given
		Task task = new Task("1", "test", "test");
		//When
		TaskDto testTaskDto = taskMapper.mapToTaskDto(task);
		//Then
		assertEquals("1", testTaskDto.getId());
		assertEquals("test", testTaskDto.getTitle());
		assertEquals("test", testTaskDto.getContent());
	}

	@Test
	public void mapToTaskDtoList() {
		//Given
		List<Task> taskList = new ArrayList<>();
		taskList.add(new Task("1", "test", "test"));
		taskList.add(new Task("2", "test", "test"));
		taskList.add(new Task("3", "test", "test"));
		//When
		List<TaskDto> outputList = taskMapper.mapToTaskDtoList(taskList);
		//Then
		assertEquals(3, outputList.size());
		assertEquals("1", outputList.get(0).getId());
		assertEquals("2", outputList.get(1).getId());
		assertEquals("3", outputList.get(2).getId());
	}
}