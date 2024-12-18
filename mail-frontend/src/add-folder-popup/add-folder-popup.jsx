import { useState, useEffect, useRef } from "react"
import "./add-folder-popup.css"
import add_img from "./assests/plus.png"
import remove_img from "./assests/minus.png"

function Add_Folder_Popup(props){

    const [contactsMenu, setContactsMenu] = useState(false)
    const [addFolderMenu, setAddFolderMenu] = useState(true)
    const [, forceRender] = useState()
    // forceRender({})

    const subjectInput = useRef(null)
    const popupBody = useRef(null)

    const choosenData = useRef({
        name: "",
        subjects: [],
        contacts: []
    })

    const showContactsMenu = () => {
        setContactsMenu(true)
        setAddFolderMenu(false)
    }

    const showAddFolderMenu = () => {
        setContactsMenu(false)
        setAddFolderMenu(true)
    }

    const addContactToData = (newContact) => {
        choosenData.current.contacts.push(newContact)
    }

    const removeContactFromData = (index) => {
        choosenData.current.contacts.splice(index, 1)
        forceRender({})
    }

    const addSubjectToData = () => {
        if (subjectInput.current.value.replace(/\s+/g, ' ').trim() == "")
            return
        choosenData.current.subjects.push(subjectInput.current.value.replace(/\s+/g, ' ').trim())
        subjectInput.current.value = ""
        forceRender({})
    }

    const removeSubjectFromData = (index) => {
        choosenData.current.subjects.splice(index, 1)
        forceRender({})
    }

    const addFolder = async () => {
        const data = {
            userId: props.user.current.id,
            name: choosenData.current.name,
            subjects: choosenData.current.subjects,
            emails: ["j@g.c"]
        }
        console.log(data)
        const url = 'http://localhost:8080/api/folders/add'
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
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

    function Contacts_Menu() {
        return (
            <div className="popup-contacts">
                <div className="title">Contacts</div>
                <button onClick={() => {
                    addContactToData("contact1")
                    showAddFolderMenu()
                }}>contact1</button>
                <button>contact2</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
                <button>contact3</button>
            </div>
        )
    }

    const closeMenu = (event) => {
        if (event.target === event.currentTarget) {
            props.closeFolderPopupFun()
        }
    }

    useEffect(() => {
        popupBody.current.addEventListener('click', closeMenu)
        return () => {
            // popupBody.current.removeEventListener('click', closeMenu)
        }
      }, []);

    return (
        <div ref={popupBody} className="popup-body">
            {contactsMenu && Contacts_Menu()}
            {addFolderMenu && <div className="content">
                <div className="title">
                    <p>New folder</p>
                </div>
                <div className="folder-name-input">
                    <p>Name</p>
                    <input onChange={(e)=>{
                        choosenData.current.name = e.target.value
                    }} type="text"/>
                </div>
                <div className="input-field">
                    <p className="input-field-title">Subject</p>
                    <div className="input-choosed">
                        {
                            choosenData.current.subjects.map((item, index) => (
                                <div key={index}>
                                    <p>{item}</p>
                                    <button className="remove-button" onClick={() => {
                                        removeSubjectFromData(index)
                                    }}>
                                        <img src={remove_img}/>
                                    </button>
                                </div>
                            ))
                        }
                    </div>
                    <div>
                        <input ref={subjectInput} className="add-subject-input" type="text" placeholder="add subject"/>
                    </div>
                    <button className="add-button" onClick={() => {addSubjectToData()}}><img src={add_img}/></button>
                </div>
                <div className="input-field">
                    <p className="input-field-title">Contacts</p>
                    <div className="input-choosed">
                        {
                            choosenData.current.contacts.map((item, index) => (
                                <div key={index}>
                                    <p>{item}</p>
                                    <button className="remove-button" onClick={() => {
                                        removeContactFromData(index)
                                    }}>
                                        <img src={remove_img}/>
                                    </button>
                                </div>
                            ))
                        }
                    </div>
                    <button className="add-button" onClick={() => {
                        showContactsMenu()
                    }}>
                        <img src={add_img}/>
                    </button>
                </div>
                <div className="add-folder-dev">
                    <button onClick={()=>{
                        addFolder()
                    }} className="add-folder-button">Add Folder</button>
                </div>
            </div>}
        </div>
    )
}

export default Add_Folder_Popup