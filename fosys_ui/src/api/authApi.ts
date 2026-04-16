
import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8765",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    const apiError: any = new Error(
      error.response?.data?.data?.message ||
      error.response?.data?.message ||
      "Request failed"
    );

    apiError.status = error.response?.status;
    return Promise.reject(apiError);
  }
);

export const sendOtpApi = (mobileNumber: string) => {
  return api.post("/fosys/auth/send-otp", {
    mobileNumber: String(mobileNumber),
  });
};

export const loginWithOtpApi = (mobileNumber: string, otp: string) => {
  return api.post("/fosys/auth/verify-login", {
    mobileNumber,
    otp,
  });
};

export const loginWithPasswordApi = (userName: string, password: string) => {
  return api.post("/fosys/auth/login", {
    userName,
    password,
  });
};

export const createUserApi = (mobileNumber: string, password: string, confirmPassword: string, userType: string, firstName: string, lastName: string, userName: string, email: string) => {
  return api.post("/fosys/auth/user", {
    mobileNumber,
    password,
    confirmPassword,
    userName,
    userType,
    firstName,
    lastName,
    email
  });
};
