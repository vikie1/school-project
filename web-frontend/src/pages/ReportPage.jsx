import { useState } from "react";
import { FirstStep } from "../components/FirstStep";
import { FourthStep } from "../components/FourthStep";
import { Header } from "../components/Header";
import { SecondStep } from "../components/SecondStep";
import { ThirdStep } from "../components/ThirdStep";


export const ReportPage = (props) => {
    const [steps, setSteps] = useState(1);

    const dataFromForms = (data, nextStep) => {
        setSteps(nextStep);
        console.log(data, nextStep);
    };

  return (
    <>
      <div>
        <Header pageTitle={"Report Accident"} />

        <main>
            {steps === 1 && <FirstStep dataFromForms={dataFromForms}/>}
            {steps === 2 && <SecondStep dataFromForms={dataFromForms}/>}
            {steps === 3 && <ThirdStep dataFromForms={dataFromForms}/>}
            {steps === 4 && <FourthStep dataFromForms={dataFromForms}/>}
        </main>
      </div>
    </>
  );
};
