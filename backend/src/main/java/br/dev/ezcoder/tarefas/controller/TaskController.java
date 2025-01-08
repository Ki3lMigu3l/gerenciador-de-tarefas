package br.dev.ezcoder.tarefas.controller;

import br.dev.ezcoder.tarefas.model.Task;
import br.dev.ezcoder.tarefas.model.TaskDTO;
import br.dev.ezcoder.tarefas.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask (@RequestBody TaskDTO taskDTO) {
        var task = new Task();
        BeanUtils.copyProperties(taskDTO, task);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(task));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks () {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById (@PathVariable UUID id) {
        Optional<Task> taskModelOptional = taskService.findById(id);

        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> updateTask (@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task não encontrada!");
        }

        var taskModel = taskOptional.get();
        BeanUtils.copyProperties(taskDTO, taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(taskModel));
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTask (@PathVariable  UUID id) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        taskService.delete(task);
    }

}
