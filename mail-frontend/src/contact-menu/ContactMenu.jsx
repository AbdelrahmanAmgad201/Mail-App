import { useState,useEffect } from 'react';
import './ContactMenu.css';


function ContactMenu(props) {
    const [contacts, setContacts] = useState([]);
    const [activeContact, setActiveContact] = useState(null);
    const [showAddPopup, setShowAddPopup] = useState(false);
    const [newContact, setNewContact] = useState({ firstName: '', lastName: '', emails: [''] });
    const [isError, setIsError] = useState(false);

    const ownerId = props.user.current.id;
    const invalidEmailinpopup = () => {
        setIsError(true); 
    };
    

    useEffect(() => {
        // Fetch contacts from the backend API
        fetch(`http://localhost:8080/api/contacts/user/${ownerId}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then((data) => {
                setContacts(data); // Update state with the fetched contacts
                console.log('Fetched contacts:', data); // Optional: See the fetched data in console
            })
            .catch((error) => {
                console.error('There was an error fetching the contacts!', error);
            });
    }, [ownerId]);

    const handleContactClick = (contactIndex) => {
        setActiveContact(activeContact === contactIndex ? null : contactIndex);
    };

    const handleAddContact = async () => {
        try {
            const queryParams = new URLSearchParams({
                ownerId,
                contactName: `${newContact.firstName} ${newContact.lastName}`,
            });

            // Make the POST request to the backend
            const response = await fetch(`http://localhost:8080/api/contacts/create?${queryParams.toString()}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newContact.emails),
            });

            if (!response.ok) {
                throw new Error('Failed to add contact');
            }

            const addedContact = await response.json();

            // Update the contacts state
            setContacts((prevContacts) => [...prevContacts, addedContact]);

            // Close the popup and reset the form
            setShowAddPopup(false);
            
            setNewContact({ firstName: '', lastName: '', emails: [''] });
        } catch (error) {
            invalidEmailinpopup()
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewContact((prev) => ({ ...prev, [name]: value }));
    };

    const handleEmailChange = (index, value) => {
        setNewContact((prev) => {
            const updatedEmails = [...prev.emails];
            updatedEmails[index] = value;
            return { ...prev, emails: updatedEmails };
        });
    };

    const addEmailField = () => {
        setNewContact((prev) => ({ ...prev, emails: [...prev.emails, ''] }));
    };

    const removeEmailField = (index) => {
        setNewContact((prev) => {
            const updatedEmails = prev.emails.filter((_, i) => i !== index);
            return { ...prev, emails: updatedEmails };
        });
    };
    console.log(contacts)
    return (
        <div>
            <div className="contact-menu">
                <div className="contact-menu-btns">
                    <button onClick={() => props.closeMenu()}>
                        <img src="src/app-assets/appIcons/left.png" alt="Icon" />
                    </button>
                    <button onClick={() => setShowAddPopup(!showAddPopup)}>
                        <img src="src/app-assets/appIcons/plus.png" alt="Icon" />
                    </button>
                </div>
                <div className="contacts">
                    {contacts.length !== 0 ? (
                        contacts.map((contact, index) => (
                            <div key={index}>
                                <p onClick={() => handleContactClick(index)}>{contact.contactName}</p>
                                {activeContact === index && (
                                    <div className="contact-menu-dropdown">
                                        {contact.members.map((email, emailIndex) => (
                                            <p key={emailIndex}>{email.emailAddress}</p>
                                        ))}
                                    </div>
                                )}
                            </div>
                        ))
                    ) : (
                        <h1>No contacts available</h1>
                    )}
                </div>
                {showAddPopup && (
                    <div className="popup">
                        <div className="popup-content">
                            <h3>Add New Contact</h3>
                            <label>
                                First Name:
                                <input
                                    type="text"
                                    name="firstName"
                                    value={newContact.firstName}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label>
                                Last Name:
                                <input
                                    type="text"
                                    name="lastName"
                                    value={newContact.lastName}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label>Emails:</label>
                            {newContact.emails.map((email, index) => (
                                <div key={index} className="email-field">
                                    <input
                                        type="email"
                                        value={email}
                                        onChange={(e) => handleEmailChange(index, e.target.value)}
                                    />
                                    <button onClick={() => removeEmailField(index)}>Remove</button>
                                </div>
                            ))}
                            {isError && (
                                    <div style={{ color: 'red', fontSize: '14px' }}>
                                                Invalid emails! .
                                    </div>
                            )}
                            <button onClick={addEmailField}>Add Email</button>
                            <div className="popup-actions">
                                <button onClick={handleAddContact}>Add Contact</button>
                                <button onClick={() => setShowAddPopup(false)}>Cancel</button>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}


export default ContactMenu;
