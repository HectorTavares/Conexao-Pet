import { Header, Post, SkeletonPost, List, ProfileCard } from "@/components";
import { useState } from "react";
import { useGlobalUser } from "@/context/user.context";
import { voluntaryService } from "@/services";
import { useEffect } from "react";

import "./style.scss";

export const Feed = () => {
  const [user] = useGlobalUser();
  const [isLoading, setIsLoading] = useState(true);
  const [posts, setPosts] = useState([]);
  const { getFeedPosts } = voluntaryService();
  const [ongsList, setOngsList] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await getFeedPosts();
        setPosts(response);
        setIsLoading(false);
      } catch (error) {
        console.error(error);
      } finally {
      }
    };

    fetchPosts();
  }, []);

  useEffect(() => {
    if (!(user instanceof Promise) && user.ongSeguidas?.length) {
      setOngsList(user.ongSeguidas);
    }
  }, [user]);

  const renderPosts = () => {
    return posts.map((post) => (
      <Post
        key={post.id}
        userName={post.donoCampanha.nome}
        userPhoto={post.donoCampanha.foto}
        userId={post.donoCampanha.id}
        date={post.dataCriacao}
        description={post.descricao}
        imageUrls={post.fotos}
        title={post.titulo}
        endDate={post.dataEncerramento}
        phoneNumber={post.telefone}
      />
    ));
  };
  {
    isLoading ? (
      <>
        <SkeletonPost key={1} />
        <SkeletonPost key={2} />
        <SkeletonPost key={3} />
      </>
    ) : null;
  }

  return (
    <div className="feed">
      <Header />
      <section className="feed-content">
        <div className="feed-left">
          <ProfileCard
            image={user?.foto}
            address={user?.endereco}
            name={user?.nome}
            phoneNumber={user?.telefone}
          />
        </div>
        <div className="feed-center">
          {posts.length ? (
            renderPosts()
          ) : (
            <h2>Siga Ongs ou mude os seus interesses para receber campanhas</h2>
          )}
        </div>
        <div className="feed-right">
          <List itens={ongsList} title={"Ong seguidas"} isOng={true} />
        </div>
      </section>
    </div>
  );
};
