import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getHotels } from "../api/hotelApi";


interface Hotel {
  id: number;
  name: string;
  status: string;
}

export default function Menu() {
  const navigate = useNavigate();
  const [hotels, setHotels] = useState<Hotel[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchHotels();
  }, []);

  const fetchHotels = async () => {
    try {
      setLoading(true);
      const res = await getHotels();
      setHotels(res?.data);
    } catch (err: any) {
      console.log(err)
      setError("Failed to load hotels");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      {/* Header */}
      <div style={{ display: "flex", justifyContent: "space-between", marginBottom: 20 }}>
        <h2>Choose Hotel</h2>
        <button onClick={() => navigate("/menu/add-hotel")}> Add Hotel</button>
      </div>

      {/* Card Grid */}
      <div style={{
        display: "grid",
        gridTemplateColumns: "repeat(auto-fill, minmax(280px, 1fr))",
        gap: 20
      }}>
        {hotels.map(hotel => (
          <div
            key={hotel.id}
            style={{
              border: "1px solid #e0e0e0",
              borderRadius: 12,
              padding: 16,
              boxShadow: "0 2px 8px rgba(0,0,0,0.08)",
              position: "relative"
            }}
          >
            {/* Subscription Badge */}
            <h3>{hotel.name}</h3>
            <p>Status: <b>{hotel.status}</b></p>

            <div style={{ marginTop: 16, display: "flex", gap: 10 }}>
              <button onClick={() => navigate(`/menu/${hotel.id}`)}>
                View Menu
              </button>
              <button disabled={hotel.status !== "OPEN"}>
                Add Menu
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
