import createGlobalState from "react-create-global-state";
import { getUserData } from "@/utils";

const type = localStorage.getItem("type");
const token = localStorage.getItem("token");

const user = type && token ? getUserData() : null;

const [useGlobalUser, UserGlobalProvider] = createGlobalState(user);

export { useGlobalUser, UserGlobalProvider };
