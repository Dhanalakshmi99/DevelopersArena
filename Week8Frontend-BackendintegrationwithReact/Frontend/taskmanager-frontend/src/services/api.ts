const api = {
  post: async (url: string, data: any, config?: any) => {
    console.log("Mock POST:", url, data, config);
    return { data: { id: Math.random(), title: data.title, status: "todo" as const } };
  },
  put: async (url: string, data: any, config?: any) => {
    console.log("Mock PUT:", url, data, config);
    const id = Number(url.split("/")[2]);
    return { data: { id, title: `Task ${id}`, status: data.status as "todo" | "done" } };
  },
};

export default api;
