import React, { useEffect, useState, useRef } from "react";
import {
  Header,
  ProfileCard,
  List,
  Post,
  SkeletonPost,
  UpdateInfoForm,
  AnimalCard,
} from "@/components";
import { useGlobalUser } from "@/context/user.context";
import { toast } from "react-toastify";
import Modal from "@mui/material/Modal";
import { ROUTES } from "@/constants";
import { useLocation, useNavigate } from "react-router-dom";
import { userService, voluntaryService } from "@/services/";
import { Button } from "@mui/material";
import { getUserIsVoluntary } from "@/utils";

import "./style.scss";

export const Profile = ({}) => {
  const [loggedUser] = useGlobalUser();
  const location = useLocation();
  const [isLoading, setIsLoading] = useState(true);
  const [isModalEditProfileOpen, setIsModalEditProfileOpen] = useState(false);
  const [isModalAnimalOpen, setIsModalAnimalOpen] = useState(false);
  const userId = location?.state?.userId;
  const isMyProfile = !userId;

  const [profileUser, setProfileUser] = useState(null);

  const [isProfileOng, setIsOng] = useState(null);
  const [isOngFollowed, setIsOngFollowed] = useState(null);
  const [userIsVoluntary, setUserIsVoluntary] = useState(null);
  const modalRef = useRef(null);

  const { getProfileInfo } = userService();
  const { followOng } = voluntaryService();

  const navigate = useNavigate();

  const goToInterestsEdit = () => {
    navigate(ROUTES.interest, { state: { interests: profileUser.interesses } });
  };

  useEffect(() => {
    if (isProfileOng) {
      if (loggedUser instanceof Promise) {
        loggedUser.then((value) => {
          const isOngFolowedValue = !!value?.ongSeguidas?.find(
            (ong) => ong.idUsuario === profileUser.idUsuario
          );
          setIsOngFollowed(isOngFolowedValue);
          setUserIsVoluntary(value?.permissions[0] === "Voluntário");
        });
      } else {
        const isOngFolowedValue = !!loggedUser?.ongSeguidas?.find(
          (ong) => ong.idUsuario === profileUser.idUsuario
        );
        setIsOngFollowed(isOngFolowedValue);
        setUserIsVoluntary(loggedUser?.permissions[0] === "Voluntário");
      }
    }
  }, [isProfileOng]);

  useEffect(() => {
    const fetchProfileInfo = async () => {
      if (userId) {
        try {
          const userInfo = await getProfileInfo(userId);
          const profileValue = userInfo.voluntarioModelReponse
            ? userInfo.voluntarioModelReponse
            : userInfo.ongModelResponse;

          setProfileUser(profileValue);
          setIsOng(profileValue?.permissions[0] === "Ong");
          setIsLoading(false);
        } catch (error) {
          console.error(error);
        }
      } else {
        if (loggedUser instanceof Promise) {
          loggedUser.then((value) => {
            setProfileUser(value);
            setIsOng(value?.permissions[0] === "Ong");
          });
        } else {
          setProfileUser(loggedUser);
          setIsOng(loggedUser?.permissions[0] === "Ong");
        }

        setIsLoading(false);
      }
    };

    fetchProfileInfo();
  }, [userId]);

  const renderPosts = () => {
    return profileUser.campanhas.map((post) => (
      <Post
        key={post.id}
        userName={post.donoCampanha.nome}
        userPhoto={post.donoCampanha.foto}
        date={post.dataCriacao}
        description={post.descricao}
        imageUrls={post.fotos}
        title={post.titulo}
        phoneNumber={post.telefone}
        endDate={post.dataEncerramento}
        userId={post.donoCampanha.id}
      />
    ));
  };

  const [animalSelected, setAnimalSelected] = useState();
  const handleSelectAnimal = (animal) => {
    setAnimalSelected(animal);
    setIsModalAnimalOpen(true);
  };

  const handleClickFollow = async () => {
    try {
      await followOng(profileUser.id);
      toast.success(!isOngFollowed ? "Ong seguida" : "Seguir cancelado");
      setIsOngFollowed((state) => !state);
    } catch (error) {
      toast.error("Erro ao seguir Ong");
    }
  };

  return (
    <div className="feed">
      <Header />
      <section className="feed-content">
        <Modal
          open={isModalAnimalOpen}
          onClose={() => setIsModalAnimalOpen(false)}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <AnimalCard
            animal={animalSelected}
            ongNumber={profileUser?.telefone}
          />
        </Modal>
        <div className="feed-left">
          <ProfileCard
            phoneNumber={profileUser?.telefone}
            image={profileUser?.foto}
            address={profileUser?.endereco}
            name={profileUser?.nome}
            isMyProfile={isMyProfile}
          />
        </div>
        <div className="feed-center">
          <div className="center-profile-content">
            {/* <h2>Nota média</h2>
            <Rating name="read-only" value={profileUser?.nota || 0} readOnly /> */}
            {isMyProfile ? (
              <>
                <Modal
                  open={isModalEditProfileOpen}
                  onClose={() => setIsModalEditProfileOpen(false)}
                  aria-labelledby="modal-modal-title"
                  aria-describedby="modal-modal-description"
                >
                  <UpdateInfoForm
                    phone={profileUser?.telefone}
                    name={profileUser?.nome}
                    adress={profileUser?.endereco}
                    profilePicture={profileUser?.foto}
                    ref={modalRef}
                  />
                </Modal>
                <Button
                  onClick={() => setIsModalEditProfileOpen(true)}
                  className="profile-button"
                >
                  Mudar informações
                </Button>
              </>
            ) : null}
            {!isProfileOng && isMyProfile ? (
              <Button className="profile-button" onClick={goToInterestsEdit}>
                Mudar Interesses
              </Button>
            ) : null}
            {isProfileOng && !isMyProfile && userIsVoluntary ? (
              <Button className="profile-button" onClick={handleClickFollow}>
                {isOngFollowed ? "Parar de seguir" : "Seguir"}
              </Button>
            ) : null}
            {isProfileOng && isMyProfile ? (
              <>
                <Button
                  className="profile-button"
                  onClick={() => navigate(ROUTES.campaing)}
                >
                  Criar Campanha
                </Button>
                <Button
                  className="profile-button"
                  onClick={() => navigate(ROUTES.animal)}
                >
                  Adicionar Animal
                </Button>
              </>
            ) : null}
          </div>
          {isProfileOng ? renderPosts() : null}
        </div>
        <div className="feed-right">
          <List
            itens={
              isProfileOng ? profileUser?.animais : profileUser?.ongSeguidas
            }
            title={isProfileOng ? "Animais" : "Ongs Seguidas"}
            isOng={!isProfileOng}
            handleSelectAnimal={handleSelectAnimal}
          />
        </div>
      </section>
    </div>
  );
};
