import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { login as loginApi } from "../services/auth";

export const useAuth = () => {
  const auth = useContext(AuthContext);

  if (!auth) {
    throw new Error("useAuth must be used within an AuthProvider");
  }

  const login = async (email: string, password: string) => {
    const data = await loginApi(email, password);
     // store token in context & localStorage
  };

  const logout = () => {
    auth.logout();
  };

  const isAuthenticated = () => !!auth.token;

  return { token: auth.token, login, logout, isAuthenticated };
};
