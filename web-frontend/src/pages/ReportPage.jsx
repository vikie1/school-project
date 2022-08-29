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

  const { root, main } = reportPageStyles();

  const dataFromForms = (data, nextStep) => {
    setSteps(nextStep);
    console.log(data, nextStep);
  };

  return (
    <>
      <div css={root}>
        <Header pageTitle={"Report Accident"} />

        <main css={main}>
          {steps === 1 && <FirstStep dataFromForms={dataFromForms} />}
          {steps === 2 && <SecondStep dataFromForms={dataFromForms} />}
          {steps === 3 && <ThirdStep dataFromForms={dataFromForms} />}
          {steps === 4 && <FourthStep dataFromForms={dataFromForms} />}
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
