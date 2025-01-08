package br.dev.ezcoder.tarefas.service;

import br.dev.ezcoder.tarefas.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(UUID id);

    void delete(Task task);
}
