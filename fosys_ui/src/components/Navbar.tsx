import { Link, useNavigate } from "react-router-dom";
import { isLoggedIn, logout } from "../utils/auth";

export default function Navbar() {

    const navigate = useNavigate();
    const loggedIn = isLoggedIn();

    function handleLogout() {
        logout();
        navigate("/login");
    }

    return (
        <nav className="navbar navbar-expand-lg bg-white shadow-sm px-3">
            <Link className="navbar-brand fw-bold text-danger" to="/">FoSys</Link>


            <button
                className="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#nav"
            >
                <span className="navbar-toggler-icon"></span>
            </button>


            <div className="collapse navbar-collapse" id="nav">
                <ul className="navbar-nav ms-auto">
                    <li className="nav-item"><Link className="nav-link" to="/menu">Menu</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/cart">Cart</Link></li>
                    {!loggedIn ? (
                        <li className="nav-item"><Link className="btn btn-primary ms-2" to="/login">Login</Link></li>
                    ) : (
                        <button
                            className="btn btn-outline-danger"
                            onClick={handleLogout}
                        >
                            Logout
                        </button>
                    )}
                </ul>
            </div>
        </nav>
    );
}