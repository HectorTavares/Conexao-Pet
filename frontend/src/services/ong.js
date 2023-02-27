import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const ongService = () => {
  const http = useHttp(`${API_BASE_URL}ong/`);

  const ongRegister = (payload) => {
    return http.post("cadastrar", payload);
  };

  const teste = () => {
    return http.get("teste");
  };

  return {
    ongRegister,
    teste,
  };
};
