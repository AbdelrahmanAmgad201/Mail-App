import { useState, useEffect, useRef } from 'react'
import './style.css'
import danger_img from './assets/danger_negative.png'

function Login(props) {
    const [errorMsg, setErrorMsg] = useState(false);

    const emailInput = useRef(null)
    const passwordInput = useRef(null)

    const submitUserInput = async () => {
        props.user.current.email = emailInput.current.value
        props.user.current.password = passwordInput.current.value

        const data = {
            email: emailInput.current.value,
            password: passwordInput.current.value
        }
        const url = 'http://localhost:8080/api/registration/login'
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
                const result = await response.text();
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
            <button className='action-button' onClick={async ()=>{
                await submitUserInput()
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
