import React, { useState, forwardRef } from "react";
import { TextField, Button } from "@mui/material";
import { SingleDropZone } from "@/components";
import { userService } from "@/services/user";
import { toast } from "react-toastify";
import "./style.scss";

export const UpdateInfoForm = forwardRef(
  ({ phone = "", name = "", adress = "", profilePicture = null }, ref) => {
    const [form, setForm] = useState({
      phone: phone,
      name: name,
      adress: adress,
    });

    const [image, setImage] = useState(profilePicture);

    const handleChange = (e) => {
      const { name, value } = e.target;
      const updatedForm = { ...form, [name]: value };
      setForm(updatedForm);
    };

    const { updateUserInfo } = userService();

    const handleSubmit = async (e) => {
      e.preventDefault();

      try {
        const formData = new FormData();

        if (image instanceof File) {
          formData.append("foto", image);
        } else {
          formData.append("foto", null);
        }

        const body = {
          telefone: form.phone,
          nome: form.name,
          endereco: form.adress,
        };
        const data = JSON.stringify(body);

        formData.append("data", data);

        await updateUserInfo(formData);
        toast.success("Perfil Atualizado com sucesso.");
        window.location.reload();
      } catch (error) {
        toast.error("Erro inesperado ao atualizar perfil.");
      }
    };

    return (
      <div className="update-infos-form" ref={ref}>
        <form onSubmit={handleSubmit}>
          <h2>Informações do Perfil</h2>

          <TextField
            onChange={handleChange}
            value={form.name}
            label={`Nome`}
            name="name"
            variant="outlined"
            required
          />
          <TextField
            onChange={handleChange}
            value={form.phone}
            label={`Telefone`}
            name="phone"
            variant="outlined"
            inputProps={{ inputMode: "numeric", pattern: "[0-9]*" }}
            required
          />
          <TextField
            onChange={handleChange}
            value={form.adress}
            label={`Endereço`}
            name="adress"
            variant="outlined"
            required
          />
          <p>Foto de perfil:</p>
          <SingleDropZone image={image} setImage={setImage} />
          <div className="button-container">
            <Button className="submit-button" type="submit">
              Enviar
            </Button>
          </div>
        </form>
      </div>
    );
  }
);
