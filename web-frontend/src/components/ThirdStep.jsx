/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useState } from "react";

export const ThirdStep = ({ dataFromForms }) => {
  const [description, setDescription] = useState(null);
  const [casualtiesDescription, setCasualtiesDescription] = useState(null);
  const [date, setDate] = useState(null);
  const [appTime, setAppTime] = useState(null);

  const { inputs, inputWrapper, formStyle, button } = thirdStepStyles();

  const time = date + "T" + appTime;

  const data = {description, casualtiesDescription, date, time};

  const handleSubmit = (e) => e.preventDefault();

  return (
    <>
      <div>
        <form onSubmit={handleSubmit} css={formStyle}>
          <div css={inputWrapper}>
            <textarea
              name="description"
              id="description"
              cols="30"
              rows="4"
              placeholder="A brief description of the accident is welcome"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              css={[
                inputs,
                css`
                  width: 96%;
                `,
              ]}
            />
          </div>
          <div css={inputWrapper}>
            <textarea
              name="casualties description"
              id="casualties description"
              cols="30"
              rows="4"
              placeholder="Provide a description of the casualties (optional)"
              value={casualtiesDescription}
              onChange={(e) => setCasualtiesDescription(e.target.value)}
              css={[
                inputs,
                css`
                  width: 96%;
                `,
              ]}
            />
          </div>
          <div css={inputWrapper}>
            <input
              css={[
                inputs,
                css`
                  width: 96%;
                `,
              ]}
              type="date"
              name="accident date"
              id="accidentDate"
              placeholder="Enter date of accident"
              value={date}
              onChange={(e) => setDate(e.target.value)}
            />
          </div>
          <div css={inputWrapper}>
            <input
              css={[
                inputs,
                css`
                  width: 96%;
                `,
              ]}
              type="time"
              name="accident time"
              id="accidentTime"
              placeholder="Enter time of accident (approximate accepted)"
              value={appTime}
              onChange={(e) => setAppTime(e.target.value)}
            />
          </div>
          <button onClick={() => dataFromForms(data, 4)} css={button}>Proceed</button>
        </form>
      </div>
    </>
  );
};

export const thirdStepStyles = () => {
  const inputs = css`
    background: transparent;
    outline: none;
    border: 1px solid;
    border-color: #08a1a1;
    border-radius: 3px;
    margin-bottom: 5px;
    padding: 5px;
  `;

  const inputWrapper = css`
    width: 300px;
  `;

  const formStyle = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  `;

  const button = css`
    text-decoration: none;
    color: white;
    background-color: #204dcc;
    padding: 5px 10px;
    border-radius: 3px;
    transition: 0.17s ease;
    border: none;
    :hover {
      background-color: #222222;
    }
  `;

  return { inputs, inputWrapper, formStyle, button };
};
