import { useGlobalUser } from "@/context/user.context";
import "./style.scss";

export const ContactButton = ({
  number,
  isToAdopt,
  animalName,
  especie,
  campaingName,
}) => {
  const [user] = useGlobalUser();

  const getMessagetext = () => {
    if (isToAdopt) {
      return `Olá, eu gostaria de Adotar o ${especie} chamado ${animalName}`;
    }
    if (campaingName) {
      return `Olá! Sou ${user.nome} e gostaria de oferecer meu tempo e habilidades para ajudar com a campanha "${campaingName}". `;
    }

    return `Olá! Sou ${user.nome} encontrei a sua ong Pelo Conexão pet e gostaria de oferecer meu tempo e habilidades para lhe ajudar`;
  };

  return (
    <a
      target="_blank"
      href={`https://wa.me/${number}/?text=${getMessagetext()}`}
      className="contact-button"
    >
      {isToAdopt ? "Quero adotar " : "Entrar em contato"}
    </a>
  );
};
