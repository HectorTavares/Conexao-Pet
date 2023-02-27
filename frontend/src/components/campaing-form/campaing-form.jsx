import React, { useState } from "react";
import { TextField, CircularProgress } from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { postService, interestsService } from "@/services";
import { useNavigate } from "react-router-dom";
import { Dropzone } from "@/components";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { toast } from "react-toastify";
import { getFormattedDate } from "@/utils";
import { ROUTES } from "@/constants";
import { InterestInput, InterestList } from "@/components";
import { useEffect } from "react";

import "./style.scss";

export function CampaingForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [interests, setInterests] = useState([]);
  const [currentInterest, setCurrentInterest] = useState("");
  const [files, setFiles] = useState([]);
  const [interestsList, setInterestsList] = useState([]);
  const [endDate, setEndDate] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const { createPost } = postService();
  const { getInterests } = interestsService();

  useEffect(() => {
    const fetchInterests = async () => {
      try {
        const response = await getInterests();

        setInterestsList(response);
      } catch (error) {
        console.error("Erro ao obter lista de interesses.");
      }
    };

    fetchInterests();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const formData = new FormData();
      files.forEach((file) => {
        formData.append("fotos", file);
      });

      const interesses = interests.map((int) => {
        return int.id ? `${int.id}` : int.nome;
      });

      const formattedDate = getFormattedDate(endDate);

      const body = {
        titulo: title,
        descricao: description,
        interesses: interesses,
        dataEncerramento: formattedDate,
      };

      const data = JSON.stringify(body);

      formData.append("data", data);

      await createPost(formData);
      toast.success("Campanha Criada com sucesso");
      navigate(ROUTES.profile);
    } catch (error) {
      toast.error("Erro inesperado ao criar campanha.");
    } finally {
      setIsLoading(false);
    }
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

  return (
    <div className={`campaing ${isLoading ? "isLoading" : ""}`}>
      {isLoading ? (
        <CircularProgress style={{ color: "white", width: 100, height: 100 }} />
      ) : (
        <>
          <form className="campaing-form" onSubmit={handleSubmit}>
            <h2 className="campaing-form-title">
              Criação de campanha para arrecadação
            </h2>
            <TextField
              label="Título"
              value={title}
              onChange={(event) => setTitle(event.target.value)}
              variant="outlined"
              margin="normal"
              fullWidth
            />

            <TextField
              label="Descrição"
              value={description}
              onChange={(event) => setDescription(event.target.value)}
              variant="outlined"
              margin="normal"
              fullWidth
              multiline
              rows={4}
            />

            <div className="campaing-form-datepicker">
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker
                  label="Data de termino"
                  value={endDate}
                  onChange={(newValue) => {
                    setEndDate(newValue);
                  }}
                  renderInput={(params) => (
                    <TextField style={{ width: "100%" }} {...params} />
                  )}
                />
              </LocalizationProvider>
            </div>

            <p className="campaing-form-label">
              Escolha interesses da lista, caso sua escolha não esteja na lista
              utilize o campo abaixo.
            </p>

            <InterestInput
              currentInterest={currentInterest}
              setCurrentInterest={setCurrentInterest}
              interests={interests}
              handleInterestRemove={handleInterestRemove}
              handleInterestAdd={handleInterestAdd}
            />

            <Dropzone
              files={files}
              setFiles={setFiles}
              className="campaing-form-dropzone"
            />
            <button type="submit" className="campaing-form-submit-button">
              Enviar
            </button>
          </form>
          <InterestList
            interestsList={interestsList}
            handleInterestRemove={handleInterestRemove}
            handleSelectInterest={handleSelectInterest}
          />
        </>
      )}
    </div>
  );
}
