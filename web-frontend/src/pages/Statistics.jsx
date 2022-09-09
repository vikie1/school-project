import { BarChart, Bar, CartesianGrid, XAxis, YAxis } from "recharts";

export const Statistics = (props) => {
  // Sample data
  const data = [
    { name: "Technical scripter", students: 700 },
    { name: "Geek-i-knack", students: 200 },
    { name: "Geeksforgeeks", students: 400 },
    { name: "Geek-o-mania", students: 1000 },
  ];
  return (
    <>
      <h1>TEST BAR CHART LIBRARY FOR REACTJS</h1>
      <div>
        <BarChart width={600} height={600} data={data}>
          <Bar dataKey="students" fill="green" />
          {/* <CartesianGrid stroke="#ccc" /> */}
          <XAxis dataKey="name" />
          <YAxis />
        </BarChart>
      </div>
    </>
  );
};
