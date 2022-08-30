/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { useState } from "react";
import { firstStepStyles } from "./FirstStep";

export const FourthStep = ({ dataFromForms, isLoading }) => {
  const [reporterName, setReporterName] = useState(null);
  const [reporterEmail, setReporterEmail] = useState(null);
  const [reporterContact, setReporterContact] = useState(null);

  const { inputs, inputWrapper, formStyle, button } = firstStepStyles();

  const data = { reporterName, reporterContact, reporterEmail };

  const handleSubmit = (e) => e.preventDefault();

  return (
    <>
      <div>
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
              name="name"
              id="name"
              placeholder="Full Name"
              value={reporterName}
              onChange={(e) => setReporterName(e.target.value)}
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
              type="email"
              name="email"
              id="email"
              placeholder="Email Address"
              value={reporterEmail}
              onChange={(e) => setReporterEmail(e.target.value)}
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
              type="phone"
              name="phone"
              id="phone"
              placeholder="Phone Number"
              value={reporterContact}
              onChange={(e) => setReporterContact(e.target.value)}
            />
          </div>

          {isLoading ? (
            <button
              onClick={() => dataFromForms(data, 5)}
              css={button}
              disabled
            >
              Done
            </button>
          ) : (
            <button onClick={() => dataFromForms(data, 5)} css={button}>
              Finish
            </button>
          )}
        </form>
      </div>
    </>
  );
};
