function UploadedFilesOpenEmail({file}){
    return(   
    <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '10px' }}>
      {console.log(file.fileName)}
      <button style={{ padding: '5px', cursor: 'pointer' }}>
        {file.fileName}
      </button>
    </div>
    );
}
export default UploadedFilesOpenEmail;