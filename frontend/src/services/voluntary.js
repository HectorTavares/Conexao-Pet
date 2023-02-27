import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const voluntaryService = () => {
  const http = useHttp(`${API_BASE_URL}voluntario/`);

  const voluntaryRegister = (payload) => {
    return http.post("cadastrar", payload);
  };

  const setVoluntaryInterests = (payload) => {
    return http.post("/adicionar/interesses", payload);
  };

  const getFeedPosts = () => {
    return http.get("/feed");
  };

  const followOng = (ongId) => {
    return http.post(`/seguir/${ongId}`);
  };

  return {
    voluntaryRegister,
    setVoluntaryInterests,
    getFeedPosts,
    followOng,
  };
};
