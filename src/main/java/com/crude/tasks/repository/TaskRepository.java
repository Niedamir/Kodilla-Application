package com.crude.tasks.repository;

import com.crude.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
	@Override
	List<Task> findAll();
	Task findById();
}
