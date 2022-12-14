/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useState } from "react";
import { FirstStep } from "../components/FirstStep";
import { FourthStep } from "../components/FourthStep";
import { Header } from "../components/Header";
import { SecondStep } from "../components/SecondStep";
import { ThirdStep } from "../components/ThirdStep";

export const ReportPage = (props) => {
  const [steps, setSteps] = useState(1);
  const [isLoading, setLoading] = useState(false);

  const { root, main } = reportPageStyles();

  let accidentData = {};
  let location = {};
  let finalData = {};

  const dataFromForms = (data, nextStep) => {
    if (nextStep === 2) {
      accidentData = { ...data };
      localStorage.setItem('accidentData', JSON.stringify(accidentData));
      setSteps(nextStep);
    } else if (nextStep === 3) {
      location = {...data };
      localStorage.setItem('location', JSON.stringify(location));
      setSteps(nextStep);
    } else if (nextStep === 4) {
      const localStorageBkp = localStorage.getItem('accidentData');
      const oldAccidentData = JSON.parse(localStorageBkp);
      accidentData = {oldAccidentData, ...data};
      localStorage.removeItem('accidentData')
      localStorage.setItem('accidentData', JSON.stringify(accidentData))
      setSteps(nextStep);
    } else if(nextStep === 5){
      accidentData = JSON.parse(localStorage.getItem('accidentData'));
      location = JSON.parse(localStorage.getItem('location'));
      finalData = {...data, accidentData, location};
      console.log(finalData);

      const url = "/api/accidents/"

      fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(finalData),
      })
        .then((response) => {
          if (response.ok) return;
          throw new Error("There was a problem saving the accident");
        })
        .then((res) => {
          setLoading(true);
        })
        .catch((error) => {
          setLoading(true);
          if (error.name === "AbortError") {
            console.log("aborted");
          } else {
            alert(error.message);
          }
        });
    }
  };

  return (
    <>
      <div css={root}>
        <Header pageTitle={"Report Accident"} />

        <main css={main}>
          {steps === 1 && <FirstStep dataFromForms={dataFromForms} />}
          {steps === 2 && <SecondStep dataFromForms={dataFromForms} />}
          {steps === 3 && <ThirdStep dataFromForms={dataFromForms} />}
          {steps === 4 && <FourthStep dataFromForms={dataFromForms} isLoading={isLoading} />}
        </main>
      </div>
    </>
  );
};

export const reportPageStyles = () => {
  const root = css`
    background-image: linear-gradient(to bottom right, #6060ee, skyblue);
    width: 100vw;
    height: 100vh;
    display: grid;
    grid-template-rows: min-content auto;
    grid-template-columns: 1fr;
  `;

  const main = css`
    width: 50%;
    height: 80%;
    margin: auto;
    background-color: white;

    /* make it appear card like */
    transition: 0.3s;
    box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    border-radius: 5px;

    display: flex;
    justify-content: center;
    align-items: center;
  `;

  return { root, main };
};
