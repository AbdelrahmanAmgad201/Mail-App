import './Inbox.css'


function Email(props) {
  return (
    <div className='email-card'>
      
      <button> <img src="src/inbox/pics/stop.png" alt="Icon" /></button>

      <button> <img src="src/inbox/pics/star (2).png" alt="Icon" /></button>

      <p>{props.sender}</p>

      <p>{props.subject}</p>

      <p>{props.date}</p>

      <button> <img src="src/inbox/pics/recycle-bin.png" alt="Icon" /></button>

    </div>
  );
}

export default Email;
