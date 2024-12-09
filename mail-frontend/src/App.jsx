import { useState, useEffect } from 'react'
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

function App(props) {

  const [showAddFolderPopup, setShowAddFolderPopup] = useState(false)
  const [showDefaultSideBar, setshowDefaultSideBar] = useState(true)
  const [showContactMenu, setShowContactMenu] = useState(false)

  useEffect(() => {
    // setShowAddFolderPopup(true)
    setshowDefaultSideBar(false)
    setShowContactMenu(true)
    return () => {
        
    }
  }, []);

  return (
    <div className='main'>
      {showAddFolderPopup && <Add_Folder_Popup />}
      <div className='side-bar'>
        { showDefaultSideBar && <div className='default'>
          <button className='compose-btn'><img src={edit_img}/><div>Compose</div></button>

          <button className='default-btns'><img src={inbox_img}/><div>Inbox</div></button>
          <button className='default-btns'><img src={star_img}/><div>Starred</div></button>
          <button className='default-btns'><img src={sent_img}/><div>Sent</div></button>
          <button className='default-btns'><img src={file_img}/><div>Drafts</div></button>
          <button className='default-btns'><img src={bin_img}/><div>Trash</div></button>

          <button className='contacts-btn'><img src={contact_img}/><div>Contacts</div></button>
          <div className='folders'>
            <div className='title'>
              <p>Folders</p>
              <button><img src={plus_img}/></button>
            </div>
            <button className='folder-btn'>university</button>
          </div>
        </div>}

        {showContactMenu && <ContactMenu />}

      </div>
      <div className='main-body'>
        <div className='top-bar'>
          <div className='search-bar'>
            <button className='search-img'><img src={search_img}/></button>
            <input type='text' placeholder='Search' />
            <button className='filter-img'><img src={filter_img}/></button>
          </div>
          <div className='user-email'>{props.email}</div>
        </div>
        <div className='main-app'>
          <Inbox />
        </div>
      </div>
    </div>
  )
}

export default App
