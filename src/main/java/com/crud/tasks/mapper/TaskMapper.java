package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
	public Task mapToTask(final TaskDto taskDto) {
		return new Task(
			taskDto.getId(),
			taskDto.getTitle(),
			taskDto.getContent());
	}
	public TaskDto mapToTaskDto(final Task task) {
		return new TaskDto(
			task.getId(),
			task.getTitle(),
			task.getContent());
	}
	public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
		return taskList.stream()
			.map(t -> new TaskDto(t.getId(), t.getTitle(), t.getContent()))
			.collect(Collectors.toList());
	}
}
