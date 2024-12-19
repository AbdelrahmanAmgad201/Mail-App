import { useEffect,useState} from "react"
import "./SearchFilter.css"


function SearchFilter(props){
    const [subjects, setSubjects] = useState([]);
    const [newSubject, setNewSubject] = useState("");
    const [receivers, setReceivers] = useState([])
    const [newReceiver, setNewReceiver] = useState("")
    const [senders, setSenders] = useState([])
    const [newSender, setNewSender] = useState("")

    const handleAddSubject = async () => {
        if (newSubject.trim()) {
            const updatedSubjects = [...subjects, newSubject];
            await setSubjects(updatedSubjects);
            props.filters.current.subjects = updatedSubjects;
            setNewSubject("");
        }
    };
    
    const handleRemoveSubject = (index) => {
        const updatedSubjects = subjects.filter((_, i) => i !== index);
        setSubjects(updatedSubjects);
        props.filters.current.subjects = updatedSubjects;
    };

    const handleAddReceiver = () => {
        if (newReceiver.trim()) {
            const updatedReceivers = [...receivers, newReceiver];
            setReceivers(updatedReceivers);
            props.filters.current.receivers = updatedReceivers; // Update filter object here
            setNewReceiver("");
        }
    };
    
    const handleRemoveReceiver = (index) => {
        const updatedReceivers = receivers.filter((_, i) => i !== index);
        setReceivers(updatedReceivers);
        props.filters.current.receivers = updatedReceivers; // Update filter object here
    };
    
    const handleAddSender = () => {
        if (newSender.trim()) {
            const updatedSenders = [...senders, newSender];
            setSenders(updatedSenders);
            props.filters.current.senders = updatedSenders; // Update filter object here
            setNewSender("");
        }
    };
    
    const handleRemoveSender = (index) => {
        const updatedSenders = senders.filter((_, i) => i !== index);
        setSenders(updatedSenders);
        props.filters.current.senders = updatedSenders; // Update filter object here
    };
    

    useEffect(() => {
        props.filters.current = {
            ...props.filters.current,
            subjects: [],
            senders: [],
            startDate: "",
            endDate: "",
            receivers: [],
            priority: 'LOW',
            body: "",
            attachment: ""
          }
        return () => {
            
        }
    }, []);

    return (
        
        <div className="popup-body-filter">
            {<div className="filter-content">

                <div className="choice">
                    <p>start date</p>
                    <input type="date" onChange={(e)=>{
                        props.filters.current.startDate = e.target.value+"T00:00:00"
                    }}/>
                </div>

                <div className="choice">
                    <p>end date</p>
                    <input type="date" onChange={(e)=>{
                        props.filters.current.endDate = e.target.value+"T00:00:00"
                    }}/>
                </div>

                <div className="input-field">
                    <p className="input-field-title">subject</p>
                    <div className="input-choosed">
                    {subjects.map((subject, index) => (
                            <div key={index} >
                                <p>{subject}</p>
                                <button
                                    className="remove-button"
                                    onClick={() => {
                                        handleRemoveSubject(index)
                                    }}
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
                    <button className="add-button"  onClick={async ()=>{
                        await handleAddSubject()
                    }}><img src="src/app-assets/appIcons/plus.png"/></button>
                </div>

                <div className="input-field">
                    <p className="input-field-title">receivers</p>
                    <div className="input-choosed">
                    {receivers.map((receiver, index) => (
                            <div key={index} >
                                <p>{receiver}</p>
                                <button
                                    className="remove-button"
                                    onClick={() => {
                                        handleRemoveReceiver(index)
                                    }}
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
                            onChange={(e) => {
                                setNewReceiver(e.target.value)
                            }}
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
                                    onClick={() => {
                                        handleRemoveSender(index)
                                        props.filters.current.senders = senders
                                    }}
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
                            onChange={(e) => {
                                setNewSender(e.target.value)
                                props.filters.current.senders = senders
                            }}
                            placeholder="add sender"
                        />
                    </div>
                    <button className="add-button" onClick={handleAddSender}><img src="src/app-assets/appIcons/plus.png"/></button>
                </div>

                <div className="choice">
                    <p>priority</p>
                    <select id="priority" name="priority" onChange={(e) => {
                        props.filters.current.priority = e.target.value
                    }}>
                        <option value="LOW">LOW</option>
                        <option value="MID">MID</option>
                        <option value="HIGH">HIGH</option>
                    </select>
                </div>

                <div className="choice">
                    <p>body</p>
                    <input type="text" onChange={(e)=>{
                        props.filters.current.body = e.target.value
                    }}/>
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