import { Client } from "@stomp/stompjs";

export const createWebSocketClient = (onMessage: (msg: any) => void) => {
  const client = new Client({
    brokerURL: "ws://localhost:8080/ws",
    reconnectDelay: 5000,
  });

  client.onConnect = () => {
    client.subscribe("/topic/tasks", (message) => {
      const body = JSON.parse(message.body);
      onMessage(body);
    });
  };

  client.activate();
  return client;
};
