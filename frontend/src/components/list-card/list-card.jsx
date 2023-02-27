import { ProfileAvatar } from "../index";
import MaleIcon from "@mui/icons-material/Male";
import FemaleIcon from "@mui/icons-material/Female";
import "./style.scss";
import { Avatar } from "@mui/material";

export const ListCard = ({
  title,
  subtitle,
  image,
  gender,
  isAnimal = false,
  userId,
  onClickAnimal = null,
  animal,
}) => {
  const renderGender = () => {
    if (isAnimal) {
      return gender === "Macho" ? (
        <MaleIcon className="gender-icon" />
      ) : (
        <FemaleIcon className="gender-icon" />
      );
    } else {
      return null;
    }
  };

  const handleOnClick = () => {
    if (animal) {
      onClickAnimal(animal);
    }
  };

  return (
    <div
      onClick={handleOnClick}
      className={`list-card ${animal ? "cliclable" : ""}`}
    >
      <div className="basic-info">
        {isAnimal ? (
          <Avatar src={image} name={title} />
        ) : (
          <ProfileAvatar profilePicture={image} name={title} userId={userId} />
        )}
        <div className="card-info">
          <h2>{title}</h2>
          <p>{subtitle}</p>
        </div>
      </div>
      <div className="gender-icon">{renderGender()}</div>
    </div>
  );
};
