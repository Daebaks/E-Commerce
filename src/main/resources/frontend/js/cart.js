let products;
let cartContainer = document.getElementById("cart-container");
let loggedUserId = sessionStorage.getItem("id");
const the_url = "http://localhost:8080";

if (loggedUserId == "undefined" || loggedUserId == null || !loggedUserId) {
  window.location.href = "login.html";
  alert("Log-in first to view your cart!!");
}
function fillCart(products) {
  for (p of products) {
    let divCart = document.createElement("div");

    divCart.innerHTML = `
        
            <div class="the-text">
        <h2>Category: ${p.category}</h2>
        <h2>Name: ${p.name}</h2>
        <h2>Price: ${(Math.round(p.unitprice * 100) / 100).toFixed(2)} $</h2>
        </div>
            <div class="the-image">
        <img src="${p.path}" alt="product_img" width="150" height="150"/>
        </div>
            <div class="the-amount">
            <h2>Quantity</h2><br/>
         <input class="counter" type="number" id="${
           p.sku
         }" value="1" step="1"  min="1" max="${
      p.quantity
    }" onKeyDown="return false"/>
        </div>
            <div class="the-button">
        <button id="add-to-cart" value="${
          p.sku
        }" onclick="removeFromCart(this.value)">Remove from cart</button>
        </div>
        `;

    divCart.setAttribute("class", "product");
    cartContainer.append(divCart);
  }
}

(async () => {
  let req = await fetch(`${the_url}/users/cart/${loggedUserId}`);
  let res = await req.json();
  products = res;
  cartContainer.innerHTML = "";
  fillCart(products);
})();

/** remove from cart */
let removeFromCart = async (e) => {
  let sku = e;
  let id = sessionStorage.getItem("id");
  let req = await fetch(`${the_url}/users/removefromcart/${sku}`, {
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
  let totalInvoice = 0;
  for (p of products) {
    let amount = document.getElementById(p.sku).value;
    totalInvoice += amount * p.unitprice;
    let newQuantity = p.quantity - amount;
    let updateObj = {
      sku: p.sku,
      category: p.category,
      name: p.name,
      quantity: newQuantity,
      unitprice: p.unitprice,
      path: p.path,
    };

    let req = await fetch(`${the_url}/products`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(updateObj),
    });
    let res = await req.json();
    console.log(res);
  }

  //now clearing the cart
  let req2 = await fetch(`${the_url}/users/clear/${loggedUserId}`, {
    method: "PUT",
  });
  let res = await req2.json();
  console.log(res);

  window.location.href = "home.html";
  alert("Order placed successfully, your total = " + totalInvoice + " $");
};
