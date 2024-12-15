import { useState, useEffect, useRef } from 'react'
import './style.css'
import danger_img from './assets/danger_negative.png'

function Registration(props) {
    const [errorMsg, setErrorMsg] = useState(false);
    const [errorMsgContent, setErrorMsgContect] = useState('');

    const emailInput = useRef(null)
    const passwordInput = useRef(null)
    const confirmPasswordInput = useRef(null)

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

    const validateUserInput = () => {
        if (emailInput.current.value == "")
            return false
        if (passwordInput.current.value != confirmPasswordInput.current.value)
            return false
        return true
    }

    const submitUserInput = () => {
        if (!validateUserInput()){
            setErrorMsg(true)
            wrongEmailFormatMsg()
            emailInput.current.value = ""
            passwordInput.current.value = ""
            confirmPasswordInput.current.value = ""
            return
        }

        props.user.current.email = emailInput.current.value
        props.user.current.password = passwordInput.current.value
        if (true){
            props.goToApp()
        }
    }

    useEffect(() => {
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
                <input ref={emailInput} type='text' placeholder='email address'/>
            </div>
            <div className='field'>
                <p>Password</p>
                <input ref={passwordInput} type='password' placeholder='password'/>
            </div>
            <div className='field'>
                <p>Confirm Password</p>
                <input ref={confirmPasswordInput} type='password' placeholder='password'/>
            </div>
            <button className='action-button' onClick={()=>{
                submitUserInput()
            }}>Create Account</button>
            <div className='other-page'>
                <div>Already have an account?</div> 
                <button onClick={props.goToLogin}>Log in</button>
            </div>
        </div>
    </div>
  )
}

export default Registration
