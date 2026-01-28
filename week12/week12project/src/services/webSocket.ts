export class WebSocketService {
  private ws?: WebSocket;

  connect(url: string, onMessage: (data: any) => void) {
    this.ws = new WebSocket(url);
    this.ws.onmessage = e => onMessage(JSON.parse(e.data));
  }

  disconnect() {
    this.ws?.close();
  }
}
