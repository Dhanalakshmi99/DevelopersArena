const taskInput = document.getElementById("taskInput");
const taskList = document.getElementById("taskList");

// Load tasks from localStorage
window.onload = loadTasks;

document.getElementById("addBtn").addEventListener("click", addTask);

function addTask() {
  const taskText = taskInput.value.trim();
  if (taskText === "") return;

  let task = {
    text: taskText,
    completed: false
  };

  addTaskToHTML(task);
  saveTask(task);

  taskInput.value = "";
}

// Add to HTML
function addTaskToHTML(task) {
  const li = document.createElement("li");
  li.textContent = task.text;

  if (task.completed) li.classList.add("completed");

  // Mark complete
  li.addEventListener("click", () => {
    li.classList.toggle("completed");
    updateLocalStorage();
  });

  // Delete button
  const del = document.createElement("span");
  del.textContent = "❌";
  del.addEventListener("click", (e) => {
    e.stopPropagation();
    li.remove();
    updateLocalStorage();
  });

  li.appendChild(del);
  taskList.appendChild(li);
}

// Save to localStorage
function saveTask(task) {
  let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  tasks.push(task);
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

// Load tasks
function loadTasks() {
  let tasks = JSON.parse(localStorage.getItem("tasks")) || [];
  tasks.forEach(task => addTaskToHTML(task));
}

// Update localStorage
function updateLocalStorage() {
  let tasks = [];
  document.querySelectorAll("#taskList li").forEach(li => {
    tasks.push({
      text: li.childNodes[0].textContent,
      completed: li.classList.contains("completed")
    });
  });
  localStorage.setItem("tasks", JSON.stringify(tasks));
}
