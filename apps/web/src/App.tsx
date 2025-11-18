import { useState, useEffect } from "react";

function App() {
  const [status, setStatus] = useState("");

  useEffect(() => {
    fetch("/api/v1/health2")
      .then((res) => {
        if (!res.ok) {
          throw new Error(`HTTP error status: ${res.status}`);
        }

        return res.json();
      })
      .then((data) => {
        setStatus(data.application);
      })
      .catch((err) => {
        console.log(err);
      });
  });

  return (
    <>
      <p>/api/v1/health 응답: {status}</p>
    </>
  );
}

export default App;
