import React, { useState } from "react";
import {
  TextField,
  FormControl,
  InputLabel,
  Select,
  Checkbox,
  MenuItem,
  FormGroup,
  FormControlLabel,
  FormLabel,
  Button,
} from "@mui/material";
import { toast } from "react-toastify";
import InfoIcon from "@mui/icons-material/Info";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { Header, Dropzone, Tooltip } from "@/components";
import { getFormattedDate } from "@/utils";
import { animalService } from "@/services";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "@/constants";

import "./style.scss";

export const AnimalPage = () => {
  const [form, setForm] = useState({
    name: "",
    gender: "",
    size: "",
    isVaccinated: false,
    breed: "",
    vaccinationStatus: "",
    characteristics: "",
    species: "",
  });

  const { createAnimal } = animalService();

  const [birthDate, setBirthDate] = useState();
  const navigate = useNavigate();

  const [files, setFiles] = useState([]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setForm((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleCheckboxChange = (event) => {
    const { name, checked } = event.target;
    setForm((prevState) => ({ ...prevState, [name]: checked }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData();
      files.forEach((file) => {
        formData.append("fotos", file);
      });

      const formattedDate = getFormattedDate(birthDate);

      const {
        species: especie,
        breed: raca,
        name: nome,
        gender: genero,
        size: porte,
        characteristics: caracteristicas,
        vaccinationStatus: situacaoVacinal,
      } = form;

      const body = {
        nome,
        raca,
        situacaoVacinal,
        caracteristicas,
        dataDeNascimento: formattedDate,
        especie,
        genero,
        porte,
      };

      const data = JSON.stringify(body);

      formData.append("data", data);

      await createAnimal(formData);
      toast.success("Animal Criado com sucesso");
      navigate(ROUTES.profile);
    } catch (error) {
      toast.error("Erro inesperado ao criar animal.");
    }
  };

  return (
    <section className="animal-page">
      <Header />
      <div className="animal-page-content">
        <form className="animal-form" onSubmit={handleSubmit}>
          <div className="flex">
            <TextField
              label="Nome"
              name="name"
              value={form.name}
              onChange={handleInputChange}
              fullWidth
              required
            />
            <TextField
              label="Raça"
              name="breed"
              value={form.breed}
              onChange={handleInputChange}
              fullWidth
              required
            />
          </div>
          <div className="flex">
            <FormControl fullWidth required>
              <InputLabel>Especie</InputLabel>
              <Select
                name="species"
                value={form.species}
                onChange={handleInputChange}
              >
                <MenuItem value="CACHORRO">Cachorro</MenuItem>
                <MenuItem value="GATO">Gato</MenuItem>
                <MenuItem value="COELHO">Coelho</MenuItem>
                <MenuItem value="RATO">Rato</MenuItem>
              </Select>
            </FormControl>
            <FormControl fullWidth required>
              <InputLabel>Gênero</InputLabel>
              <Select
                name="gender"
                value={form.gender}
                onChange={handleInputChange}
              >
                <MenuItem value="MACHO">Macho</MenuItem>
                <MenuItem value="FEMEA">Fêmea</MenuItem>
              </Select>
            </FormControl>
            <FormControl fullWidth required>
              <InputLabel>Porte</InputLabel>
              <Select
                name="size"
                value={form.size}
                onChange={handleInputChange}
              >
                <MenuItem value="PEQUENO">Pequeno</MenuItem>
                <MenuItem value="MEDIO">Médio</MenuItem>
                <MenuItem value="GRANDE">Grande</MenuItem>
              </Select>
            </FormControl>
            <Tooltip text={"Coloque a data de nascimento aproximada."}>
              <InfoIcon />
            </Tooltip>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Data de nascimento"
                value={birthDate}
                name="birthDate"
                onChange={(newValue) => {
                  setBirthDate(newValue);
                }}
                renderInput={(params) => (
                  <TextField
                    name="birthDate"
                    style={{ width: "100%" }}
                    {...params}
                  />
                )}
              />
            </LocalizationProvider>
          </div>

          <div className="flex">
            <FormGroup>
              <FormControlLabel
                control={
                  <Checkbox
                    name="isVaccinated"
                    checked={form.isVaccinated}
                    onChange={handleCheckboxChange}
                  />
                }
                label="É Vacinado?"
              />
            </FormGroup>
            {form.isVaccinated ? (
              <TextField
                label="Vacinas"
                name="vaccinationStatus"
                value={form.vaccinationStatus}
                onChange={handleInputChange}
                multiline
                fullWidth
                required
              />
            ) : null}
          </div>

          <TextField
            label="Caracteristicas"
            name="characteristics"
            value={form.characteristics}
            onChange={handleInputChange}
            multiline
            fullWidth
            required
          />

          <Dropzone files={files} setFiles={setFiles} />

          <div className="button-container">
            <Button className="submit-button" type="submit">
              Criar
            </Button>
          </div>
        </form>
      </div>
    </section>
  );
};
