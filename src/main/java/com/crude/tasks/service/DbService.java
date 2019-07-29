package com.crude.tasks.service;

import com.crude.tasks.domain.Task;
import com.crude.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}