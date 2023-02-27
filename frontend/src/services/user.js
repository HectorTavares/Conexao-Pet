import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const userService = () => {
  const http = useHttp(`${API_BASE_URL}`);

  const loginAction = ({ email, password }) => {
    return http.post(
      "login/public",
      {},
      {
        auth: {
          username: email,
          password: password,
        },
      }
    );
  };

  const getAutenticatedOng = () => {
    return http.get("user/ong");
  };

  const getAutenticatedVoluntary = () => {
    return http.get("user/voluntario");
  };

  const getProfileInfo = (userId) => {
    return http.get(`user/buscar/${userId}`);
  };

  const getUserByName = (name) => {
    return http.get(`user/buscar?nome=${name}`);
  };

  const updateUserInfo = (formData) => {
    return http.put("/user/atualizar", formData, {
      headers: {
        "Content-Type": "multipart-file/form-data",
      },
    });
  };

  return {
    loginAction,
    getAutenticatedOng,
    getAutenticatedVoluntary,
    getProfileInfo,
    getUserByName,
    updateUserInfo,
  };
};
