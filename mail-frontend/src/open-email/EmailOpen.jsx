import { useState, useRef, useEffect } from 'react';
import './EmailOpen.css';


function EmailOpen() {
  const [isDropdownVisible, setDropdownVisible] = useState(false);
  const [isSortMenuVisible, setSortMenuVisible] = useState(false);
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

  return (
    <div className='emails-bar'>
      <div className='selection-bar'>
            <button><img src="src/app-assets/appIcons/left.png" alt="Icon"/></button>
          <div className='select-btn'>
            <button>
              <img src="src/app-assets/appIcons/info.png" alt="Icon" />
            </button>
            <button onClick={toggleDropdown}>
              <img src="src/inbox/pics/down-arrow.png" alt="Icon" />
            </button>
          </div>
        <button><img src="src/app-assets/appIcons/star.png" alt="Icon" /></button>
        <button className='sort-btn' onClick={toggleSortMenu}>
          <img src="src/app-assets/appIcons/priority.png" alt="Icon" />
        </button>
        <button><img src="src/app-assets/appIcons/recycle-bin.png" alt="Icon" /></button>
        <p className='from-email'>III@gmail.com</p>
      </div>
        <div className='menus-email-toggling'>

            {isDropdownVisible && (
                    <div className='select-menu' ref={dropdownRef}>
                      <p>from:</p>
                      <p>to:</p>
                      <p>date:</p>
                      <p>subject:</p>
                    </div>
            )}
            {isSortMenuVisible && (
                <div className='sort-menu' ref={sortMenuRef}>
                  <p>low</p>
                  <p>medium</p>
                  <p>high</p>
                </div>
            )}

            <div className='email-body'>
                <p>Body here</p>
                <div className='reply-btns'>
                    <button>reply</button>
                    <button>forward</button>
                </div>
            </div>

      </div>

    </div>
  );
}

export default EmailOpen;
