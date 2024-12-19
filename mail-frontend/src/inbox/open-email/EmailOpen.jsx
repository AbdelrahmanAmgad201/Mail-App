import { useState, useRef, useEffect } from 'react';
import './EmailOpen.css';


function EmailOpen(props) {
  const [isDropdownVisible, setDropdownVisible] = useState(false);
  const [isSortMenuVisible, setSortMenuVisible] = useState(false);
  const [starred, setStarred] = useState(props.mail.current.isStarred)
  const dropdownRef = useRef(null);
  const sortMenuRef = useRef(null);


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

  const toggleEmailStar = async () => {
    const url = 'http://localhost:8080/api/starred/star/' + props.user.current.id + '/' + props.mail.current.email.emailId
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
    const url = 'http://localhost:8080/api/emails/trash/' + props.user.current.id + '/' + props.mail.current.email.emailId
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
      <div className='selection-bar'>
            <button onClick={()=>{
              props.setShowOpenEmail(false)
            }}><img src="src/app-assets/appIcons/left.png" alt="Icon"/></button>
          <div className='select-btn'>
            <button>
              <img src="src/app-assets/appIcons/info.png" alt="Icon" />
            </button>
            <button onClick={toggleDropdown}>
              <img src="src/inbox/pics/down-arrow.png" alt="Icon" />
            </button>
          </div>
        <button onClick={async ()=>{
          await toggleEmailStar()
          setStarred(!starred)
        }}>
          {!starred ? (
            <img src="src/inbox/pics/star-empty.png" alt="Icon" />
          ) : (
            <img src="src/inbox/pics/star.png" alt="Icon" />
          )}
        </button>
        {/* <button className='sort-btn' onClick={toggleSortMenu}>
          <img src="src/app-assets/appIcons/priority.png" alt="Icon" />
        </button> */}
        <button onClick={async ()=>{
          await deleteEmail()
          await props.reload()
          props.setShowOpenEmail(false)
        }}><img src="src/app-assets/appIcons/recycle-bin.png" alt="Icon" /></button>
        <p className='from-email'>{props.mail.current.email.subject}</p>
      </div>
        <div className='menus-email-toggling'>
            {isDropdownVisible && (
                    <div className='select-menu' ref={dropdownRef}>
                      <p>from: {props.mail.current.email.sender.emailAddress}</p>
                      <p>to: {props.mail.current.receivers.map((item)=>{return item.emailAddress+'; '})}</p>
                      <p>date: {props.mail.current.email.metadata.dateSent}</p>
                      <p>subject: {props.mail.current.email.subject}</p>
                    </div>
            )}
            {/* {isSortMenuVisible && (
                <div className='sort-menu' ref={sortMenuRef}>
                  <p>low</p>
                  <p>medium</p>
                  <p>high</p>
                </div>
            )} */}

            <div className='email-body'>
                <p>{props.mail.current.email.body}</p>
                {/* <div className='reply-btns'>
                    <button>reply</button>
                    <button>forward</button>
                </div> */}
            </div>

      </div>

    </div>
  );
}

export default EmailOpen;
