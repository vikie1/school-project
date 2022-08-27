import { useEffect } from "react";
import { Link } from "react-router-dom";

export const Header = ({ pageTitle }) => {
    useTitle(pageTitle + ' | School Project');
  return (
    <>
      <title>{pageTitle} | School Project</title>

      <header>
        <ul>
          <li>
            <Link to={"/home"}>Home</Link>
          </li>
          <li>
            <Link to={"/data"}>Accident Data</Link>
          </li>
          <li>
            <Link to={"/analytics"}>Statistics</Link>
          </li>
          <li>
            <Link to={"/about"}>About Us</Link>
          </li>
          <li>
            <Link to={"contact"}>Contact</Link>
          </li>
        </ul>
      </header>
    </>
  );
};

export function useTitle(title) {
  useEffect(() => {
    const prevTitle = document.title;
    document.title = title;
    return () => {
      document.title = prevTitle;
    };
  });
}
