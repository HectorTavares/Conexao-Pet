import { useState } from "react";
import { styled, alpha } from "@mui/material/styles";
import {
  AppBar,
  Box,
  Toolbar,
  InputBase,
  Avatar,
  Button,
} from "@mui/material/";
import LogoutIcon from "@mui/icons-material/Logout";
import SearchIcon from "@mui/icons-material/Search";
import logo from "../../assets/logo-dark-blue.png";
import { ProfileAvatar } from "../index";
import { useGlobalUser } from "@/context/user.context";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { getUserData } from "@/utils";
import { ROUTES } from "@/constants/routes";
import { toast } from "react-toastify";

import "./style.scss";
import { userService } from "../../services/user";

const Search = styled("div")(({ theme }) => ({
  position: "relative",
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  "&:hover": {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginLeft: 0,
  width: "100%",
  [theme.breakpoints.up("sm")]: {
    marginLeft: theme.spacing(1),
    width: "auto",
  },
}));

const SearchIconWrapper = styled("div")(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: "100%",
  position: "absolute",
  pointerEvents: "none",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}));

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: "inherit",
  "& .MuiInputBase-input": {
    padding: theme.spacing(1, 1, 1, 0),

    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create("width"),
    width: "100%",
    [theme.breakpoints.up("sm")]: {
      width: "12ch",
      "&:focus": {
        width: "20ch",
      },
    },
  },
}));

export const Header = () => {
  const [search, setSearch] = useState("");
  const [user, setUser] = useGlobalUser();

  const { getUserByName } = userService();

  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserData = async () => {
      if (user !== null) {
        const userData = await getUserData();
        setUser(userData);
      }
    };

    fetchUserData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await getUserByName(search);

      navigate(ROUTES.search, { state: { searchResult: response } });
    } catch (e) {
      toast.error("Erro inesperado ao pesquisar por usuarios!");
    }
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate(ROUTES.login);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar className="header">
          <button className="header-logo">
            <img
              onClick={() => navigate(ROUTES.feed)}
              className="logo-img"
              src={logo}
            />
          </button>
          <Search>
            <form onSubmit={handleSubmit}>
              <SearchIconWrapper>
                <SearchIcon />
              </SearchIconWrapper>
              <StyledInputBase
                placeholder="Pesquisar…"
                inputProps={{ "aria-label": "search" }}
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
            </form>
          </Search>
          <div className="header-options">
            <ProfileAvatar profilePicture={user?.foto} name={user?.nome} />
            <Button onClick={handleLogout}>
              <LogoutIcon style={{ color: "white" }} />
            </Button>
          </div>
        </Toolbar>
      </AppBar>
    </Box>
  );
};
