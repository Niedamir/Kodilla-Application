package com.crude.tasks.service;

import com.crude.tasks.domain.Task;
import com.crude.tasks.repository.TaskRepository;
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

	public Task getTaskById(Long id) {
		return repository.findById();
	}

	public Task saveTask(final Task task) {
		return repository.save(task);
	}
	public Optional<Task> getTask(final Long id) {
		return repository.findById(id);
	}
	public Optional<Task> deleteTask(final Long id) {
		return repository.delete(id);
	}
}