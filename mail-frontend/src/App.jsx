import { useRef,useState, useEffect } from 'react'
import './app-assets/App.css'
import './app-assets/SideBar.css'
import './app-assets/TopBar.css'
import star_img from "./app-assets/appIcons/star.png"
import colored_star_img from "./app-assets/appIcons/colored_star.png"
import sent_img from "./app-assets/appIcons/sent.png"
import inbox_img from "./app-assets/appIcons/inbox.png"
import file_img from "./app-assets/appIcons/file.png"
import edit_img from "./app-assets/appIcons/edit.png"
import bin_img from "./app-assets/appIcons/bin.png"
import contact_img from "./app-assets/appIcons/contact-book.png"
import plus_img from "./app-assets/appIcons/plus.png"
import search_img from "./app-assets/appIcons/search.png"
import filter_img from "./app-assets/appIcons/filter.png"

import Add_Folder_Popup from './add-folder-popup/add-folder-popup.jsx'
import Inbox from './inbox/Inbox'
import ContactMenu from './contact-menu/ContactMenu.jsx'
import SearchFilter from './search-filter-menu/SearchFilter.jsx'
import Folder from './folder/Folder.jsx'
import ComposeEmail from './inbox/ComposeEmail.jsx'


function App(props) {

  const [showAddFolderPopup, setShowAddFolderPopup] = useState(false)
  const [showDefaultSideBar, setShowDefaultSideBar] = useState(true)
  const [showContactMenu, setShowContactMenu] = useState(false);
  const [showFilterMenu, setShowFilterMenu] = useState(false);
  const [showComposeEmail, setShowComposeEmail] = useState(false);
  const [showEmails, setShowEmails] = useState(true);
  const [showFolder, setShowFolder] = useState(false);

  const loadEmails = useRef(()=>{})
  const filters = useRef({
    userId: props.user.current.id,
    subjects: [],
    senders: [],
    startDate: null,
    endDate: null,
    receivers: [],
    priority: 'LOW',
    body: "",
    attachment: ""
  })

  const [emails, setEmails] = useState([])
  const [folders, setFolders] = useState([])

  const filterDivRef = useRef(null);

  const closeFolderPopup = () => {
    setShowAddFolderPopup(false)
  }

  const closeContactMenu = () => {
    setShowContactMenu(false)
    setShowDefaultSideBar(true)
  }

  const closeALLApps = () => {
    setShowEmails(false)
    setShowFolder(false)
    setShowComposeEmail(false)
  }

  
  const loadInboxEmails = async () => {
    const url = 'http://localhost:8080/api/receivers/inbox/' + props.user.current.id
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.json();
            console.log(result)
            setEmails(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  const loadTrashEmails = async () => {
    const url = 'http://localhost:8080/api/receivers/trash/' + props.user.current.id
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.json();
            console.log(result)
            setEmails(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  const loadSentEmails = async () => {
    const url = 'http://localhost:8080/api/receivers/sent/' + props.user.current.id
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.json();
            console.log(result)
            setEmails(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  const loadStarredEmails = async () => {
    const url = 'http://localhost:8080/api/receivers/starred/' + props.user.current.id
    console.log(url)
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.json();
            console.log(result)
            setEmails(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }
  

  const loadFolders = async () => {
    const url = 'http://localhost:8080/api/folders/user/' + props.user.current.id
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            const result = await response.json();
            console.log(result)
        } 
    } 
    catch (error) {
        console.error('Network error:', error);
    }
  }

  useEffect(() => { 
    loadInboxEmails()
    loadEmails.current = loadInboxEmails

    const handleOutsideClick = (event) => {
      if (filterDivRef.current && !filterDivRef.current.contains(event.target)) {
        setShowFilterMenu(false); // Close menu if clicked outside
      }
    };

    document.addEventListener('mousedown', handleOutsideClick);

    return () => {
      document.removeEventListener('mousedown', handleOutsideClick);
    };
  }, []);

  const toggleFilterMenu = () => {
    setShowFilterMenu((prev) => !prev);
  };

  return (
    <div className='main'>
      {showAddFolderPopup && <Add_Folder_Popup user={props.user} closeFolderPopupFun={closeFolderPopup}/>}
      <div className='side-bar'>
      { showDefaultSideBar && <div className='default'>
          <button className='compose-btn' onClick={()=>{
            closeALLApps()
            setShowComposeEmail(true)
          }}><img src={edit_img}/><div>Compose</div></button>

          <button className='default-btns' onClick={async ()=>{
            closeALLApps()
            await loadInboxEmails()
            loadEmails.current = loadInboxEmails
            setShowEmails(true)
          }} ><img src={inbox_img}/><div>Inbox</div></button>

          <button className='default-btns' onClick={async ()=>{
            closeALLApps()
            await loadStarredEmails()
            loadEmails.current = loadStarredEmails
            setShowEmails(true)
          }}><img src={star_img}/><div>Starred</div></button>

          <button className='default-btns' onClick={async ()=>{
            closeALLApps()
            await loadSentEmails()
            loadEmails.current = loadSentEmails
            setShowEmails(true)
          }}><img src={sent_img}/><div>Sent</div></button>

          <button className='default-btns'><img src={file_img}/><div>Drafts</div></button>

          <button className='default-btns' onClick={async ()=>{
            closeALLApps()
            await loadTrashEmails()
            loadEmails.current = loadTrashEmails
            setShowEmails(true)
          }}><img src={bin_img}/><div>Trash</div></button>

          <button className='contacts-btn' onClick={() => {
            setShowContactMenu(true)
            setShowDefaultSideBar(false)
          }}>
            <img src={contact_img}/><div>Contacts</div></button>
          <div className='folders'>
            <div className='title'>
              <p>Folders</p>
              <button onClick={() => {setShowAddFolderPopup(true)}}><img src={plus_img}/></button>
            </div>
            <button className='folder-btn'>university</button>
          </div>
        </div>}

        {showContactMenu && <ContactMenu closeMenu={closeContactMenu} user={props.user}/>}

      </div>

      <div className='main-body'>
        <div className='top-bar'>
          <div className='search-bar'>
            <button className='search-img'><img src={search_img}/></button>
            <input type='text' placeholder='Search' />
            <div className='filter-div' >
                <button className='filter-img' onClick={toggleFilterMenu}><img src={filter_img}/></button>
            </div>
          </div>
          {showFilterMenu && (
                <div className='filter-options' ref={filterDivRef}>
                  <SearchFilter filters={filters}/>
                  <button className='btn-search' onClick={toggleFilterMenu}>Search</button>
                </div>
          )}
          <div className='user-email'>{props.email}</div>
        </div>
        <div className='main-app'>
          {showComposeEmail && <ComposeEmail user={props.user}/>}
          {showFolder && <Folder />}
          {showEmails && <Inbox user={props.user} emails={emails} reload={loadEmails.current}/>}
        </div>
      </div>
    </div>
  )
}

export default App