import { InterestsTag } from "../interest-tag/interest-tag";
import { TextField } from "@mui/material";

import "./style.scss";

export const InterestInput = ({
  currentInterest,
  setCurrentInterest,
  interests,
  handleInterestRemove,
  handleInterestAdd,
}) => {
  return (
    <div className="interests">
      <form className="interests">
        <TextField
          label="Novo interesse"
          value={currentInterest}
          onChange={(e) => setCurrentInterest(e.target.value)}
          variant="outlined"
          margin="normal"
          fullWidth
        />
        <button
          type="submit"
          onClick={handleInterestAdd}
          className="campaing-form-button"
        >
          Adicionar
        </button>
      </form>
      <div className="new-interests-list">
        {interests.map((interest) => (
          <InterestsTag
            key={interest.nome}
            interest={interest}
            handleInterestRemove={handleInterestRemove}
          />
        ))}
      </div>
    </div>
  );
};
