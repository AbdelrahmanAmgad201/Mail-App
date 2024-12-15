import { useEffect,useState} from "react"
import "./SearchFilter.css"


function SearchFilter(){
    const [subjects, setSubjects] = useState([]);
    const [newSubject, setNewSubject] = useState("");
    const [receivers, setReceivers] = useState([])
    const [newReceiver, setNewReceiver] = useState("")
    const [senders, setSenders] = useState([])
    const [newSender, setNewSender] = useState("")

    const handleAddSubject = () => {
        if (newSubject.trim()) {
            setSubjects([...subjects, newSubject]);
            setNewSubject("");
        }
    };

    const handleRemoveSubject = (index) => {
        setSubjects(subjects.filter((_, i) => i !== index));
    };

    const handleAddReceiver = () => {
        if (newReceiver.trim()) {
            setReceivers([...receivers, newReceiver]);
            setNewSubject("");
        }
    };

    const handleRemoveReceiver = (index) => {
        setReceivers(receivers.filter((_, i) => i !== index));
    };

    const handleAddSender = () => {
        if (newSender.trim()) {
            setSenders([...senders, newSender]);
            setNewSender("");
        }
    };

    const handleRemoveSender = (index) => {
        setSenders(senders.filter((_, i) => i !== index));
    };
    

    useEffect(() => {
        return () => {
            
        }
    }, []);

    return (
        
        <div className="popup-body-filter">
            {<div className="filter-content">

                <div className="choice">
                    <p>Date</p>
                    <input type="text"/>
                </div>
                

                <div className="input-field">
                    <p className="input-field-title">subject</p>
                    <div className="input-choosed">
                    {subjects.map((subject, index) => (
                            <div key={index} >
                                <p>{subject}</p>
                                <button
                                    className="remove-button"
                                    onClick={() => handleRemoveSubject(index)}
                                >
                                    <img src="src/app-assets/appIcons/minus.png" alt="Remove" />
                                </button>
                            </div>
                        ))}
                    </div>
                    <div>
                    <input
                            className="add-new-input"
                            type="text"
                            value={newSubject}
                            onChange={(e) => setNewSubject(e.target.value)}
                            placeholder="add subject"
                        />                    </div>
                    <button className="add-button"  onClick={handleAddSubject}><img src="src/app-assets/appIcons/plus.png"/></button>
                </div>


                <div className="input-field">
                    <p className="input-field-title">receivers</p>
                    <div className="input-choosed">
                    {receivers.map((receiver, index) => (
                            <div key={index} >
                                <p>{receiver}</p>
                                <button
                                    className="remove-button"
                                    onClick={() => handleRemoveReceiver(index)}
                                >
                                    <img src="src/app-assets/appIcons/minus.png" alt="Remove" />
                                </button>
                            </div>
                        ))}
                    </div>
                    <div>
                    <input
                            className="add-new-input"
                            type="text"
                            value={newReceiver}
                            onChange={(e) => setNewReceiver(e.target.value)}
                            placeholder="add receiver"
                        />
                    </div>
                    <button className="add-button" onClick={handleAddReceiver}><img src="src/app-assets/appIcons/plus.png"/></button>

                </div>

                <div className="input-field">
                    <p className="input-field-title">senders</p>
                    <div className="input-choosed">
                    {senders.map((sender, index) => (
                            <div key={index} >
                                <p>{sender}</p>
                                <button
                                    className="remove-button"
                                    onClick={() => handleRemoveSender(index)}
                                >
                                    <img src="src/app-assets/appIcons/minus.png" alt="Remove" />
                                </button>
                            </div>
                        ))}
                    </div>
                    <div>
                    <input
                            className="add-new-input"
                            type="text"
                            value={newSender}
                            onChange={(e) => setNewSender(e.target.value)}
                            placeholder="add sender"
                        />
                    </div>
                    <button className="add-button" onClick={handleAddSender}><img src="src/app-assets/appIcons/plus.png"/></button>
                </div>

                <div className="choice">
                    <p>priority</p>
                    <input type="text"/>
                </div>

                <div className="choice">
                    <p>body</p>
                    <input type="text"/>
                </div>

                <div className="choice">
                    <p>attachment</p>
                    <input type="text"/>
                </div>


            </div>}
        </div>
    )
}

export default SearchFilter