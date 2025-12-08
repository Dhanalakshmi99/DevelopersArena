import { useState } from "react";
import "./App.css";

const products = [
    {
        id: 1,
        name: "Smart Tv",
        price: 15999.99,
        image: "https://production-inventory-service.s3.ap-south-1.amazonaws.com/kodnest-documents/salessavvy/COSTOMER+IMAGES/tv1.png"
    },
    {
        id: 2,
        name: "Smart Shirt",
        price: 599.99,
        image: "https://production-inventory-service.s3.ap-south-1.amazonaws.com/kodnest-documents/salessavvy/COSTOMER+IMAGES/shirt1.png"
    },
    {
        id: 3,
        name: "Blue Jeans",
        price: 1099.99,
        image: "https://production-inventory-service.s3.ap-south-1.amazonaws.com/kodnest-documents/salessavvy/COSTOMER+IMAGES/pant1.png"
    },
    {
        id: 4,
        name: "Smart Phone",
        price: 24999.99,
        image: "https://production-inventory-service.s3.ap-south-1.amazonaws.com/kodnest-documents/salessavvy/COSTOMER+IMAGES/ph1.png"
    }
];

export default function App() {
  const [cart, setCart] = useState([]);
  const [open, setOpen] = useState(false);

  // Add item to cart
  const addToCart = (id) => {
    const product = products.find((p) => p.id === id);
    const exists = cart.find((c) => c.id === id);

    if (exists) {
      setCart(
        cart.map((item) =>
          item.id === id ? { ...item, qty: item.qty + 1 } : item
        )
      );
    } else {
      setCart([...cart, { ...product, qty: 1 }]);
    }
  };

  // Remove item
  const removeItem = (id) => {
    setCart(cart.filter((item) => item.id !== id));
  };

  // Total price
  const total = cart.reduce((sum, i) => sum + i.price * i.qty, 0);

  return (
    <div>
      {/* Header */}
      <header className="header">
        <h1>ShopNow</h1>
        <button className="cart-btn" onClick={() => setOpen(!open)}>
          Cart ({cart.length})
        </button>
      </header>

      {/* Product List */}
      <div className="products">
        {products.map((p) => (
          <div className="product" key={p.id}>
            <img src={p.image} alt={p.name} />
            <h3>{p.name}</h3>
            <p>{p.price.toFixed(2)}</p>
            <button onClick={() => addToCart(p.id)}>Add to Cart</button>
          </div>
        ))}
      </div>

      {/* Cart Drawer */}
      <div className={`cart ${open ? "open" : ""}`}>
        <h2>Your Cart</h2>

        <div className="cart-items">
          {cart.map((item) => (
            <div className="cart-item" key={item.id}>
              <span>
                {item.name} x {item.qty}
              </span>
              <button onClick={() => removeItem(item.id)}>❌</button>
            </div>
          ))}
        </div>

        <div id="total">Total: {total.toFixed(2)}</div>
        <button id="checkout-btn">Checkout</button>
      </div>
    </div>
  );
}