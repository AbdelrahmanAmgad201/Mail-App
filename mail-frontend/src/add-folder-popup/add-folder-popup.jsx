import { useState, useEffect } from "react"
import "./add-folder-popup.css"

function Add_Folder_Popup(){
    return (
        <div className="popup-body">
            <div className="content">
                <div className="title">Add folder</div>
                <div className="folder-name-input">

                </div>
                <div className="input-field">
                    <p>Subject</p>
                    <div></div>
                </div>
                <div className="input-field">
                    <p>Contacts</p>
                    <div></div>
                </div>
            </div>
        </div>
    )
}

export default Add_Folder_Popup