import './Inbox.css'
import { useState, useEffect } from 'react'


function Email(props) {

  const [starred, setStarred] = useState(props.allEmail.isStarred)

  const toggleEmailStar = async () => {
    const url = 'http://localhost:8080/api/starred/star/' + props.user.current.id + '/' + props.allEmail.email.emailId
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.text();
            console.log(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  const deleteEmail = async () => {
    const url = 'http://localhost:8080/api/emails/trash/' + props.user.current.id + '/' + props.allEmail.email.emailId
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.text();
            console.log(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  useEffect(()=>{
  }, [])

  return (
    <div className='email-card'>
      
      <button> <img src="src/inbox/pics/stop.png" alt="Icon" /></button>

      <button onClick={async () => {
        await toggleEmailStar()
        setStarred(!starred)
      }}> 
      {!starred ? (
          <img src="src/inbox/pics/star-empty.png" alt="Icon" />
        ) : (
          <img src="src/inbox/pics/star.png" alt="Icon" />
        )}
      </button>

      <p>{props.allEmail.email.sender.emailAddress}</p>

      <p>{props.allEmail.email.subject}</p>

      <p>{props.allEmail.email.metadata.dateSent}</p>

      <button onClick={async ()=>{
        await deleteEmail()
        props.reload()
      }}>
        <img src="src/inbox/pics/recycle-bin.png" alt="Icon" />
      </button>

    </div>
  );
}

export default Email;
