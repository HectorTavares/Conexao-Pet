import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const interestsService = () => {
  const http = useHttp(`${API_BASE_URL}interesse/`);

  const getInterests = () => {
    return http.get("/listar");
  };

  return {
    getInterests,
  };
};
