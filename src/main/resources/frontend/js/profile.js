const the_url = "YOUR_ENDPOINT_URL_HERE";

let personContainer = document.getElementById("user-container");

(async () => {
  if (
    !sessionStorage.getItem("username") ||
    sessionStorage.getItem("username") == "undefined" ||
    sessionStorage.getItem("username") == null
  ) {
    window.location.href = "login.html";
    alert("You need to login first!!!");
  }
  let req = await fetch(
    `${the_url}/users/${sessionStorage.getItem("username")}`
  );
  let res = await req.json();
  personContainer.innerHTML = "";
  populatePerson(res);
  populateUpdate(res);
})();
function populatePerson(person) {
  personContainer.innerHTML = `
    <h2>Username: ${person.username}</h2>
    <h2>Password: ${person.password}</h2>
    <h2>Email: ${person.email}</h2>
    `;
}

function populateUpdate(person) {
  document
    .getElementById("username")
    .setAttribute("value", `${person.username}`);
  document
    .getElementById("password")
    .setAttribute("value", `${person.password}`);
  document.getElementById("email").setAttribute("value", `${person.email}`);
}

let putUser = async () => {
  let id = sessionStorage.getItem("id");
  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let email = document.getElementById("email").value;

  let updateObj = {
    id,
    username,
    password,
    email,
  };

  fetch(`${the_url}/users`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(updateObj),
  }).then((response) => {
    response.json();
    if (!response.ok) {
      alert("User update failed! try again.");
    } else {
      alert("User updated successfully!!");
      sessionStorage.setItem("id", `${updateObj.id}`);
      sessionStorage.setItem("username", `${updateObj.username}`);
      location.reload();
    }
  });
};

function update(event) {
  event.preventDefault();
  putUser();
}

document.getElementById("update-form").addEventListener("submit", update);

function toggleUpdateForm() {
  let form = document.getElementById("update-form");
  if (form.hasAttribute("hidden")) {
    form.removeAttribute("hidden");
  } else {
    form.setAttribute("hidden", "true");
  }
}
