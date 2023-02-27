import { userService } from "@/services";

export const getUserData = async () => {
  const { getAutenticatedOng, getAutenticatedVoluntary } = userService();

  const type = localStorage.getItem("type");
  const token = localStorage.getItem("token");

  try {
    if (token) {
      if (type === "Voluntário") {
        const response = await getAutenticatedVoluntary();
        return response;
      } else {
        const response = await getAutenticatedOng();
        return response;
      }
    }
  } catch (error) {
    localStorage.removeItem("token");
    localStorage.removeItem("type");
  }
};
