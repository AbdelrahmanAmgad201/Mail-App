import { useState, useEffect, useRef } from 'react'
import Login from './login_registration/Login.jsx'
import Registration from './login_registration/Registration.jsx'
import App from './App.jsx'

function Control() {
    const [showApp, setShowApp] = useState(false)
    const [showLogin, setShowLogin] = useState(false)
    const [showRegistration, setShowRegistration] = useState(false)

    useEffect(() => {
        setShowApp(true)
        setShowLogin(true)
        setShowRegistration(true)
        return () => {
            
        }
    }, []);

  return (
    <div className='Control-body'>
        { showApp && <App email='hello@hello.com' />}
        { showLogin && <Login />}
        { showRegistration && <Registration />}
    </div>
  )
}

export default Control
