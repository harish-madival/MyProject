import axios from "axios";
import { isTokenExpired, logout } from "../utils/auth";

const api = axios.create({
  baseURL: "http://localhost:8765",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("authToken");

    if (token) {
      if (isTokenExpired(token)) {
        logout(); // ⛔ expired → logout immediately
        return Promise.reject("Token expired");
      }

      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

export const getHotels = async () => {
  const response = await api.get("/fosys/onboard/hotel");
  return response.data;
};

export interface AddHotelRequest {
  name: string;
  status: string;
}

export const addHotel = async (payload: AddHotelRequest) => {
  const response = await api.post("/fosys/onboard/hotel", payload);
  return response.data;
};
