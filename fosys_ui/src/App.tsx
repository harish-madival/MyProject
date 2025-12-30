import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Cart from "./pages/cart";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Menu from "./pages/Menu";
import ProtectedRoute from "./routes/ProtectedRoute";
import AddHotel from "./pages/AddHotel";

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />

        <Route element={<ProtectedRoute />}>
          <Route path="/menu" element={<Menu />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/menu/add-hotel" element={<AddHotel />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
