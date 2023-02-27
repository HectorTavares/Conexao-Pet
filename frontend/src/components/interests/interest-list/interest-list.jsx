import { InterestsTag } from "../interest-tag/interest-tag";
import "./style.scss";

export const InterestList = ({
  interestsList,
  handleInterestRemove,
  handleSelectInterest,
}) => {
  return (
    <div className="interest-list">
      <h2>Lista de interesses disponiveis</h2>
      <div className="available-interests-content">
        {interestsList.map((int) => {
          return (
            <InterestsTag
              handleInterestRemove={handleInterestRemove}
              interest={int}
              selectInterest={handleSelectInterest}
            />
          );
        })}
      </div>
    </div>
  );
};
