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

    const submitUserInput = async () => {
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

        const data = {
            email: emailInput.current.value,
            password: passwordInput.current.value
        }
        const url = 'http://localhost:8080/api/registration/signup'
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
    
            // Handle the response
            if (response.ok) {
                const result = await response.json();
                console.log(result)
                props.user.current.id = result
                props.goToApp()
            } 
            else {
                setErrorMsg(true)
            }
        } catch (error) {
            console.error('Network error:', error);
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
                <div>Invalid credentials</div>
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
            <button className='action-button' onClick={async ()=>{
                await submitUserInput()
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
