import { useState, useEffect, useRef } from 'react'
import './style.css'
import danger_img from './assets/danger_negative.png'

function Login(props) {
    const [errorMsg, setErrorMsg] = useState(false);

    const emailInput = useRef(null)
    const passwordInput = useRef(null)

    const validateUserInput = () => {
        if (emailInput.current.value == "")
            return false
        return true
    }

    const submitUserInput = () => {
        if (!validateUserInput()){
            setErrorMsg(true)
            emailInput.current.value = ""
            passwordInput.current.value = ""
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
        <div className='form'>
            {errorMsg && <div className='error-msg'>
                <img src={danger_img} />
                <div>Incorrect email or password.</div>
            </div>}
            <div className='field'>
                <p>Email</p>
                <input ref={emailInput} type='text' placeholder='email address'/>
            </div>
            <div className='field'>
                <p>Password</p>
                <input ref={passwordInput} type='password' placeholder='password'/>
            </div>
            <button className='action-button' onClick={()=>{
                submitUserInput()
            }}>Log In</button>
            <div className='other-page'>
                <div>Don't have an account?</div> 
                <button onClick={props.goToRegistration}>Sign up</button>
            </div>
        </div>
    </div>
  )
}

export default Login
