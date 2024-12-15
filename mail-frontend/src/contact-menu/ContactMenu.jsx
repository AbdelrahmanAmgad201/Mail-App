import { useState } from 'react';
import './ContactMenu.css';


function ContactMenu(props) {

    const contacts = Array.from({ length: 30 }, (_, index) => {
        const numberOfEmails = Math.floor(Math.random() * 5) + 1;
        return {
            name: `contact${index + 1}`,
            emails: Array.from({ length: numberOfEmails }, (_, emailIndex) => `email${index + 1}-${emailIndex + 1}`)
        };
    });

    const [activeContact, setActiveContact] = useState(null);

    const handleContactClick = (contactIndex) => {
        setActiveContact(activeContact === contactIndex ? null : contactIndex);
    };

    return (
        <div>
            <div className='contact-menu'>
                <div className="contact-menu-btns">
                    <button onClick={() => {
                        props.closeMenu()
                    }}>
                        <img src="src/app-assets/appIcons/left.png" alt="Icon" />
                    </button>
                    <button>
                        <img src="src/app-assets/appIcons/plus.png" alt="Icon" />
                    </button>
                </div>
                <div className="contacts">
                    {contacts.map((contact, index) => (
                        <div key={index}>
                            <p onClick={() => handleContactClick(index)}>{contact.name}</p>
                            {activeContact === index && (
                                <div className='contact-menu-dropdown'>
                                    {contact.emails.map((email, emailIndex) => (
                                        <p key={emailIndex}>{email}</p>
                                    ))}
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default ContactMenu;
