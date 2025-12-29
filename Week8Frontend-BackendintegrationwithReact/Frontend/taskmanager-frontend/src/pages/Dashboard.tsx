import { useContext } from "react";
import { TaskForm } from "../components/TaskForm";
import { TaskList } from "../components/TaskList";
import { TaskContext } from "../context/TaskContext"; // runtime import
import type { Task } from "../context/TaskContext"; // type-only import
import { useAuth } from "../context/AuthContext";
import api from "../services/api";

export const Dashboard = () => {
  const taskContext = useContext(TaskContext);
  const { token } = useAuth(); // Get token from AuthContext

  if (!taskContext || !token) return null;

  const handleAdd = async (title: string) => {
    const response = await api.post(
      "/tasks",
      { title },
      { headers: { Authorization: `Bearer ${token}` } }
    );

    const newTask: Task = {
      id: response.data.id,
      title: response.data.title,
      status: response.data.status as "todo" | "done",
    };

    taskContext.setTasks([...taskContext.tasks, newTask]);
  };

  const handleStatusChange = async (id: number, status: "todo" | "done") => {
    const response = await api.put(
      `/tasks/${id}/status`,
      { status },
      { headers: { Authorization: `Bearer ${token}` } }
    );

    const updatedTask: Task = {
      id: response.data.id,
      title: response.data.title,
      status: response.data.status as "todo" | "done",
    };

    taskContext.setTasks(
      taskContext.tasks.map((t) => (t.id === id ? updatedTask : t))
    );
  };

  return (
    <div>
      <h2>Dashboard</h2>
      <TaskForm onAdd={handleAdd} />
      <TaskList
        tasks={taskContext.tasks}
        onStatusChange={handleStatusChange}
      />
    </div>
  );
};
