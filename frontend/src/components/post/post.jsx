import React from "react";
import { ProfileAvatar, ContactButton } from "@/components";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import "./style.scss";

export function Post({
  userPhoto,
  userName,
  date,
  description,
  imageUrls,
  title,
  endDate,
  userId,
  phoneNumber,
}) {
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

  return (
    <div className="post">
      <div className="post-header">
        <ProfileAvatar
          name={userName}
          profilePicture={userPhoto}
          userId={userId}
        />
        <div className="post-info">
          <h2>{userName}</h2>
          <p>{date}</p>
        </div>
      </div>
      <h3 className="post-title">{title}</h3>
      <p className="post-description">{description}</p>
      {imageUrls ? (
        <Carousel
          responsive={responsive}
          infinite={false}
          autoPlay={false}
          removeArrowOnDeviceType={["tablet", "mobile"]}
          keyBoardControl={true}
          customTransition="all .5"
          transitionDuration={20}
          containerClass="carousel-container carrosel-container"
          dotListClass="custom-dot-list-style"
          itemClass="carousel-item-padding-40-px"
        >
          {imageUrls.map((url) => (
            <img className="post-image" key={url} src={url} alt={url} />
          ))}
        </Carousel>
      ) : null}

      <div className="post-footer">
        <p className="post-end-date">Encerra em: {endDate}</p>

        <ContactButton campaingName={title} number={phoneNumber} />
      </div>
    </div>
  );
}
