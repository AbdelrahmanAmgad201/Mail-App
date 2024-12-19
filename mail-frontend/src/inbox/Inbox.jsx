import { useState, useRef, useEffect } from 'react';
import './Inbox.css';
import Email from './Email';
import EmailOpen from './open-email/EmailOpen.jsx'

function Inbox(props) {
  const [isDropdownVisible, setDropdownVisible] = useState(false)
  const [isSortMenuVisible, setSortMenuVisible] = useState(false)
  const [showOpenEmail, setShowOpenEmail] = useState(false)

  const openedMailRef = useRef(null)
  const dropdownRef = useRef(null)
  const sortMenuRef = useRef(null)
  
  
  const [selectedMails, setSelectedMails] = useState([]);

  const selectMailAction = (mail) => {
    setSelectedMails((prevSelectedMails) => {
      // Find the index of the mail
      const index = prevSelectedMails.findIndex(item => item.email.emailId === mail.email.emailId);

      if (index > -1) {
        // If mail exists, remove it
        return prevSelectedMails.filter((_, i) => i !== index);
      } else {
        // If mail doesn't exist, add it
        return [...prevSelectedMails, mail];
      }
    });
  };

  const handleClickOutside = (event) => {
    if (
      dropdownRef.current &&
      !dropdownRef.current.contains(event.target) &&
      !event.target.closest('.select-btn')
    ) {
      setDropdownVisible(false);
    }
    if (
      sortMenuRef.current &&
      !sortMenuRef.current.contains(event.target) &&
      !event.target.closest('.sort-btn')
    ) {
      setSortMenuVisible(false);
    }
  };

  useEffect(() => {
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const toggleDropdown = () => {
    setDropdownVisible((prev) => !prev);
    if (isSortMenuVisible) {
      setSortMenuVisible(false);
    }
  };

  const toggleSortMenu = () => {
    setSortMenuVisible((prev) => !prev);
    if (isDropdownVisible) {
      setDropdownVisible(false);
    }
  };

  const deleteEmail = async (mail) => {
    const url = 'http://localhost:8080/api/emails/trash/' + props.user.current.id + '/' + mail.email.emailId
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

  return (
    <div className='emails-bar'>
      {!showOpenEmail && <div className='emails-list'>
        <div className='selection-bar'>
            <div className='select-btn'>
              {(selectedMails.length > 0) && 
                <button onClick={async ()=>{
                  await selectedMails.map(async (mail) => {
                    await deleteEmail(mail)
                  })
                  setSelectedMails([])
                }}>
                  <img src="src/inbox/pics/recycle-bin.png"/>
                </button>
              }
              <button>
                <img src="src/inbox/pics/stop.png" alt="Icon" />
              </button>
              <button onClick={toggleDropdown}>
                <img src="src/inbox/pics/down-arrow.png" alt="Icon" />
              </button>
            </div>
          <button onClick={()=>{
            props.reload()
          }}><img src="src/inbox/pics/reload.png" alt="Icon" /></button>
          <button className='sort-btn' onClick={toggleSortMenu}>
            <img src="src/inbox/pics/vertical-dots.png" alt="Icon" />
          </button>

        </div>
          <div className='menus-toggling'>
            {isDropdownVisible && (
                    <div className='select-menu' ref={dropdownRef}>
                      <p onClick={()=>{
                        setSelectedMails(props.emails)
                        setDropdownVisible(false);
                      }}>all</p>
                      <p onClick={()=>{
                        setSelectedMails([])
                        setDropdownVisible(false);
                      }}>none</p>
                      <p onClick={()=>{
                        let newSelectedEmails = []
                        props.emails.map((mail, index)=>{
                          console.log(index, mail.isStarred)
                          if (mail.isStarred) 
                            newSelectedEmails.push(mail)
                        })
                        setSelectedMails(newSelectedEmails)
                        setDropdownVisible(false);
                      }}>starred</p>
                      <p onClick={() => {
                        let newSelectedEmails = []
                        props.emails.map((mail)=>{
                          if (mail.isStarred == null || mail.isStarred == false) 
                            newSelectedEmails.push(mail)
                        })
                        setSelectedMails(newSelectedEmails)
                        setDropdownVisible(false);
                      }}>unstarred</p>
                    </div>
            )}
            {isSortMenuVisible && (
                <div className='sort-menu' ref={sortMenuRef}>
                  <p onClick={()=>{
                    props.setSort("date")
                    props.reload()
                  }}>date</p>
                  <p onClick={()=>{
                    props.setSort("priority")
                    props.reload()
                  }}>priority</p>
                </div>
            )}
            <div className='emailsDisplay'>
              {props.emails.map((allEmail, index) => (
                <Email key={index} allEmail={allEmail} user={props.user} reload={props.reload} setShowOpenEmail={setShowOpenEmail} openedMailRef={openedMailRef} selectedMails={selectedMails} selectMailAction={selectMailAction}/>
              ))}
            </div>
        </div>
      </div>}
      {showOpenEmail && <EmailOpen reload={props.reload} user={props.user} setShowOpenEmail={setShowOpenEmail} mail={openedMailRef} />}
    </div>
  );
}

export default Inbox;
