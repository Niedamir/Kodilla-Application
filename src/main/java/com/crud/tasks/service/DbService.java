package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
	@Autowired
	private TaskRepository repository;

	public List<Task> getAllTasks() {
		return repository.findAll();
	}

	public Optional<Task> getTaskById(String id) {
		return repository.findById(Long.parseLong(id));
	}

	public Task saveTask(final Task task) {
		return repository.save(task);
	}
	public Optional<Task> getTask(final TaskDto taskDto) {
		return repository.findById(Long.parseLong(taskDto.getId()));
	}
	public void deleteTask(final TaskDto taskDto) {
		repository.deleteById(Long.parseLong(taskDto.getId()));
	}
}