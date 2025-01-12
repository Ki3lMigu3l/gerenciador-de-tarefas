package br.dev.ezcoder.tarefas.model;

import br.dev.ezcoder.tarefas.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tarefas")
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private LocalDateTime createdAt;

    public Task () {
        this.status = TaskStatus.PENDENTE;
        this.createdAt = LocalDateTime.now();
    }
}
