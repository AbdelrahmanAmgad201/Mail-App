import { useState } from 'react'
import './App.css'
import './SideBar.css'
import './TopBar.css'
import star_img from "./appIcons/star.png"
import colored_star_img from "./appIcons/colored_star.png"
import sent_img from "./appIcons/sent.png"
import inbox_img from "./appIcons/inbox.png"
import file_img from "./appIcons/file.png"
import edit_img from "./appIcons/edit.png"
import bin_img from "./appIcons/bin.png"
import contact_img from "./appIcons/contact-book.png"
import plus_img from "./appIcons/plus.png"
import search_img from "./appIcons/search.png"
import filter_img from "./appIcons/filter.png"

function App() {

  return (
    <div className='main'>
      <div className='side-bar'>
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
            <img src={plus_img}/>
          </div>
          <button className='folder-btn'>university</button>
        </div>
      </div>
      <div className='main-body'>
        <div className='top-bar'>
          <div className='search-bar'>
            <button className='search-img'><img src={search_img}/></button>
            <input type='text' placeholder='Search' />
            <button className='filter-img'><img src={filter_img}/></button>
          </div>
          <div className='user-email'></div>
        </div>
        <div className='main-app'>

        </div>
      </div>
    </div>
  )
}

export default App
