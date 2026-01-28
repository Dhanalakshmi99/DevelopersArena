import { LineChart, Line, XAxis, YAxis } from "recharts";
import { useAppSelector } from "../../app/hooks";

export default function DashboardPage() {
  const data = useAppSelector(s => s.dashboard.metrics);

  return (
    <LineChart width={600} height={300} data={data}>
      <XAxis dataKey="id" />
      <YAxis />
      <Line type="monotone" dataKey="value" stroke="#1976d2" />
    </LineChart>
  );
}
