/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useEffect } from "react";
import { Link } from "react-router-dom";

export const Header = ({ pageTitle }) => {
  useTitle(pageTitle + " | School Project");

  // css styles
  const { listStyle, navPosition, navSizing, linkStyle, textLeft } =
    headerStyles();

  return (
    <>
      <title>{pageTitle} | School Project</title>

      <header css={navPosition}>
        <div css={textLeft}>
          Accident
          <br />
          Tracker
        </div>

        <nav css={navSizing}>
          <ul css={listStyle}>
            <li>
              <Link to={"/home"} css={linkStyle}>
                Home
              </Link>
            </li>
            <li>
              <Link to={"/data"} css={linkStyle}>
                Accident Data
              </Link>
            </li>
            <li>
              <Link to={"/analytics"} css={linkStyle}>
                Statistics
              </Link>
            </li>
            <li>
              <Link to={"/report"} css={linkStyle}>
                Report
              </Link>
            </li>
            <li>
              <Link to={"/about"} css={linkStyle}>
                About Us
              </Link>
            </li>
            <li>
              <Link to={"/contact"} css={linkStyle}>
                Contact
              </Link>
            </li>
          </ul>
        </nav>
      </header>
    </>
  );
};

// the title tag wasn't working for some reason and using react helmet for this project is an overkill
export function useTitle(title) {
  useEffect(() => {
    const prevTitle = document.title;
    document.title = title;
    return () => {
      document.title = prevTitle;
    };
  });
}

export const headerStyles = () => {
  const listStyle = css`
    list-style: none;
    display: flex;
    justify-content: space-around;
    background-color: transparent;
  `;
  const navSizing = css`
    padding: 2rem 0px;
    grid-column-start: 2;
    grid-column-end: 4;
  `;
  const navPosition = css`
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    width: 100%;
    height: fit-content;
  `;
  const linkStyle = css`
    color: white;
    text-decoration: none;
    :hover {
      text-decoration: underline;
    }
  `;
  const textLeft = css`
    color: white;
    grid-column-start: 1;
    grid-column-end: 2;
    place-self: center;
    font-size: x-large;
    font-weight: bolder;
  `;

  return { listStyle, navPosition, navSizing, linkStyle, textLeft };
};
