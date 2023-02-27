import React from "react";
import { ContactButton } from "@/components";
import Carousel from "react-multi-carousel";
import MaleIcon from "@mui/icons-material/Male";
import FemaleIcon from "@mui/icons-material/Female";
import "react-multi-carousel/lib/styles.css";

import "./style.scss";

export const AnimalCard = ({ animal, ongNumber }) => {
  const responsive = {
    desktop: {
      breakpoint: { max: 3000, min: 1024 },
      items: 1,
      slidesToSlide: 1,
    },
    tablet: {
      breakpoint: { max: 1024, min: 464 },
      items: 1,
      slidesToSlide: 1,
    },
    mobile: {
      breakpoint: { max: 464, min: 0 },
      items: 1,
      slidesToSlide: 1,
    },
  };

  const {
    especie,
    genero,
    porte,
    dataDeNascimento,
    nome,
    raca,
    situacaoVacinal,
    caracteristicas,
    fotos,
  } = animal;

  const renderGender = () => {
    return genero === "Macho" ? (
      <MaleIcon style={{ width: 50, height: 50 }} className="gender-icon" />
    ) : (
      <FemaleIcon style={{ width: 50, height: 50 }} className="gender-icon" />
    );
  };

  return (
    <div className="post animal-card">
      <h2 className="animal-title">
        {nome} {renderGender()}
      </h2>
      <div className="animal-details">
        <p className="animal-description">
          <strong>Raça:</strong> {raca}
        </p>
        <p className="animal-description">
          <strong>Espécie:</strong> {especie}
        </p>
        <p className="animal-description">
          <strong>Porte:</strong> {porte}
        </p>
        <p className="animal-description">
          <strong>Características: </strong> {caracteristicas}
        </p>
        <p className="animal-description">
          <strong>Situação Vacinal: </strong>
          {situacaoVacinal.length ? situacaoVacinal : "Não vacinado"}
        </p>
      </div>
      {fotos ? (
        <Carousel
          responsive={responsive}
          infinite={false}
          autoPlay={false}
          removeArrowOnDeviceType={["tablet", "mobile"]}
          keyBoardControl={true}
          customTransition="all .5"
          transitionDuration={20}
          containerClass="carousel-container"
          dotListClass="custom-dot-list-style"
          itemClass="carousel-item-padding-40-px"
        >
          {fotos.map((url) => (
            <img className="post-image" key={url} src={url} alt={url} />
          ))}
        </Carousel>
      ) : null}

      <div className="post-footer">
        <p className="post-end-date">Data de nascimento: {dataDeNascimento}</p>

        <ContactButton
          number={ongNumber}
          isToAdopt={true}
          especie={especie}
          animalName={nome}
        />
      </div>
    </div>
  );
};
