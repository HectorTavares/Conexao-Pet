import Avatar from "@mui/material/Avatar";
import { useState } from "react";
import { ContactButton, NotificationModal } from "@/components";

import { formatPhoneNumber } from "../../utils/formatPhoneNumber";
import { Button } from "@mui/material";
import { getUserIsVoluntary } from "@/utils";

import "./style.scss";

export const ProfileCard = ({
  image,
  name = "",
  address,
  phoneNumber,
  isMyProfile,
}) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const isVoluntary = getUserIsVoluntary();

  const handleModal = () => {
    setIsModalOpen(true);
  };

  const nameFirstLetter = name.charAt(0);
  return (
    <div className="profile-card">
      <div className="card-info-main-infos">
        <Avatar
          sx={{ borderRadius: "50px", width: "50px", height: "50px" }}
          src={image}
          alt={name}
          variant="square"
        >
          {nameFirstLetter}
        </Avatar>
        <div className="card-info">
          <h2>{name}</h2>
          <p>{phoneNumber ? formatPhoneNumber(phoneNumber) : null}</p>
        </div>
      </div>
      <div className="card-info-address">
        <p>{address}</p>
      </div>
      <div className="contact-number-container">
        {!isMyProfile ? <ContactButton number={phoneNumber} /> : null}
        {isMyProfile && isVoluntary ? (
          <>
            <Button
              className="activate-notification-modal-button"
              onClick={handleModal}
            >
              Ativar notificação
            </Button>
            <NotificationModal
              isModalOpen={isModalOpen}
              setIsModalOpen={setIsModalOpen}
            />
          </>
        ) : null}
      </div>
    </div>
  );
};
