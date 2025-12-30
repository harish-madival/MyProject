
export default function Home() {

  const foods = [
    { id: 1, name: "Pizza", price: 199, image: "../assets/pizza.jpg", description: "Cheese pizza" },
    { id: 2, name: "Burger", price: 99, image: "../assets/burger.jpg", description: "Veg burger" },
  ];


  return (
    <div className="container my-5">
      <h2 className="mb-4 fw-bold">Popular Items</h2>


      <div className="row g-4">
        {foods.map((f) => (
          <div className="col-md-3" key={f.id}>
            
          </div>
        ))}
      </div>
    </div>
  );
}