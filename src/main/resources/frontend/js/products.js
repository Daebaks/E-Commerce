let available;
let availableContainer = document.getElementById("in-stock-container");
const URL = "http://localhost:8080";

function populateInStock(available) {
  for (p of available) {
    let divAvailable = document.createElement("div");

    divAvailable.innerHTML = `
        <h2>Category: ${p.category}</h2>
        <h2>Name: ${p.name}</h2>
        <h2>Price: ${p.unitprice}</h2>
        <h2>Quantity in stock: ${p.quantity}</h2>
        <button id="add-to-cart">Add to cart</button>
        `;

    divAvailable.setAttribute("class", "product");
    availableContainer.append(divAvailable);
  }
}

(async () => {
  let req = await fetch(`${URL}/products/instock`);
  let res = await req.json();
  available = res;
  console.log(available);
  availableContainer.innerHTML = "";
  populateInStock(available);
})();

let sold;
let soldContainer = document.getElementById("out-ofstock-container");

function populateOutOfStock(sold) {
  for (p of sold) {
    let divSold = document.createElement("div");

    divSold.innerHTML = `
        <h2>Category: ${p.category}</h2>
        <h2>Name: ${p.name}</h2>
        <h2>Price: ${p.unitprice}</h2>
        <h2>Quantity in stock: ${p.quantity}</h2>
         `;

    divSold.setAttribute("class", "product");
    soldContainer.append(divSold);
  }
}

(async () => {
  let req = await fetch(`${URL}/products/outofstock`);
  let res = await req.json();
  sold = res;
  console.log(sold);
  soldContainer.innerHTML = "";
  populateOutOfStock(sold);
})();

function toggleOutOfStock() {
  let container = document.getElementById("out-ofstock-container");
  let style = window.getComputedStyle(container);
  let display = style.getPropertyValue("display");

  console.log(display);
  if (display == "none") {
    container.style.display = "inline-flex";
  } else {
    container.style.display = "none";
  }
}
