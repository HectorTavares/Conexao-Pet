import React, { useState } from "react";
import "./style.scss";

export const Tooltip = ({ text, children }) => {
  const [show, setShow] = useState(false);

  const onMouseEnter = () => setShow(true);
  const onMouseLeave = () => setShow(false);

  return (
    <div className="tooltip-container">
      <div
        className="tooltip-trigger"
        onMouseEnter={onMouseEnter}
        onMouseLeave={onMouseLeave}
      >
        {children}
      </div>
      {show && <div className="tooltip">{text}</div>}
    </div>
  );
};
