import "./style.scss";

export const InterestsTag = ({
  interest,
  handleInterestRemove,
  selectInterest,
}) => {
  return (
    <div
      key={interest.nome}
      className={`new-interest ${selectInterest ? "available" : ""}`}
      onClick={() => selectInterest(interest)}
    >
      <span className="campaing-form-interest-text">{interest.nome}</span>
      {!selectInterest ? (
        <button
          type="button"
          onClick={() => handleInterestRemove(interest)}
          className="new-interest-button"
        >
          X
        </button>
      ) : null}
    </div>
  );
};
