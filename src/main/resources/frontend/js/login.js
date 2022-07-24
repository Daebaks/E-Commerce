function toggleForm() {
  let registerForm = document.getElementById("register-form");
  if (registerForm.hasAttribute("hidden")) {
    registerForm.removeAttribute("hidden");
  } else {
    registerForm.setAttribute("hidden", "true");
  }
}

let postLogin = async () => {
  let username = document.getElementById("login-username").value;
  let password = document.getElementById("login-password").value;

  let loginObj = {
    username,
    password,
  };

  console.log(loginObj);

  let req = await fetch(`http://localhost:8080/users/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(loginObj),
  });

  let res = await req.json();
  console.log(res);

  sessionStorage.setItem("id", `${res.id}`);
  sessionStorage.setItem("username", `${res.username}`);
  if (!res.id || !res.username) {
    alert("Try again, failed login!!");
    window.location.href = "login.html";
  } else {
    alert("Successfully logged-in");
    console.log(sessionStorage.getItem("id"));
    window.location.href = "products.html";
  }
};

let postRegister = async () => {
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let email = document.getElementById("email").value;

  let registerObj = {
    username,
    password,
    email,
  };

  console.log(registerObj);

  let req = await fetch(`http://localhost:8080/users`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(registerObj),
  });

  let res = await req.json();

  sessionStorage.setItem("id", `${res.id}`);
  sessionStorage.setItem("username", `${res.username}`);

  console.log(res);

  console.log(sessionStorage.getItem("id"));
};

function submitLogin(event) {
  event.preventDefault();
  postLogin();
}

function submitRegister(event) {
  event.preventDefault();
  postRegister();
}

let loginButton = document
  .getElementById("login-form")
  .addEventListener("submit", submitLogin);

let registerButton = document
  .getElementById("register-form")
  .addEventListener("submit", submitRegister);
