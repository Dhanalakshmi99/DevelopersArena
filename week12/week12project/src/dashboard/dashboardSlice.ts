import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface Metric {
  id: string;
  value: number;
}

interface DashboardState {
  metrics: Metric[];
}

const initialState: DashboardState = {
  metrics: []
};

const dashboardSlice = createSlice({
  name: "dashboard",
  initialState,
  reducers: {
    setMetric: (state, action: PayloadAction<Metric>) => {
      state.metrics.push(action.payload);
    }
  }
});

export const { setMetric } = dashboardSlice.actions;
export default dashboardSlice.reducer;
