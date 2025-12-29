import React from "react";
import { AuthProvider } from "./context/AuthContext";
import { TaskProvider } from "./context/TaskContext";
import { Login } from "./pages/Login";
import { Dashboard } from "./pages/Dashboard";

function App() {
  const token = localStorage.getItem("token");

  return (
    <AuthProvider>
      <TaskProvider>{token ? <Dashboard /> : <Login />}</TaskProvider>
    </AuthProvider>
  );
}

export default App;
