import type { ReactNode } from "react";

export const Layout = ({ children }: { children: ReactNode }) => (
  <div style={{ margin: "20px", fontFamily: "Arial" }}>
    <header>
      <h1>Task Manager</h1>
    </header>
    <main>{children}</main>
  </div>
);
