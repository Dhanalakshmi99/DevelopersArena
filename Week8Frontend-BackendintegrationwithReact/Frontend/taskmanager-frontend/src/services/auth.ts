import api from "./api";

export const login = async (email: string, password: string) => {
  const response = await api.post("/auth/login", { email, password });
  return response.data; // { token: "JWT_TOKEN" }
};

export const register = async (name: string, email: string, password: string) => {
  const response = await api.post("/auth/register", { name, email, password });
  return response.data;
};
