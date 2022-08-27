/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Header } from "../components/Header";
import backgroundImage from "./../resources/image/ewscripps.brightspotcdn.png";

export const HomePage = (props) => {
  const { background } = homePageStyles();

  return (
    <>
      <div css={background}>
        <Header pageTitle={"Home"} />
        <main></main>
      </div>
    </>
  );
};

export const homePageStyles = () => {
  const background = css`
    width: 100vw;
    height: 100vh;
    background-image: url(${backgroundImage});
    background-color: #200335;
    background-attachment: fixed;
    background-size: cover;
    background-blend-mode: multiply;
  `;

  return { background };
};
