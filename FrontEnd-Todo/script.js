// Server Url
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

// Register

function register() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email != "" && password != "") {
        fetch(`${SERVER_URL}/auth/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        })
            .then(response => {
                if (response.ok) {
                    alert("Registered SuccessFullly....!")
                    window.location.href = "login.html"
                }
                else {
                    return response.json().then(data => {
                        throw new Error(data.message || "Email Already Exists")
                    })
                }
            })
            .catch(err => {
                alert(err.message);
            })
    }
    else {
        alert("Enter Email and Password");
    }
}

// Login

function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email != "" && password != "") {
        fetch(`${SERVER_URL}/auth/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(response.message || "Enter Valid Email and Password");
                }
                return response.json();
            })
            .then(data => {
                localStorage.setItem("token", data.token);
                window.location.href = "todos.html";
            })
            .catch(err => {
                alert(err.message);
            })
    }
    else {
        alert("Enter Email and Password")
    }
}

// CreateTodoCard

function createTodoCard(todo) {
    const card = document.createElement("div");
    card.className = "todo-card";

    const checkbox = document.createElement("input");
    checkbox.className = "todo-checkbox";
    checkbox.type = "checkbox";
    checkbox.checked = todo.isCompleted;


    checkbox.addEventListener("change", () => {
        const updatedTodo = { ...todo, isCompleted: checkbox.checked }
        updatedTodoStatus(updatedTodo)
    })

    const span = document.createElement("span");
    span.textContent = todo.title;

    if (todo.isCompleted) {
        span.style.textDecoration = "line-through";
        span.style.color = "#aaa";
    }


    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "X";
    deleteBtn.onclick = function () {
        deleteTodo(todo.id);
    }

    card.appendChild(checkbox);
    card.appendChild(span);
    card.appendChild(deleteBtn);

    return card;
}


// Delete Todo

function deleteTodo(id) {
    fetch(`${SERVER_URL}/api/v1/todo/${id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${token}` }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(data.message || "Failed delete Todo");
            }
            return response.text();
        })
        .then(() => loadTodos())
        .catch(err => {
            alert(err.message);
        })
}

// UpdateTodo

function updatedTodoStatus(todo) {
    fetch(`${SERVER_URL}/api/v1/todo`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        },
        body: JSON.stringify(todo)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(data.message || "Failed Update Todo");
            }
            return response.json();
        })
        .then(() => loadTodos())
        .catch(err => {
            alert(err.message);
        })
}

// Add Todo

function addTodo() {
    const input = document.getElementById("new-todo");
    const todoText = input.value.trim();

    if (todoText != "") {
        fetch(`${SERVER_URL}/api/v1/todo/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            },
            body: JSON.stringify({ title: todoText, isCompleted: false })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(data.message || "Failed Add Todo");
                }
                return response.json();
            })
            .then((newTodo) => {
                input.value = "";
                loadTodos();
            })
            .catch(err => {
                alert(err.message);
            })
    }
    else{
        alert("Enter Your ToDo......!");
    }
}


// Load Todos

function loadTodos() {
    if (!token) {
        alert("Please Login First....!");
        window.location.href = "login.html";
        return;
    }

    fetch(`${SERVER_URL}/api/v1/todo`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(data.message || "Failed to Load Todos")
            }
            return response.json();
        })
        .then((todos) => {
            const todolist = document.getElementById("todo-list");
            todolist.innerHTML = "";

            if (!todos || todos.length == 0) {
                todolist.innerHTML = `<p id="empty-message">No Todos Yet.Add One Below....!</p>`;
            }
            else {
                todos.forEach(todo => {
                    todolist.appendChild(createTodoCard(todo));
                })
            }
        })
        .catch(err => {
            alert(err.message);
        })
}


document.addEventListener("DOMContentLoaded", () => {
    const todolist = document.getElementById("todo-list");
    if (todolist) {
        loadTodos();
    }
})