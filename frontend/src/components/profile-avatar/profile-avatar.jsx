import { Avatar } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "@/constants";

import "./style.scss";

export const ProfileAvatar = ({ profilePicture, name = "", userId }) => {
  const nameFirstLetter = name.charAt(0);

  const navigate = useNavigate();

  const goToProfile = () => {
    if (userId) {
      navigate(ROUTES.profile, { state: { userId: userId } });
    } else {
      navigate(ROUTES.profile, { state: { userId: undefined } });
    }
  };

  return (
    <button onClick={goToProfile} className="profile-avatar">
      <Avatar alt={name} src={profilePicture}>
        {nameFirstLetter}
      </Avatar>
    </button>
  );
};
