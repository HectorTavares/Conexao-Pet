import { Avatar } from "@mui/material";
import MaleIcon from "@mui/icons-material/Male";
import FemaleIcon from "@mui/icons-material/Female";

import "./style.scss";

export const CardSearchAnimal = ({ animal, handleSelectAnimal }) => {
  const renderGender = () => {
    return animal.genero === "Macho" ? (
      <MaleIcon className="gender-icon" />
    ) : (
      <FemaleIcon className="gender-icon" />
    );
  };

  return (
    <div
      onClick={() => handleSelectAnimal(animal)}
      className={`list-card-animal-container`}
    >
      <div className="basic-info">
        <Avatar src={animal?.fotos[0]} name={animal.nome} />
        <div className="card-info">
          <h2>{animal.nome}</h2>
          <p>{animal.raca}</p>
        </div>
      </div>
      <div className="gender-icon">{renderGender()}</div>
    </div>
  );
};
