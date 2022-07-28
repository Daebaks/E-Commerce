const the_url = "YOUR_ENDPOINT_URL_HERE";

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

  let req = await fetch(`${the_url}/users/login`, {
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

  let req = await fetch(`${the_url}/users`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(registerObj),
  });

  let res = await req.json();

  sessionStorage.setItem("id", `${res.id}`);
  sessionStorage.setItem("username", `${res.username}`);

  console.log(res);
  if (
    !sessionStorage.getItem("username") ||
    sessionStorage.getItem("username") == "undefined" ||
    sessionStorage.getItem("username") == null
  ) {
    alert("Failed register. Username taken or wrong entries! Try again.");
    window.location.href = "login.html";
  } else {
    alert("Registered successfully");
    window.location.href = "home.html";
  }
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
