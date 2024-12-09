import { useState, useEffect } from 'react'
import './style.css'
import danger_img from './assets/danger_negative.png'

function Registration() {
    const [errorMsg, setErrorMsg] = useState(false);
    const [errorMsgContent, setErrorMsgContect] = useState('');

    const usedEmailMsg = () => {
        setErrorMsgContect("Already used email");
    }

    const wrongEmailFormatMsg = () => {
        setErrorMsgContect("Wrong Email Format");
    }

    const shortPasswordMsg = () => {
        setErrorMsgContect("Short password");
    }

    const passwordsDoesntMatchMsg = () => {
        setErrorMsgContect("Passwords doesn't match");
    }

    useEffect(() => {
        setErrorMsg(true)
        wrongEmailFormatMsg()
        return () => {
            
        }
    }, []);

  return (
    <div className='login-body'>
        <div className='registration-form'>
            {errorMsg && <div className='error-msg'>
                <img src={danger_img} />
                <div>{errorMsgContent}</div>
            </div>}
            <div className='field'>
                <p>Email</p>
                <input type='text' placeholder='email address'/>
            </div>
            <div className='field'>
                <p>Password</p>
                <input type='password' placeholder='password'/>
            </div>
            <div className='field'>
                <p>Confirm Password</p>
                <input type='password' placeholder='password'/>
            </div>
            <button className='action-button'>Log In</button>
            <div className='other-page'>
                <div>Already have an account?</div> 
                <button>Log in</button>
            </div>
        </div>
    </div>
  )
}

export default Registration
