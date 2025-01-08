package br.dev.ezcoder.tarefas.service.impl;

import br.dev.ezcoder.tarefas.model.Task;
import br.dev.ezcoder.tarefas.repository.TaskRepository;
import br.dev.ezcoder.tarefas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
