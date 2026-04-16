import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  createUserApi,
  loginWithOtpApi,
  loginWithPasswordApi,
  sendOtpApi,
} from "../api/authApi";

type Mode = "mobile" | "password";

export default function Login() {
  const [mode, setMode] = useState<Mode>("mobile");

  const [mobile, setMobile] = useState("");
  const [otp, setOtp] = useState("");
  const [otpSent, setOtpSent] = useState(false);

  const [password, setPassword] = useState("");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [showCreateUser, setShowCreateUser] = useState(false);
  const [displayCreateUser, setDisplayCreateUser] = useState(false);
  const [hide, setHide] = useState(true);
  const [hideOtpLogin, setHideOtpLogin] = useState(true);
  const [userType, setUserType] = useState("");

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");



  const navigate = useNavigate();
  const location = useLocation();

  const redirectTo = location.state?.from || "/";

  async function sendOtp(e: React.FormEvent) {
    e.preventDefault();
    setError("");

    try {
      setLoading(true);
      console.log("Before API");
      const res = await sendOtpApi(mobile);
      console.log("After API", res);
      setOtpSent(true);
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  async function loginWithOtp(e: React.FormEvent) {
    e.preventDefault();
    setError("");

    try {
      setLoading(true);
      const res = await loginWithOtpApi(mobile, otp);
      sessionStorage.setItem("authToken", res?.data?.token);
      console.log("OTP Login Success:", res);
      navigate(redirectTo, { replace: true });
    } catch (err: any) {
      if (err.status === 404) {
        setShowCreateUser(true);
        setOtpSent(false);
        setHideOtpLogin(false);
      } else {
        setError(err.message);
      }
    } finally {
      setLoading(false);
    }
  }

  function registerUser() {
    setHide(true);
    setDisplayCreateUser(true);
  }

  async function handleCreateUser(e: React.FormEvent) {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      await createUserApi(mobile, password, confirmPassword ,userType, firstName, lastName, userName, email);

      const res = await loginWithPasswordApi(mobile, password);
      sessionStorage.setItem("authToken", res?.data?.token);
      navigate(redirectTo, { replace: true });
      console.log("User created & logged in");
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }


  async function loginWithPassword(e: React.FormEvent) {
    e.preventDefault();
    setError("");

    try {
      setLoading(true);
      const res = await loginWithPasswordApi(userName, password);
      console.log("Password Login Success:", res);
      sessionStorage.setItem("authToken", res?.data?.token);
      navigate(redirectTo, { replace: true });
    } catch (err: any) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  function setModeAndError(p0: string): void {
    if (p0 === "mobile") {
      setMode("mobile");
      setError("");
      setShowCreateUser(false)
      setOtpSent(false)
      setHide(true);
      setDisplayCreateUser(false);
    } else {
      setMode("password");
      setError("");
      setHide(false);
      setDisplayCreateUser(false);
    }


  }

  return (
    <div className="container d-flex justify-content-center align-items-center min-vh-100">
      <div className="card p-4 shadow" style={{ width: "380px" }}>
        <h4 className="text-center mb-3">Login</h4>

        {/* Switch */}
        <div className="btn-group w-100 mb-3">
          <button
            className={`btn btn-outline-primary ${mode === "mobile" ? "active" : ""}`}
            onClick={() => setModeAndError("mobile")}
          >
            Mobile
          </button>
          <button
            className={`btn btn-outline-primary ${mode === "password" ? "active" : ""}`}
            onClick={() => setModeAndError("password")}
          >
            User Name
          </button>
        </div>

        {error && <div className="alert alert-danger">{error}</div>}

        {/* Mobile Login */}
        {mode === "mobile" && (
          <>
            {!otpSent && !showCreateUser && (
              <form onSubmit={sendOtp}>
                <input
                  className="form-control mb-3"
                  placeholder="Mobile number"
                  value={mobile}
                  onChange={(e) => setMobile(e.target.value)}
                  required
                />
                <button className="btn btn-primary w-100" disabled={loading}>
                  {loading ? "Sending..." : "Send OTP"}
                </button>
              </form>
            )}

            {otpSent && (
              <form onSubmit={loginWithOtp}>
                <input
                  className="form-control mb-3"
                  placeholder="Enter OTP"
                  value={otp}
                  onChange={(e) => setOtp(e.target.value)}
                  required
                />
                {hideOtpLogin && (
                  <button className="btn btn-success w-100" disabled={loading}>
                    {loading ? "Verifying..." : "Login"}
                  </button>
                )}
              </form>
            )}

            {showCreateUser && (

              <form onSubmit={handleCreateUser}>
                <select
                  className="form-control mb-3"
                  value={userType}
                  onChange={(e) => setUserType(e.target.value)}
                >
                  <option value="">Select User Type</option>
                  
                  <option value="PARTNER">PARTNER</option>
                  <option value="ENTERPRISE">ENTERPRISE</option>
                </select>

                <input
                  className="form-control mb-3"
                  placeholder="Mobile number"
                  value={mobile}
                  onChange={(e) => setMobile(e.target.value)}
                  required
                />

                <input
                  type="firstName"
                  className="form-control mb-3"
                  placeholder="First Name"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                />

                <input
                  type="lastName"
                  className="form-control mb-3"
                  placeholder="First Name"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                />

                <input
                  type="userName"
                  className="form-control mb-3"
                  placeholder="User Name"
                  value={userName}
                  onChange={(e) => setUserName(e.target.value)}
                  required
                />

                <input
                  type="email"
                  className="form-control mb-3"
                  placeholder="Email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />

                <input
                  type="password"
                  className="form-control mb-3"
                  placeholder="Create Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />

                <input
                  type="confirmPassword"
                  className="form-control mb-3"
                  placeholder="Confirm Password"
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  required
                />

                <button className="btn btn-success w-100" disabled={loading}>
                  {loading ? "Creating..." : "Create Account"}
                </button>
              </form>
            )}
          </>
        )}


        {/* Username Password */}
        {mode === "password" && !hide && (
          <form onSubmit={loginWithPassword}>
            <input
              className="form-control mb-3"
              placeholder="User Name"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
            />
            <input
              type="password"
              className="form-control mb-3"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button className="btn btn-success w-100" disabled={loading}>
              {loading ? "Logging in..." : "Login"}
            </button>
          </form>
        )}

        {!hide && (
          <button className="btn" onClick={registerUser}>
            RegisterUser
          </button>
        )}
        {displayCreateUser && (

          <form onSubmit={handleCreateUser}>
            <select
              className="form-control mb-3"
              value={userType}
              onChange={(e) => setUserType(e.target.value)}
            >
              <option value="">Select User Type</option>
              <option value="PARTNER">PARTNER</option>
              <option value="ENTERPRISE">ENTERPRISE</option>
            </select>

            <input
              className="form-control mb-3"
              placeholder="Mobile number"
              value={mobile}
              onChange={(e) => setMobile(e.target.value)}
              required
            />

            <input
              type="firstName"
              className="form-control mb-3"
              placeholder="First Name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              required
            />

            <input
              type="lastName"
              className="form-control mb-3"
              placeholder="First Name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              required
            />

            <input
              type="userName"
              className="form-control mb-3"
              placeholder="User Name"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              required
            />

            <input
              type="email"
              className="form-control mb-3"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <input
              type="password"
              className="form-control mb-3"
              placeholder="Create Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            <input
              type="confirmPassword"
              className="form-control mb-3"
              placeholder="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />

            <button className="btn btn-success w-100" disabled={loading}>
              {loading ? "Creating..." : "Create Account"}
            </button>
          </form>
        )}

      </div>
    </div>
  );
}
