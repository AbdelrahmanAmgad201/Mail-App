import axios from 'axios';
function UploadedFilesOpenEmail({file}){

  const downloadFile = async (fileName) => {
    const url2 = `http://localhost:8080/download?fileName=${fileName}`
    try {
        console.log(url2)
        const response = await axios.get(url2, {
            responseType: 'blob', // Important for handling binary data
        });

        // Create a temporary link to download the file
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', fileName); // Specify the file name
        document.body.appendChild(link);
        link.click();

        // Clean up the temporary link
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    } catch (error) {
        console.error('Error downloading the file:', error);
    }
};

  return(   
  <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '10px' }}>
    <button style={{ padding: '5px', cursor: 'pointer' }} onClick={async ()=>{
      downloadFile(file.fileName)
    }}>
      {file.fileName}
    </button>
  </div>
  );
}
export default UploadedFilesOpenEmail;