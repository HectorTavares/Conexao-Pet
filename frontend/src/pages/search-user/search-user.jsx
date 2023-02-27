import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import {
  Header,
  CardSearchUser,
  CardSearchAnimal,
  AnimalCard,
} from "@/components";
import { Modal } from "@mui/material";

import "./style.scss";

export const SearchUser = () => {
  const [isModalAnimalOpen, setIsModalAnimalOpen] = useState(false);
  const [animalSelected, setAnimalSelected] = useState({});

  const location = useLocation();

  const handleSelectAnimal = (animal) => {
    setAnimalSelected(animal);
    setIsModalAnimalOpen(true);
  };

  const { searchResult = [] } = location?.state;

  return (
    <>
      <Header />
      <div className="search-user-container">
        <Modal
          open={isModalAnimalOpen}
          onClose={() => setIsModalAnimalOpen(false)}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <AnimalCard
            animal={animalSelected}
            ongNumber={animalSelected.telefoneDono}
          />
        </Modal>
        <div className="user-list">
          <div className="list-tittle">
            <h2>Voluntários</h2>
          </div>
          <div className="list-container">
            {searchResult?.voluntarios.map((user) => {
              return (
                <CardSearchUser
                  key={user.id}
                  id={user.id}
                  nome={user.nome}
                  foto={user.foto}
                  endereco={user.endereco}
                  isVoluntario={user.isVoluntario}
                />
              );
            })}
          </div>
        </div>
        <div className="user-list">
          <div className="list-tittle">
            <h2>Ongs</h2>
          </div>
          <div className="list-container">
            {searchResult?.ongs.map((user) => {
              return (
                <CardSearchUser
                  key={user.id}
                  id={user.id}
                  nome={user.nome}
                  foto={user.foto}
                  endereco={user.endereco}
                  isVoluntario={user.isVoluntario}
                />
              );
            })}
          </div>
        </div>

        <div className="user-list">
          <div className="list-tittle">
            <h2>Animais</h2>
          </div>
          <div className="list-container">
            {searchResult?.animais.map((animal) => {
              return (
                <CardSearchAnimal
                  key={animal.id}
                  animal={animal}
                  handleSelectAnimal={handleSelectAnimal}
                />
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
};
