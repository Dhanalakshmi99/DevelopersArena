import { useParams } from "react-router-dom";

export const TaskDetail = () => {
  const { id } = useParams();
  return <div>Task Detail Page - ID: {id}</div>;
};
