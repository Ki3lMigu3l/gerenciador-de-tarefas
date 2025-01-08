package br.dev.ezcoder.tarefas.repository;

import br.dev.ezcoder.tarefas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
