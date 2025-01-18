import React, { useState, useEffect } from "react";
import "./TaskManager.css";

const TaskManager = () => {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState({ title: "", description: "" });
  const [isFormTaskOpen, setIsFormTaskOpen] = useState(false);
  const [title, setTitle] = useState("");

  const fetchTasks = async () => {
    try {
      const response = await fetch("http://localhost:8080/tasks");
      const data = await response.json();
      setTasks(data);
    } catch (error) {
      console.error("Erro ao buscar tarefas: ", error);
    }
  };

  const createTask = async () => {
    try {
      const response = await fetch("http://localhost:8080/tasks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newTask),
      });

      if (response.ok) {
        fetchTasks();
        setNewTask({ title: "", description: "" });
        setIsFormTaskOpen(false);
      } else {
        console.error("Erro ao adicionar tarefa: ", response.statusText);
      }
    } catch (error) {
      console.error("Error ao adicinar tarefa: ", error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <div className="navbar">
      <div className={isFormTaskOpen ? "navbar-logo blurred" : "navbar-logo"}>
        <span>Task</span>Coder
      </div>

      <div
        className={
          isFormTaskOpen
            ? "blurred navbar-input-container"
            : "navbar-input-container"
        }
      >
        <div className="navbar-input-container">
          <input
            placeholder="Adicione uma nova tarefa (titulo)"
            className="navbar-input"
            name="text"
            onChange={(e) => setNewTask({ ...newTask, title: e.target.value })}
          />

          <button
            className="navbar-input-btn"
            onClick={() => setIsFormTaskOpen(true)}
          >
            {" "}
            +{" "}
          </button>
        </div>
      </div>

      <div className="task-form-container">
        {isFormTaskOpen && (
          <>
            <>
            <button className="close-button" onClick={() => setIsFormTaskOpen(false)}>&times;</button>
              <h2>Tarefa: {newTask.title}</h2>
              <input
                className="navbar-input-description"
                type="text"
                placeholder="Descrição"
                onChange={(e) =>
                  setNewTask({ ...newTask, description: e.target.value })
                }
              />
            </>
            <button className="navbar-task-btn" onClick={createTask}>
              Enviar
            </button>
          </>
        )}
      </div>
      <div className={isFormTaskOpen ? "task-list blurred" : "task-list"}>
        {tasks.map((task) => (
          <div key={task.id} className="task-card">
            <h3>{task.title}</h3>
            <p>{task.description}</p>
            <p className="task-status">Status: {task.status}</p>
            <p className="task-date">
            Criado em:{" "}
            {new Date(task.createdAt.split(".")[0]).toLocaleString("pt-BR")}
            </p>
            
            </div>
        ))}
        
      </div>
    </div>
  );
};

export default TaskManager;
