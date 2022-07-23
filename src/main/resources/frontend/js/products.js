let inStockProducts;
let inStockContainer = document.getElementById("in-stock-container");
const URL = "http://localhost:8080";

function populateInStock(inStockProducts) {
  for (p of inStockProducts) {
    let div = document.createElement("div");

    div.innerHTML = `
        <h2>Category: ${p.category}</h2>
        <h2>Name: ${p.name}</h2>
        <h2>Price: ${p.unitprice}</h2>
        <h2>Quantity in stock: ${p.quantity}</h2>
        <button id="add-to-cart">Add to cart</button>
        `;

    div.setAttribute("class", "product");
    inStockContainer.append(div);
  }
}

(async () => {
  let req = await fetch(`${URL}/products/instock`);
  let res = await req.json();
  inStockProducts = res;
  console.log(inStockProducts);
  inStockContainer.innerHTML = "";
  populateInStock(inStockProducts);
})();
