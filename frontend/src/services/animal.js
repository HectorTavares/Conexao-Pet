import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const animalService = () => {
  const http = useHttp(`${API_BASE_URL}animal/`);

  const createAnimal = (formData) => {
    return http.post("cadastrar", formData, {
      headers: {
        "Content-Type": "multipart-file/form-data",
      },
    });
  };

  return {
    createAnimal,
  };
};
