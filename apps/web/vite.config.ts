import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api": {
        // /api로 요청하면 target으로 대신 전달
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});
