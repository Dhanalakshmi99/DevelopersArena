import { useEffect } from "react";
import { WebSocketService } from "../services/websocket";
import { useAppDispatch } from "../app/hooks";
import { setMetric } from "../features/dashboard/dashboardSlice";

export const useWebSocket = (url: string) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    const ws = new WebSocketService();
    ws.connect(url, data => dispatch(setMetric(data)));
    return () => ws.disconnect();
  }, [url]);
};
