import React, { useState, useEffect } from "react";
import "./style.scss";

const BASE_FILE_NAME = "BASE_FILE_NAME";

export const SingleDropZone = ({ image, setImage }) => {
  const [dragging, setDragging] = useState(false);
  const [imagePreview, setImagePreview] = useState(null);

  const handleDragEnter = (e) => {
    e.preventDefault();
    e.stopPropagation();
    setDragging(true);
  };

  const handleDragLeave = (e) => {
    e.preventDefault();
    e.stopPropagation();
    setDragging(false);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    e.stopPropagation();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    e.stopPropagation();
    setDragging(false);
    const file = e.dataTransfer.files[0];

    const lastFileExtension = file.name.lastIndexOf(".");
    const extesionName = file.name
      .substring(lastFileExtension + 1)
      .toLowerCase();

    const newFile = new File([file], `${BASE_FILE_NAME}.${extesionName}`, {
      type: file.type,
    });

    if (file.type.startsWith("image/")) {
      setImage(newFile);
    }
  };

  useEffect(() => {
    if (image instanceof Blob) {
      const file = image;
      const reader = new FileReader();
      reader.onload = () => {
        setImagePreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  }, [image]);

  return (
    <div
      className={`drag-and-drop ${dragging ? "dragging" : ""}`}
      onDragEnter={handleDragEnter}
      onDragLeave={handleDragLeave}
      onDragOver={handleDragOver}
      onDrop={handleDrop}
    >
      {image ? (
        <>
          <img
            src={imagePreview ? imagePreview : image}
            className="uploaded"
            alt="uploaded"
          />
          <button type="button" onClick={() => setImage(null)}>
            X
          </button>
        </>
      ) : (
        <div className="placeholder">Arraste uma imagem aqui</div>
      )}
    </div>
  );
};
