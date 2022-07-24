let products;
let cartContainer = document.getElementById("cart-container");
let loggedUserId = sessionStorage.getItem("id");
const URL = "http://localhost:8080";

function fillCart(products) {
  for (p of products) {
    let divCart = document.createElement("div");

    divCart.innerHTML = `
        <h2>Category: ${p.category}</h2>
        <h2>Name: ${p.name}</h2>
        <h2>Price: ${p.unitprice}</h2>
        Quantity<input type="number" id="${p.sku}"  min="1" max="${p.quantity}"/><br/>
        <button id="add-to-cart" value="${p.sku}" onclick="removeFromCart(this.value)">Remove from cart</button>
        `;

    divCart.setAttribute("class", "product");
    cartContainer.append(divCart);
  }
}

(async () => {
  if (loggedUserId == null) {
    window.location.href = "login.html";
    alert("Log-in first to view your cart!!");
  }
  let req = await fetch(`${URL}/users/cart/${loggedUserId}`);
  let res = await req.json();
  products = res;
  console.log(products);
  cartContainer.innerHTML = "";
  fillCart(products);
})();

/** remove from cart */
let removeFromCart = async (e) => {
  let sku = e;
  let id = sessionStorage.getItem("id");
  let req = await fetch(`http://localhost:8080/users/removefromcart/${sku}`, {
    method: "POST",
    headers: { "Content-Type": "application/json", user_id: `${id}` },
  });
  let res = await req.json();
  console.log(res);
  location.reload();
};

/** checkout/place order */
let checkOut = async () => {
  if (products == "") {
    window.location.href = "products.html";
    alert("Add items first!!!");
  }
  for (p of products) {
    let amount = document.getElementById(p.sku).value;
    let newQuantity = p.quantity - amount;
    let updateObj = {
      sku: p.sku,
      category: p.category,
      name: p.name,
      quantity: newQuantity,
      unitprice: p.unitprice,
    };

    let req = await fetch(`http://localhost:8080/products`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updateObj),
    });
    let res = await req.json();
    console.log(res);
  }

  //now clearing the cart
  let req2 = await fetch(`http://localhost:8080/users/clear/${loggedUserId}`, {
    method: "PUT",
  });
  let res = await req2.json();
  console.log(res);

  window.location.href = "home.html";
  alert("Order placed successfully!!");
};
