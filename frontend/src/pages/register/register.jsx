import { useState } from "react";
import {
  Radio,
  RadioGroup,
  FormControlLabel,
  FormControl,
  TextField,
  Button,
  Link,
  Alert,
} from "@mui/material/";

import InfoIcon from "@mui/icons-material/Info";

import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { voluntaryService, ongService, userService } from "@/services";
import { PasswordInput, Tooltip } from "@/components";
import { ROUTES, REGISTER_MESSAGES } from "@/constants";
import { getUserData } from "@/utils";
import { useGlobalUser } from "@/context/user.context";
import "./style.scss";

const VOLUNTARY = "voluntary";

export const Register = () => {
  const [accountType, setAccountType] = useState(VOLUNTARY);
  const [, setUser] = useGlobalUser();
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    passwordConfirmation: "",
    phone: "",
    cpf: "",
    adress: "",
  });

  const { voluntaryRegister } = voluntaryService();
  const { ongRegister } = ongService();
  const { loginAction } = userService();

  const navigate = useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const {
        name: nome,
        email,
        password: senha,
        passwordConfirmation: confirmacaoSenha,
        phone: telefone,
        cpf,
        adress: endereco,
      } = form;

      const usuario = {
        nome,
        senha,
        email,
        confirmacaoSenha,
        telefone,
        endereco,
      };

      if (accountType === VOLUNTARY) {
        const payload = {
          usuario,
          cpf,
        };
        await voluntaryRegister(payload);
      } else {
        await ongRegister({ usuario });
      }

      toast.success(REGISTER_MESSAGES.SUCCESS);
      localStorage.setItem("isFirstAcess", true);
      const payload = { email: form.email, password: form.password };
      const response = await loginAction(payload);
      const token = response.headers["x-auth-token"];
      const type = response.data.permissoes[0].name;
      setUserData(token, type);
    } catch (error) {
      console.error(error);
      toast.error(error.message || REGISTER_MESSAGES.ERROR);
    }
  };

  const setUserData = (token, type) => {
    localStorage.setItem("token", token);
    localStorage.setItem("type", type);
    const user = getUserData();

    setUser(user);
    navigate(ROUTES.feed);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedForm = { ...form, [name]: value };
    setForm(updatedForm);
  };

  const renderForm = () => {
    return (
      <form onSubmit={handleSubmit} className="register-container">
        <TextField
          onChange={handleChange}
          value={form.name}
          label={`Nome ${accountType !== VOLUNTARY ? "da Ong" : ""}`}
          name="name"
          variant="outlined"
          required
        />
        <PasswordInput passwordValue={form.password} onChange={handleChange} />

        <TextField
          onChange={handleChange}
          value={form.passwordConfirmation}
          name="passwordConfirmation"
          label="Confirmaçao de Senha"
          variant="outlined"
          type="password"
          autoComplete="current-password"
          required
        />
        {form.password !== form.passwordConfirmation ? (
          <Alert severity="error">As senhas não coincidem</Alert>
        ) : null}

        <TextField
          onChange={handleChange}
          value={form.email}
          label="Email"
          name="email"
          type="email"
          variant="outlined"
          required
        />

        {accountType === VOLUNTARY ? (
          <TextField
            onChange={handleChange}
            value={form.cpf}
            label="CPF"
            name="cpf"
            variant="outlined"
            required
          />
        ) : null}

        <TextField
          onChange={handleChange}
          value={form.adress}
          label="Endereço"
          name="adress"
          variant="outlined"
          required
        />

        <TextField
          onChange={handleChange}
          value={form.phone}
          label="Número do Whatsapp"
          name="phone"
          variant="outlined"
          type="number"
          required
        />

        <Tooltip
          text={
            "Coloque o número do seu Whatsapp, para o contato ser realizado corretamente por ele."
          }
        >
          <InfoIcon />
        </Tooltip>
        <div className="options">
          <Button
            disabled={form.password !== form.passwordConfirmation}
            type="submit"
            className="button"
            variant="contained"
          >
            Registrar
          </Button>
          <Link href={ROUTES.login}>Já tem conta? Logar.</Link>
        </div>
      </form>
    );
  };

  return (
    <section className="register">
      <h1>Registrar</h1>
      <div>
        <FormControl>
          <label htmlFor=""> Escolha o tipo de conta.</label>
          <RadioGroup
            aria-labelledby="controlled-radio-buttons-group"
            name="controlled-radio-buttons-group"
            value={accountType}
            onChange={(e) => setAccountType(e.target.value)}
            defaultValue="voluntary"
          >
            <FormControlLabel
              value="voluntary"
              control={<Radio />}
              label="Voluntario"
            />
            <FormControlLabel value="Ong" control={<Radio />} label="Ong" />
          </RadioGroup>
        </FormControl>
      </div>
      <div>{renderForm()}</div>
    </section>
  );
};
