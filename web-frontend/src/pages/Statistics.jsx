/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { BarChart, Bar, XAxis, YAxis, Legend } from "recharts";
import { Header } from "../components/Header";
import { useFetch } from "../hooks/useFetch";

export const Statistics = (props) => {
  const byTime = useFetch("/api/analytics/time");
  let timeBarData = {};
  if (!byTime.data && byTime.isLoading) {
    timeBarData = [{ timeSlot: "loading", totalAccidents: 0 }];
  }
  if (byTime.data) {
    timeBarData = byTime.data.analytics;
  }
  if (!byTime.data && byTime.error) {
    console.error(byTime.error);
    timeBarData = [{ timeSlot: "error", totalAccidents: 0 }];
  }

  return (
    <>
      <div
        css={css`
          background-color: #070049;
          height: 100vh;
        `}
      >
        <Header pageTitle={"Analytics - Time"} />
        <div css={css`display: flex;`}>
          <div>
            <h1
              css={css`
                color: white;
                text-align: center;
              `}
            >
              BAR CHART OF ACCIDENT OCCURENCE AGAINST TIME
            </h1>
            <BarChart
              title="Accidents occurrence by time"
              width={700}
              height={500}
              data={timeBarData}
            >
              <Bar dataKey="totalAccidents" fill="#a328f5" />
              {/* <CartesianGrid stroke="#ccc" /> */}
              <XAxis dataKey="timeSlot" />
              <YAxis />
              <Legend />
            </BarChart>
          </div>
          
        </div>
      </div>
    </>
  );
};
