import { useAppDispatch, useAppSelector } from "../../app/hooks";
import { login, logout } from "./authSlice";

export const useAuth = () => {
  const auth = useAppSelector(s => s.auth);
  const dispatch = useAppDispatch();

  return {
    ...auth,
    login: (user: string) => dispatch(login(user)),
    logout: () => dispatch(logout())
  };
};
