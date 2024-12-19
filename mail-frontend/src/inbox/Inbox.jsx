import { useState, useRef, useEffect } from 'react';
import './Inbox.css';
import Email from './Email';
import EmailOpen from './open-email/EmailOpen.jsx'

function Inbox(props) {
  const [isDropdownVisible, setDropdownVisible] = useState(false)
  const [isSortMenuVisible, setSortMenuVisible] = useState(false)
  const [showOpenEmail, setShowOpenEmail] = useState(false)

  const selectedMails = useRef([])
  const openedMailRef = useRef(null)
  const dropdownRef = useRef(null)
  const sortMenuRef = useRef(null)

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

  return (
    <div className='emails-bar'>
      {!showOpenEmail && <div className='emails-list'>
        <div className='selection-bar'>
            <div className='select-btn'>
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
                      <p>all</p>
                      <p>none</p>
                      <p>starred</p>
                      <p>unstarred</p>
                      <p>read</p>
                      <p>unread</p>
                    </div>
            )}
            {isSortMenuVisible && (
                <div className='sort-menu' ref={sortMenuRef}>
                  <p>date</p>
                  <p>priority</p>
                </div>
            )}
            <div className='emailsDisplay'>
              {props.emails.map((allEmail, index) => (
                <Email key={index} allEmail={allEmail} user={props.user} reload={props.reload} setShowOpenEmail={setShowOpenEmail} openedMailRef={openedMailRef}/>
              ))}
            </div>
        </div>
      </div>}
      {showOpenEmail && <EmailOpen reload={props.reload} user={props.user} setShowOpenEmail={setShowOpenEmail} mail={openedMailRef} />}
    </div>
  );
}

export default Inbox;
