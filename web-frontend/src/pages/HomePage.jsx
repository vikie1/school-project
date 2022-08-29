/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Link } from "react-router-dom";
import { Header } from "../components/Header";
import backgroundImage from "./../resources/image/ewscripps.brightspotcdn.png";
import analyticsImage from "./../resources/image/home-font.png";

export const HomePage = (props) => {
  const {
    background,
    pageStyle,
    mainStyleLanding,
    landingTextStyle,
    linkButtonStyle,
    buttonWrapper
  } = homePageStyles();

  return (
    <>
      <div css={[background, pageStyle]}>
        <Header pageTitle={"Home"} />

        <main css={mainStyleLanding}>
          <div css={landingTextStyle}>
            <h1>
              Kenya's Leading Accident
              <br />
              Awareness Website
            </h1>

            <p css={css`padding-top: 1rem;`}>
              This website collects accidents occurence in real time and and
              stores the data in a central database. The data is then clustered
              according to location and time of occurence. We then analyse
              this data and visualise it for consumption by the web users.
            </p>
            <div css={buttonWrapper}>
              <Link
                to={"/report"}
                css={[
                  linkButtonStyle,
                  css`
                    background-color: white;
                    color: #204dcc;
                    :hover {
                      background-color: #204dcc;
                      color: white;
                    }
                  `,
                ]}
              >
                Report
              </Link>
              <Link
                to={"/report"}
                css={[
                  linkButtonStyle,
                  css`
                    background-color: #204dcc;
                    color: white;
                    :hover {
                      background-color: #222222;
                    }
                  `,
                ]}
              >
                Contact
              </Link>
            </div>
          </div>

          <img src={analyticsImage} alt="Analytics info" />
        </main>
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
  const pageStyle = css`
    display: grid;
    grid-template-rows: min-content auto;
    grid-template-columns: 1fr;
  `;
  const mainStyleLanding = css`
    color: white;
    place-self: center;
    margin: 0px 7rem;
    display: grid;
    grid-template-columns: 1fr 1fr;
  `;
  const landingTextStyle = css`
    place-self: center;
  `;
  const linkButtonStyle = css`
    text-decoration: none;
    padding: 10px 25px;
    margin: 0px 10px;
    transition: 0.17s ease;
    border-radius: 25px;
  `;
  const buttonWrapper = css`
    padding: 2rem 0px;
    min-height: max-content;
    min-width: max-content;
  `;

  return {
    background,
    pageStyle,
    mainStyleLanding,
    landingTextStyle,
    linkButtonStyle,
    buttonWrapper
  };
};
