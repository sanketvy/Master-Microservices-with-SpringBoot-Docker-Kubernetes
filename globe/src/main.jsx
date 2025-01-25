import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(

  <>
  <div style={{ position: 'relative', width: '100vw', height: '100vh' }}>
  <div style={{
    backgroundColor: 'lightblue',
    width: '100%',
    height: '100%',
    position: 'absolute',
    top: 0,
    left: 0,
  }}>
    <App />
  </div>

  <div style={{
    backgroundColor: 'rgba(255, 255, 255, 0)',
    color: 'white',
    width: '100%',
    height: '100%',
    position: 'absolute',
    top: 0,
    left: 0,
  }}>
    Overlay Div
  </div>

</div>
<h1>Hii</h1>

</>

)
