import { Header, ProfileAvatar } from "@/components";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "@/constants";

import "./style.scss";

export const CardSearchUser = ({ id, nome, foto, endereco = "" }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(ROUTES.profile, { state: { userId: id } });
  };

  return (
    <button className="card-search-user-container" onClick={handleClick}>
      <div className="profile-avatar-infos">
        <div className="profile-avatar">
          <ProfileAvatar profilePicture={foto} name={nome} userId={id} />
        </div>
        <div className="profile-main-infos">
          <h2 className="subtitle">{nome}</h2>
          <p>{endereco}</p>
        </div>
      </div>
    </button>
  );
};
