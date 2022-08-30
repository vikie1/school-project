/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useState } from "react";

export const SecondStep = ({ dataFromForms }) => {
  const [streetAddress, setStreetAddress] = useState(null);
  const [locality, setLocality] = useState(null);
  const [city, setCity] = useState(null);
  const [pinCode, setPinCode] = useState(null);
  
  const address = streetAddress + " " + city + " " + locality + " " + pinCode;

  const data = {address, streetAddress, locality, city, pinCode};

  const handleSubmit = (e) => e.preventDefault();

  const { inputs, inputWrapper, formStyle, button } = secondStepStyles();

  return (
    <>
      <div>
        <h2>Accident location</h2>
        <form onSubmit={handleSubmit} css={formStyle}>
          <div css={inputWrapper}>
            <input
              css={[
                inputs,
                css`
                  width: 96%;
                `,
              ]}
              type="text"
              name="accidentType"
              id="accidentType"
              placeholder="Street name or nearest feature"
              value={streetAddress}
              onChange={(e) => setStreetAddress(e.target.value)}
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
              type="text"
              name="city"
              id="city"
              placeholder="town, ward or location"
              value={city}
              onChange={(e) => setCity(e.target.value)}
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
              type="text"
              name="locality"
              id="locality"
              placeholder="locality or county"
              value={locality}
              onChange={(e) => setLocality(e.target.value)}
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
              type="number"
              name="area code"
              id="area_code"
              placeholder="area code/zip code(optional)"
              value={pinCode}
              onChange={(e) => setPinCode(e.target.value)}
            />
          </div>
          <button onClick={() => dataFromForms(data, 3)} css={button}>Proceed</button>
        </form>
      </div>
    </>
  );
};

export const secondStepStyles = () => {
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
