import logo from './logo.svg';
import './App.css';

import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';

var stompClient = null;

function App() {

  const [socketStatus, setSocketStatus] = useState("");
  const [payload, setPayload] = useState("");

  useEffect(() => {
    let sockjs = new SockJS("http://localhost:8080/websocket");
    stompClient = over(sockjs);
    stompClient.connect({}, onConnected, onError);
  }, []);

  const onConnected = () => {
    setSocketStatus("onConnected")
    stompClient.subscribe('/topic/greetings', onScribeGreetings)
  }

  const onError = () => {
    setSocketStatus("onError")
  }

  const onScribeGreetings = (payload) => {
    let payloadJson = JSON.parse(payload.body);
    setPayload(payloadJson.content);
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
         {payload}
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          {socketStatus}
        </a>
      </header>
    </div>
  );
}

export default App;
