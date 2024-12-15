import { useState, useRef, useEffect } from 'react';
import './Folder.css';
import Email from "../inbox/Email.jsx"; // going one level up


function Folder() {
  const [isDropdownVisible, setDropdownVisible] = useState(false);
  const [isSortMenuVisible, setSortMenuVisible] = useState(false);
  const dropdownRef = useRef(null);
  const sortMenuRef = useRef(null);


  const emailList = [
    { id: 1, subject: 'Welcome to the app!', sender: 'no-reply@example.com' },
    { id: 2, subject: 'Your account has been updated', sender: 'support@example.com' },
    { id: 3, subject: 'Don’t miss our new features', sender: 'newsletter@example.com' },
    { id: 4, subject: 'Password reset request', sender: 'security@example.com' },
    { id: 5, subject: 'Weekly digest', sender: 'updates@example.com' },
  ];

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

        <div className='selection-bar'>
                <div className='select-btn'>
                  <button>
                    <img src="src/inbox/pics/stop.png" alt="Icon" />
                  </button>
                  <button onClick={toggleDropdown}>
                    <img src="src/inbox/pics/down-arrow.png" alt="Icon" />
                  </button>
                </div>
              <button><img src="src/inbox/pics/reload.png" alt="Icon" /></button>
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
            
            
              <div className='folder-heading-info'>

                  <div>
                    <p>FolderType: </p>
                    <p>(ContactName)(Subject)</p>
                  </div>

                  <p>ContactEmail</p>

              </div>

              <div className='folder-emails-display'>
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

                {/* Dynamically render Email components
                {emailList.map((email) => (
                  <Email key={email.id} subject={email.subject} sender={email.sender} />
                ))} */}

              </div>

              <p className='pagination'>pagination</p>
              

      </div>

    </div>
  );
}

export default Folder;
