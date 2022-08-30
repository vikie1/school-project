/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useState } from "react";

export const FirstStep = ({ dataFromForms }) => {
  const [type, setType] = useState(null);
  const [cause, setCause] = useState(null);
  const [causalVehicleType, setCausalVehicleType] = useState('PSV');
  const [causalVehicleGroup, setCausalVehicleGroup] = useState(null);
  const [totalVehiclesInvolved, setTotalVehiclesInvolved] = useState(null);
  const [passengerCasualties, setPassengerCasualties] = useState(null);
  const [passersByCasualties, setPassersByCasualties] = useState(null);
  const [otherCasualties, setOtherCasualties] = useState(null);

  const totalCasualties =
    parseInt(passengerCasualties) +
    parseInt(passersByCasualties) +
    parseInt(otherCasualties);

  const data = {
    type,
    cause,
    causalVehicleType,
    causalVehicleGroup,
    totalVehiclesInvolved,
    passengerCasualties,
    passersByCasualties,
    otherCasualties,
    totalCasualties,
  };

  const { inputs, center, inputWrapper, formStyle, button } = firstStepStyles();

  const handleSubmit = (e) => e.preventDefault();

  return (
    <>
      <div>
        <h1 css={center}>Report Accident</h1>
        <form onSubmit={handleSubmit} css={formStyle}>
          <div css={inputWrapper}>
            <input
              css={[inputs, css`width: 96%;`]}
              type="text"
              name="accidentType"
              id="accidentType"
              placeholder="Accident Type e.g. Head On Collision"
              value={type}
              onChange={(e) => setType(e.target.value)}
            />
          </div>
          <div css={inputWrapper}>
            <input
              css={[inputs, css`width: 96%;`]}
              type="text"
              name="accidentCause"
              id="accidentCause"
              placeholder="Cause e.g. drunk driving, weather e.t.c"
              value={cause}
              onChange={(e) => setCause(e.target.value)}
            />
          </div>
          <div css={inputWrapper}>
            <select
              css={[inputs, css`width: 100%;`]}
              id="cars"
              name="cars"
              value={causalVehicleType}
              onChange={(e) => setCausalVehicleType(e.target.value)}
            >
              <option value="PSV">PSV</option>
              <option value="Personal Vehicle">Personal Vehicle</option>
              <option value="Company/Institution Vehicle">
                Company/Institution Vehicle
              </option>
              <option value="Cago Vehicles">Cago Vehicles</option>
            </select>
          </div>
          <div css={inputWrapper}>
            <input
              css={[inputs, css`width: 96%;`]}
              type="text"
              name="causalVehicleGroup"
              id="causalVehicleGroup"
              placeholder="Enter info on the car e.g number plates"
              value={causalVehicleGroup}
              onChange={(e) => setCausalVehicleGroup(e.target.value)}
            />
          </div>
          <div css={inputWrapper}>
            <input
              css={[inputs, css`width: 96%;`]}
              type="number"
              name="totalVehiclesInvolved"
              id="totalVehiclesInvolved"
              placeholder="Enter number of vehicles involved/affected"
              value={totalVehiclesInvolved}
              onChange={(e) => setTotalVehiclesInvolved(e.target.value)}
            />
          </div>
          <p css={inputWrapper}>Casualties: passenger, passersby, other</p>
          <div
            css={[
              inputWrapper,
              css`
                display: flex;
              `,
            ]}
          >
            <input
              css={[
                inputs,
                css`
                  margin-right: 5px;
                `,
              ]}
              type="number"
              name="passengerCasualties"
              id="passengerCasualties"
              placeholder="Passenger casualties"
              value={passengerCasualties}
              onChange={(e) => setPassengerCasualties(e.target.value)}
            />
            <input
              css={[
                inputs,
                css`
                  margin-right: 5px;
                `,
              ]}
              type="number"
              name="passersByCasualties"
              id="passersByCasualties"
              placeholder="Passersby casualties"
              value={passersByCasualties}
              onChange={(e) => setPassersByCasualties(e.target.value)}
            />
            <input
              css={inputs}
              type="number"
              name="otherCasualties"
              id="otherCasualties"
              placeholder="Enter info on the car e.g number plates"
              value={otherCasualties}
              onChange={(e) => setOtherCasualties(e.target.value)}
            />
          </div>
          <div>
            <button onClick={() => dataFromForms(data, 2)} css={button}>
              Proceed
            </button>
          </div>
        </form>
      </div>
    </>
  );
};

export const firstStepStyles = () => {
  const inputs = css`
    background: transparent;
    outline: none;
    border: 1px solid;
    border-color: #08a1a1;
    border-radius: 3px;
    margin-bottom: 5px;
    padding: 5px;
  `;

  const center = css`
    margin: auto;
    text-align: center;
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

  return { inputs, center, inputWrapper, formStyle, button };
};
