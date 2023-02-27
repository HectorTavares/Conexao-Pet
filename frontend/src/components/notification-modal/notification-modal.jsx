import WhatsAppIcon from "@mui/icons-material/WhatsApp";
import CloseIcon from "@mui/icons-material/Close";

import { Button, Modal } from "@mui/material";

import "./style.scss";

export const NotificationModal = ({ isModalOpen, setIsModalOpen }) => {
  const handleClose = () => {
    setIsModalOpen(false);
  };

  return (
    <Modal
      open={isModalOpen}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <div className="notification-modal-container">
        <div className="notification-container">
          <CloseIcon className="exit-button" onClick={handleClose} />
          <div class="modal__content">
            <h2 class="modal__content__title">
              Receba notificações por WhatsApp!
            </h2>
            <p class="modal__content__text">
              Nosso sistema possui uma funcionalidade de notificações por
              WhatsApp, mas para recebê-las, você precisa clicar no botão abaixo
              e enviar uma mensagem para nosso número. A mensagem já estará
              pronta para envio.
            </p>
            <a
              href="https://api.whatsapp.com/send/?phone=%2B14155238886&text=join+respect-leather&type=phone_number&app_absent=0"
              class="modal__content__link"
              target="_blank"
            >
              Receber Notificações
              <WhatsAppIcon />
            </a>
            <p class="modal__content__footer">
              Você pode cancelar a qualquer momento. Suas informações serão
              utilizadas apenas para enviar notificações de campanhas de ONGs
              que possuem interesses em comum com você.
            </p>
          </div>
        </div>
      </div>
    </Modal>
  );
};
