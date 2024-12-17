import { useState, useRef, useEffect } from 'react';
import './Inbox.css'


function ComposeEmail(props) {

    const recipientsEmails = useRef(null)
    const subject = useRef(null)
    const body = useRef(null)

    const submitEmail = async () => {
        // private Long senderId;
        // private List<String> receiverEmails;
        // private String subject;
        // private String body;
        // private String priority; // Can be LOW, MEDIUM, HIGH
        const data = {
            senderId: props.user.current.id,
            receiverEmails: recipientsEmails.current.value.split(';')
                                .map(word => word.trim())  // Trim spaces
                                .filter(word => word.length > 0),
            subject: subject.current.value,
            body: body.current.value,
            priority: "LOW"
        }
        console.log(data)
        const url = 'http://localhost:8080/api/emails/send'
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (response.ok) {
                const result = await response.json();
                console.log(result)
                props.goToApp()
            } 
        } 
        catch (error) {
            console.error('Network error:', error);
        }
    }

    return (
    <div className='email-compose'>
       <div className='email-top-bar'>

        <p>New Message</p>
        <button><img src="src/inbox/pics/close.png" alt="Icon" /></button>

       </div>

       <div className='email-main'>

            <input ref={recipientsEmails} type='email' className='to' placeholder='Recipient email' />
            <input ref={subject} type='text' className='subject' placeholder='Email subject' />
            <textarea ref={body} className='body' placeholder='Write your email here...' rows='21' ></textarea>

       </div>

       <div className='email-bottom-bar'>
        <button onClick={async () => {
            submitEmail()
        }}><img src="src/inbox/pics/send-message.png" alt="Icon" /></button>
        <button><img src="src/inbox/pics/attach-document.png" alt="Icon" /></button>
        <button><img src="src/inbox/pics/image.png" alt="Icon" /></button>
       </div>

    </div>
    )
}

export default ComposeEmail;