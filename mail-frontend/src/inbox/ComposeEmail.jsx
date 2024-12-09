import './Inbox.css'


function ComposeEmail() {
    return (
    <div className='email-compose'>
       <div className='email-top-bar'>

        <p>New Message</p>
        <button><img src="src/inbox/pics/close.png" alt="Icon" /></button>

       </div>

       <div className='email-main'>

            <input type='email' className='to' placeholder='Recipient email' />

            <input type='text' className='subject' placeholder='Email subject' />

            <textarea className='body' placeholder='Write your email here...' rows='21' ></textarea>

       </div>

       <div className='email-bottom-bar'>
        <button><img src="src/inbox/pics/send-message.png" alt="Icon" /></button>
        <button><img src="src/inbox/pics/attach-document.png" alt="Icon" /></button>
        <button><img src="src/inbox/pics/image.png" alt="Icon" /></button>
       </div>

    </div>
    )
}

export default ComposeEmail;