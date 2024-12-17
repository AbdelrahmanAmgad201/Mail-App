import { useState, useEffect, useRef } from 'react'
import Login from './login_registration/Login.jsx'
import Registration from './login_registration/Registration.jsx'
import App from './App.jsx'

function Control() {
    const [showApp, setShowApp] = useState(false)
    const [showLogin, setShowLogin] = useState(true)
    const [showRegistration, setShowRegistration] = useState(false)
    const user = useRef({id:""})

    const closeAllPages = () => {
      setShowApp(false)
      setShowLogin(false)
      setShowRegistration(false)
    }

    const openLoginPage = () => {
      closeAllPages()
      setShowLogin(true)
    }

    const openRegistrationPage = () => {
      closeAllPages()
      setShowRegistration(true)
    }

    const openAppPage = () => {
      console.log(user.current)
      closeAllPages()
      setShowApp(true)
    }

    useEffect(() => {
        return () => {
            
        }
    }, []);

  return (
    <div className='Control-body'>
        { showApp && <App email='hello@hello.com' user={user}/>}
        { showLogin && <Login user={user} goToRegistration={openRegistrationPage} goToApp={openAppPage}/>}
        { showRegistration && <Registration user={user} goToLogin={openLoginPage} goToApp={openAppPage}/>}
    </div>
  )
}

export default Control
