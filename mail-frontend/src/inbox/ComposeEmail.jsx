import { useState, useRef, useEffect } from 'react';
import './Inbox.css'
import UploadedFiles from './uploadedFile.jsx';


function ComposeEmail(props) {

    const recipientsEmails = useRef(null)
    const subject = useRef(null)
    const body = useRef(null)
    const priorityList = useRef(["LOW", "MID", "HIGH"])
    const priorityIndx = useRef(0)
    const [files, setFiles] = useState([]);

    const submitEmail = async () => {
        const fileNames = files.map((file) => file.name);
        const fileTypes = files.map((file) => file.type);
        const fileSizes = files.map((file) => file.size);
        const content = files.map((file) => file.base64);
        console.log(content)
        const data = {
            senderId: props.user.current.id,
            receiverEmails: recipientsEmails.current.value.split(';')
                                .map(word => word.trim())  // Trim spaces
                                .filter(word => word.length > 0),
            subject: subject.current.value,
            body: body.current.value,
            priority: priorityList.current[priorityIndx.current],
            fileNames,
            fileTypes,
            fileSizes,
            content,
        }
        
        const url = 'http://localhost:8080/api/emails/send'
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (response.ok) {
                const result = await response.json();
                console.log(result)
                console.log("email sent")
            } 
        } 
        catch (error) {
            console.error('Network error:', error);
        }
    }
    const handleFileChange = (event) => {
        const uploadedFiles = Array.from(event.target.files);
    
        uploadedFiles.forEach((file) => {
            const reader = new FileReader();
    
            reader.onloadend = () => {
                const base64Content = reader.result.split(',')[1]; // Extract base64 content from the data URL
    
                
                const fileData = {
                    file: file,
                    size: file.size,
                    name: file.name,
                    type: file.type,
                    base64: base64Content, // Store base64 content here
                };
    
                // Update state with the new file
                setFiles((prevFiles) => [...prevFiles, fileData]);
            };
            reader.readAsDataURL(file);
        });
    };
    
    
      const handleRemoveFile = (index) => {
        setFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
      };
    return (
    <div className='email-compose'>
       <div className='email-top-bar'>

        <p>New Message</p>
        <button><img src="src/inbox/pics/close.png" alt="Icon" /></button>

       </div>

       <div className='email-main'>

            <input ref={recipientsEmails} type='email' className='to' placeholder='Recipient email' />
            <input ref={subject} type='text' className='subject' placeholder='Email subject' />
            <textarea ref={body} className='body' placeholder='Write your email here...' rows='21' ></textarea>

       </div>

       <div className='email-bottom-bar'>
        <button onClick={async () => {
            submitEmail()
        }}><img src="src/inbox/pics/send-message.png" alt="Icon" /></button>
        <button onClick={() => document.getElementById('file-input').click()}
            style={{ border: 'none', background: 'none', padding: 0, cursor: 'pointer' }} 
        >
            <img src="src/inbox/pics/attach-document.png" alt="Icon" />
        </button>
        <button><img src="src/inbox/pics/image.png" alt="Icon" /></button>
        <input
            type="file"
            id="file-input"
            multiple
            style={{ display: 'none' }}
            onChange={handleFileChange}
        />
        <div>
            <h3>Uploaded Files:</h3>
                <ul>
                    {files.map((file, index) => (
                        <UploadedFiles key={index} file = {file}  onRemove={() =>handleRemoveFile(index)}/>
                    ))}
                </ul>
            </div>
       </div>

    </div>
    )
}

export default ComposeEmail;