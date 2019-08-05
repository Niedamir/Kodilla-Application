package com.crude.tasks.repository;

import com.crude.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
	@Override
	List<Task> findAll();
	Task findById();
	@Override
	Task save(Task task);
	Optional<Task> delete(Long id);
	@Override
	Optional<Task> findById(Long id);
}
