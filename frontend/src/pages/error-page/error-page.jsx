import React from "react";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "@/constants";
import "./style.scss";

export function ErrorPage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate(ROUTES.login);
  };

  return (
    <div className="error-container">
      <h1>Desculpe, infelizmente ocorreu um erro inesperado 😞</h1>
      <h2>Tente novamente mais tarde.</h2>
      <button onClick={() => window.location.reload()}>
        Recarregar a página
      </button>
      <button onClick={handleLogout}>Sair</button>
    </div>
  );
}
