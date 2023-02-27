import { useAxios } from "./useAxios";

export function useHttp(baseURL, headers) {
  const token = localStorage.getItem("token");

  const instance = useAxios(baseURL, { ...headers, "x-auth-token": token });

  async function get(url) {
    try {
      const response = await instance.get(url);

      return response.data;
    } catch (error) {
      if (error.response.status === 401) {
        localStorage.removeItem("token");
        localStorage.removeItem("type");
      }
      throw new Error("Ocorreu um erro");
    }
  }

  const post = async (endpoint, data, options = {}) => {
    const res = await instance.post(endpoint, data, options);

    return res;
  };

  const put = async (endpoint, data, options = {}) => {
    const res = await instance.put(endpoint, data, options);

    return res;
  };

  const remove = async (endpoint, data, options = {}) => {
    const res = await instance.delete(endpoint, data, options);

    return res;
  };

  return {
    get,
    post,
    put,
    remove,
  };
}
