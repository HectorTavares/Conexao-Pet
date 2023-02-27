export const getUserIsVoluntary = () => {
  return localStorage.getItem("type") === "Voluntário";
};
