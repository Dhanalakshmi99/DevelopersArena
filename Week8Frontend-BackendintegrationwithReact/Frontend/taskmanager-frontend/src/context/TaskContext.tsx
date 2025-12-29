import { createContext, useState, useContext } from "react";
import type { ReactNode } from "react";

export interface Task {
  id: number;
  title: string;
  status: "todo" | "done";
}

interface TaskContextType {
  tasks: Task[];
  setTasks: (tasks: Task[]) => void;
}

export const TaskContext = createContext<TaskContextType | null>(null);

export const TaskProvider = ({ children }: { children: ReactNode }) => {
  const [tasks, setTasks] = useState<Task[]>([]);

  return (
    <TaskContext.Provider value={{ tasks, setTasks }}>
      {children}
    </TaskContext.Provider>
  );
};

export const useTasks = () => {
  const context = useContext(TaskContext);
  if (!context) throw new Error("useTasks must be used within TaskProvider");
  return context;
};
