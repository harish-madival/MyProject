import { jwtDecode } from "jwt-decode";

interface JwtPayload {
  exp: number;
}

export const isTokenExpired = (token: string) => {
  const decoded = jwtDecode<JwtPayload>(token);
  return decoded.exp * 1000 < Date.now();
};

export const isLoggedIn = () => {
  return !!sessionStorage.getItem("authToken");
};

export const logout = () => {
  sessionStorage.removeItem("authToken");
  window.location.href = "/login";
};
