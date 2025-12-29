import type { Task } from "../context/TaskContext";

interface TaskListProps {
  tasks: Task[];
  onStatusChange: (id: number, status: "todo" | "done") => void;
}

export const TaskList = ({ tasks, onStatusChange }: TaskListProps) => {
  if (tasks.length === 0) return <p>No tasks yet</p>;

  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.id}>
          <span
            style={{
              textDecoration: task.status === "done" ? "line-through" : "none",
            }}
          >
            {task.title}
          </span>
          <button
            onClick={() =>
              onStatusChange(task.id, task.status === "todo" ? "done" : "todo")
            }
          >
            {task.status === "todo" ? "Mark Done" : "Mark Todo"}
          </button>
        </li>
      ))}
    </ul>
  );
};
