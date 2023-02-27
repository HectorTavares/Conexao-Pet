import { TextField, Button, Link } from "@mui/material/";
import { useNavigate } from "react-router-dom";
import { TITLE, ABREVIATED_TITLE, ROUTES, LOGIN_MESSAGES } from "@/constants";
import { userService } from "@/services";
import { toast } from "react-toastify";
import { PasswordInput } from "@/components";
import { useState } from "react";
import logo from "@/assets/logo-light-blue.png";
import { getUserData } from "@/utils";
import { useGlobalUser } from "@/context/user.context";
import "./style.scss";

export const Login = () => {
  const [form, setForm] = useState({ password: "", email: "" });
  const { loginAction } = userService();
  const [, setUser] = useGlobalUser();
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedForm = { ...form, [name]: value };
    setForm(updatedForm);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = { email: form.email, password: form.password };
      const response = await loginAction(payload);
      const token = response.headers["x-auth-token"];
      const type = response.data.permissoes[0].name;
      setUserData(token, type);
      toast.success(LOGIN_MESSAGES.SUCCESS);
    } catch (error) {
      console.error(error);
      toast.error(LOGIN_MESSAGES.ERROR);
    }
  };

  const setUserData = async (token, type) => {
    localStorage.setItem("token", token);
    localStorage.setItem("type", type);

    const user = await getUserData();

    setUser(user);

    navigate(ROUTES.feed);
  };

  return (
    <section className="login">
      <section className="login-form">
        <button className="login-logo">
          <img className="logo-img" src={logo} />
        </button>
        <div className="login-title">
          <h1>{ABREVIATED_TITLE}</h1>
          <h3>{TITLE}</h3>
        </div>

        <form onSubmit={handleSubmit} className="login-container">
          <TextField
            onChange={handleChange}
            value={form.email}
            label="Email"
            name="email"
            variant="outlined"
            required
            type={"email"}
          />
          <PasswordInput
            passwordValue={form.password}
            onChange={handleChange}
          />

          <div className="options">
            <Button type="submit" className="button" variant="contained">
              Logar
            </Button>
            <Link href={ROUTES.register}>Não tem conta? Registrar</Link>
          </div>
        </form>
      </section>
      <section className="login-wallpaper"></section>
    </section>
  );
};
