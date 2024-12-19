import { useState, useEffect, useRef } from "react";
import "./add-folder-popup.css";
import add_img from "./assests/plus.png";
import remove_img from "./assests/minus.png";

function Add_Folder_Popup(props) {
  const [contactsMenu, setContactsMenu] = useState(false);
  const [addFolderMenu, setAddFolderMenu] = useState(true);
  const [contacts, setContacts] = useState([]); // State to store contacts
  const subjectInput = useRef(null);
  const popupBody = useRef(null);
  const choosenData = useRef({
    name: "",
    subjects: [],
    contacts: []
  });

  // Fetch contacts when the component mounts
  useEffect(() => {
    const ownerId = props.user.current.id;
    fetch(`http://localhost:8080/api/contacts/user/${ownerId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch contacts");
        }
        return response.json();
      })
      .then((data) => {
        setContacts(data); // Update contacts state with the fetched data
      })
      .catch((error) => {
        console.error("Error fetching contacts:", error);
      });
  }, [props.user.current.id]);
  console.log(contacts);

  const showContactsMenu = () => {
    setContactsMenu(true);
    setAddFolderMenu(false);
  };

  const showAddFolderMenu = () => {
    setContactsMenu(false);
    setAddFolderMenu(true);
  };

  const addContactToData = (newContact) => {
    choosenData.current.contacts.push(newContact);
  };

  const removeContactFromData = (index) => {
    choosenData.current.contacts.splice(index, 1);
  };

  const addSubjectToData = () => {
    if (subjectInput.current.value.replace(/\s+/g, " ").trim() === "") return;
    choosenData.current.subjects.push(
      subjectInput.current.value.replace(/\s+/g, " ").trim()
    );
    subjectInput.current.value = "";
  };

  const removeSubjectFromData = (index) => {
    choosenData.current.subjects.splice(index, 1);
  };

  console.log(choosenData.current.subjects)
  const addFolder = async () => {
    const data = {
      userId: props.user.current.id,
      folderName: choosenData.current.name,
      subjectFilters: choosenData.current.subjects,
      memberIds: choosenData.current.contacts.map((contact) => 
                            contact.members.map((member) => member.memberId)
                       )
      
    };
  
    const params = new URLSearchParams();
    params.append('userId', data.userId);
    params.append('folderName', data.folderName);
    
    // Handle arrays
    data.subjectFilters.forEach(subject => 
      params.append('subjectFilters', subject)
    );
    data.memberIds.forEach(id => 
      params.append('memberIds', id)  // No need to convert to string, already numeric
    );
  
    const url = "http://localhost:8080/api/folders/create-with-filters";
    
    try {
      const response = await fetch(`${url}?${params.toString()}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        }
      });
      
      if (response.ok) {
        const folderOwner = await response.json();
        console.log("Folder created:", folderOwner);
        return folderOwner;
      } else {
        const error = await response.text();
        console.error("Error creating folder:", error);
        throw new Error(error);
      }
    } catch (error) {
      console.error("Network error:", error);
      throw error;
    }
  };

  const closeMenu = (event) => {
    if (event.target === event.currentTarget) {
      props.closeFolderPopupFun();
    }
  };

  useEffect(() => {
    popupBody.current.addEventListener("click", closeMenu);
    return () => {
      popupBody.current.removeEventListener("click", closeMenu);
    };
  }, []);
  console.log(choosenData.current.subjects)
  return (
    <div ref={popupBody} className="popup-body">
      {contactsMenu && (
        <div className="popup-contacts">
          <div className="title">Contacts</div>
          {contacts.length > 0 ? (
            contacts.map((contact, index) => (
              <button
                key={index}
                onClick={() => {
                  addContactToData(contact); // Call with the contact's name
                  showAddFolderMenu();
                }}
              >
                {contact.contactName}
              </button>
            ))
          ) : (
            <p>No contacts available</p>
          )}
        </div>
      )}
      {addFolderMenu && (
        <div className="content">
          <div className="title">
            <p>New folder</p>
          </div>
          <div className="folder-name-input">
            <p>Name</p>
            <input
              onChange={(e) => {
                choosenData.current.name = e.target.value;
              }}
              type="text"
            />
          </div>
          <div className="input-field">
            <p className="input-field-title">Subject</p>
            <div className="input-choosed">
              {choosenData.current.subjects.map((item, index) => (
                <div key={index}>
                  <p>{item}</p>
                  <button
                    className="remove-button"
                    onClick={() => {
                      removeSubjectFromData(index);
                    }}
                  >
                    <img src={remove_img} />
                  </button>
                </div>
              ))}
            </div>
            <div>
              <input
                ref={subjectInput}
                className="add-subject-input"
                type="text"
                placeholder="Add subject"
              />
            </div>
            <button
              className="add-button"
              onClick={() => {
                addSubjectToData();
              }}
            >
              <img src={add_img} />
            </button>
          </div>
          <div className="input-field">
            <p className="input-field-title">Contacts</p>
            <div className="input-choosed">
              {choosenData.current.contacts.map((item, index) => (
                <div key={index}>
                  <p>{item.contactName}</p>
                  <button
                    className="remove-button"
                    onClick={() => {
                      removeContactFromData(index);
                    }}
                  >
                    <img src={remove_img} />
                  </button>
                </div>
              ))}
            </div>
            <button
              className="add-button"
              onClick={() => {
                showContactsMenu();
              }}
            >
              <img src={add_img} />
            </button>
          </div>
          <div className="add-folder-dev">
            <button
              onClick={() => {
                addFolder();
              }}
              className="add-folder-button"
            >
              Add Folder
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Add_Folder_Popup;
