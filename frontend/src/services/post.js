import { useHttp } from "../hooks/api/useHttp";
import { API_BASE_URL } from "../constants/general";

export const postService = () => {
  const http = useHttp(`${API_BASE_URL}ong/`);

  const createPost = (formData) => {
    return http.post("/campanha/criar", formData, {
      headers: {
        "Content-Type": "multipart-file/form-data",
      },
    });
  };

  return {
    createPost,
  };
};
