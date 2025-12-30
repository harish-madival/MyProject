import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addHotel } from "../api/hotelApi";

export default function AddHotel() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [status, setStatus] = useState("");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    setError("");
    setSuccess("");

    // basic validation
    if (!name || !status) {
      setError("All fields are required");
      return;
    }

    try {
      setLoading(true);

      await addHotel({
        name,
        status,
      });

      setSuccess("Hotel added successfully");

      // optional: redirect after success
      setTimeout(() => navigate("/menu"), 1000);
    } catch (err: any) {
      setError(err.message || "Failed to add hotel");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container d-flex justify-content-center mt-5">
      <div className="card p-4 shadow" style={{ width: "450px" }}>
        <h4 className="mb-3 text-center">Add Hotel</h4>

        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}

        <form onSubmit={handleSubmit}>
          <input
            className="form-control mb-3"
            placeholder="Hotel Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            className="form-control mb-3"
            placeholder="Status"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          />

          <button className="btn btn-primary w-100" disabled={loading}>
            {loading ? "Saving..." : "Add Hotel"}
          </button>
        </form>
      </div>
    </div>
  );
}
