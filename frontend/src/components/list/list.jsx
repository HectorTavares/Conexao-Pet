import React, { useState } from "react";
import { ListCard, AnimalCard } from "@/components";
import Modal from "@mui/material/Modal";

import "./style.scss";

export const List = ({ itens = [], title, isOng, handleSelectAnimal }) => {
  return (
    <>
      <ul className="list">
        <h2 className="list-title">{title}</h2>
        {itens.map((item, index) => (
          <li key={index}>
            {isOng ? (
              <ListCard
                title={item.nome}
                subtitle={item.endereco}
                image={item.foto}
                userId={item.idUsuario}
                isAnimal={!isOng}
              />
            ) : (
              <>
                <ListCard
                  animal={item}
                  title={item.nome}
                  subtitle={item.raca}
                  image={item.fotos[0]}
                  gender={item.genero}
                  isAnimal={true}
                  onClickAnimal={handleSelectAnimal}
                />
              </>
            )}
          </li>
        ))}
      </ul>
    </>
  );
};
