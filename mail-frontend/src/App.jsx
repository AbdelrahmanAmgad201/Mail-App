import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='main'>
      <div className='side-bar'>

      </div>
      <div className='main-body'>
        <div className='top-bar'></div>
        <div className='main-app'>

        </div>
      </div>
    </div>
  )
}

export default App
