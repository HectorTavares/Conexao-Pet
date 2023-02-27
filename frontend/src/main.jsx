import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import { router } from "./routes";
import "./index.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { UserGlobalProvider } from "./context/user.context";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ToastContainer />
    <UserGlobalProvider>
      <RouterProvider router={router} />
    </UserGlobalProvider>
  </React.StrictMode>
);
