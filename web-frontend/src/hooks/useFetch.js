import { useState, useEffect } from "react";

/*This hook will be used for all get requests*/
export const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const abortController = new AbortController();

    fetch(url, { signal: abortController.signal })
      .then((res) => {
        if (!res.ok) {
          throw Error("Did not get data from server");
        }
        return res.json();
      })
      .then((data) => {
        setData(prevData => data);
        setLoading(preLoading => false);
        setError(prevError => null);
      })
      .catch((error) => {
        if (error.name === "AbortError") {
          console.log("aborted");
        } else {
          setLoading(prevLoading => false);
          setError(prevError => error.message);
        }
      });

    return () => abortController.abort();
  }, [url]);

  return { data, isLoading, error };
};
