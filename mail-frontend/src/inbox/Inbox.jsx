import './Inbox.css'
import Email from './Email'

function Inbox() {

  return (
    <div className='emails-bar'>

    <div className='selection-bar'>
      
      <div className='select-btn'>
        <button><img src="src/inbox/pics/stop.png" alt="Icon" /></button>
        <button><img src="src/inbox/pics/down-arrow.png" alt="Icon" /></button>
      </div>

      <button><img src="src/inbox/pics/reload.png" alt="Icon" /></button>

      <button><img src="src/inbox/pics/vertical-dots.png" alt="Icon" /></button>

    </div>

    <div className='emailsDisplay'>
      
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />
      <Email />

    </div>

    
  </div>

  )
}

export default Inbox;
