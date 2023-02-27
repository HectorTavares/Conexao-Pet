import { useState } from "react";
import {
  FormControl,
  IconButton,
  OutlinedInput,
  InputLabel,
  InputAdornment,
} from "@mui/material/";
import { Visibility, VisibilityOff } from "@mui/icons-material/";

export const PasswordInput = ({ passwordValue, onChange }) => {
  const [showPassword, setShowPassword] = useState(false);

  const handleClickShowPassword = () => setShowPassword((show) => !show);

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
    <FormControl variant="outlined">
      <InputLabel htmlFor="outlined-adornment-password">Senha *</InputLabel>
      <OutlinedInput
        value={passwordValue}
        id="outlined-adornment-password"
        autoComplete="current-password"
        required
        type={showPassword ? "text" : "password"}
        label="Senha"
        name="password"
        onChange={onChange}
        endAdornment={
          <InputAdornment position="end">
            <IconButton
              aria-label="toggle password visibility"
              onClick={handleClickShowPassword}
              onMouseDown={handleMouseDownPassword}
              edge="end"
            >
              {showPassword ? <VisibilityOff /> : <Visibility />}
            </IconButton>
          </InputAdornment>
        }
      />
    </FormControl>
  );
};
