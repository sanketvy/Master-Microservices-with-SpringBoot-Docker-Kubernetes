import logo from './logo.svg';
import { useState } from 'react';
import './App.css';
import axios from  'axios';

function App() {

  const [name, setName] = useState("");

  axios.get('http://localhost:9000/api/name').then((res)=>{
    setName(res.data);
  }).catch((err)=>{
    console.log(err);
  })

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>{name.name}</p>
        <p>Above data is coming from API</p>
      </header>
    </div>
  );
}

export default App;
