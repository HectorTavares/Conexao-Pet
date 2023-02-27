import {
  Header,
  InterestInput,
  InterestList,
  NotificationModal,
} from "@/components";
import { voluntaryService, interestsService } from "@/services";
import { useNavigate, useLocation } from "react-router-dom";
import { ROUTES } from "@/constants";
import { Button } from "@mui/material";
import { toast } from "react-toastify";
import { useGlobalUser } from "../../context/user.context";
import { useEffect, useState, useRef } from "react";

import "./style.scss";

export const InterestPage = ({}) => {
  const [user] = useGlobalUser();
  const location = useLocation();
  const state = location?.state;
  const [interests, setInterests] = useState(state?.interesses || []);
  const [interestsList, setInterestsList] = useState([]);
  const [currentInterest, setCurrentInterest] = useState("");
  const modalRef = useRef(null);

  const isFirstAcess = JSON.parse(localStorage.getItem("isFirstAcess"));

  const [isModalOpen, setIsModalOpen] = useState(isFirstAcess);

  const { setVoluntaryInterests } = voluntaryService();
  const { getInterests } = interestsService();

  const navigate = useNavigate();

  useEffect(() => {
    if (!(user instanceof Promise) && user.interesses?.length) {
      setInterests(user.interesses);
    }
  }, [user.interesses]);

  useEffect(() => {
    fecthInterestList();
  }, []);

  const fecthInterestList = async () => {
    try {
      const response = await getInterests();
      setInterestsList(response);
    } catch (error) {
      toast.error("Erro ao buscar lista de interesses.");
    }
  };

  const handleInterestRemove = (interest) => {
    const newInt = interests.find((item) => item.nome === interest.nome);

    setInterests(interests.filter((item) => item.nome !== interest.nome));
    setInterestsList([...interestsList, newInt]);
  };

  const handleSelectInterest = (interest) => {
    const updatedList = interestsList.filter(
      (item) => item.nome !== interest.nome
    );
    setInterests([...interests, interest]);
    setInterestsList(updatedList);
  };

  const handleInterestAdd = (e) => {
    e.preventDefault();

    const existCurrentInterest = interests.find(
      (int) => int.nome === currentInterest
    );
    const existCurrentInterestAvailable = interestsList.find(
      (int) => int.nome === currentInterest
    );

    if (
      currentInterest.trim() !== "" &&
      !existCurrentInterest &&
      !existCurrentInterestAvailable
    ) {
      const currentInterestObj = {
        id: null,
        nome: currentInterest,
      };

      setInterests([...interests, currentInterestObj]);
      setCurrentInterest("");
    }
  };

  const setInfos = async () => {
    const payload = interests.map((int) => {
      return int.id ? int.id : int.nome;
    });
    await setVoluntaryInterests({ interesses: payload });
    localStorage.setItem("isFirstAcess", false);
  };

  const handleSubmit = async () => {
    if (interests.length >= 3) {
      try {
        await setInfos();

        toast.success("Interesses atualizados.");

        navigate(ROUTES.feed);
      } catch (error) {
        toast.error("Erro inesperado ao atualizar interesses.");
      }
    } else {
      toast.error("Escolha pelomenos 3 interesses");
    }
  };

  return (
    <section className="interest-page">
      {isFirstAcess ? null : <Header />}

      <NotificationModal
        ref={modalRef}
        isModalOpen={isModalOpen}
        setIsModalOpen={setIsModalOpen}
      />

      <section className="interest-content">
        <div className="new-interest-container">
          <div className="new-interest-content">
            <InterestInput
              currentInterest={currentInterest}
              setCurrentInterest={setCurrentInterest}
              interests={interests}
              handleInterestRemove={handleInterestRemove}
              handleInterestAdd={handleInterestAdd}
            />
            <div className="button-container">
              <Button
                className="submit-interests-button submit-button"
                onClick={handleSubmit}
              >
                Enviar
              </Button>
            </div>
          </div>
        </div>

        <InterestList
          interestsList={interestsList}
          handleInterestRemove={handleInterestRemove}
          handleSelectInterest={handleSelectInterest}
        />
      </section>
    </section>
  );
};
