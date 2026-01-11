import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const client = new QueryClient();

<QueryClientProvider client={client}>
  <App />
</QueryClientProvider>
