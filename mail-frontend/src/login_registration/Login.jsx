import { useState, useEffect } from 'react'
import './style.css'
import danger_img from './assets/danger_negative.png'

function Login() {
    const [errorMsg, setErrorMsg] = useState(false);

    useEffect(() => {
        setErrorMsg(true)
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
                <input type='text' placeholder='email address'/>
            </div>
            <div className='field'>
                <p>Password</p>
                <input type='password' placeholder='password'/>
            </div>
            <button className='action-button'>Log In</button>
            <div className='other-page'>
                <div>Don't have an account?</div> 
                <button>Sign up</button>
            </div>
        </div>
    </div>
  )
}

export default Login
