import React, { useState, useEffect } from "react";

import "./style.scss";

export function Dropzone({ files, setFiles }) {
  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const newFiles = Array.from(e.dataTransfer.files);
    setFiles([...files, ...newFiles]);
  };

  const handleFileInputChange = (e) => {
    const newFiles = Array.from(e.target.files);
    setFiles([...files, ...newFiles]);
  };

  const [imagePreviews, setImagePreviews] = useState([]);

  useEffect(() => {
    const newImagePreviews = [];
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const reader = new FileReader();
      reader.onload = () => {
        newImagePreviews.push(reader.result);
        if (i === files.length - 1) {
          setImagePreviews(newImagePreviews);
        }
      };
      reader.readAsDataURL(file);
    }
  }, [files]);

  const handleDeleteClick = (index) => {
    const newFiles = [...files];
    newFiles.splice(index, 1);
    setFiles(newFiles);

    const newImagePreviews = [...imagePreviews];
    newImagePreviews.splice(index, 1);
    setImagePreviews(newImagePreviews);
  };

  return (
    <div className="dropzone" onDragOver={handleDragOver} onDrop={handleDrop}>
      <div className="image-preview-container">
        {imagePreviews.map((preview, index) => (
          <div key={index} className="image-preview-wrapper">
            <img
              className="image-preview"
              src={preview}
              alt={`Imagem ${index}`}
            />
            <button
              type="button"
              onClick={() => handleDeleteClick(index)}
              className="delete-button"
            >
              X
            </button>
          </div>
        ))}
      </div>
      <div>
        <label className="file-upload-label">
          <input
            className="file-upload-input"
            type="file"
            multiple
            onChange={handleFileInputChange}
          />
          Selecione as imagens
        </label>
      </div>
    </div>
  );
}
