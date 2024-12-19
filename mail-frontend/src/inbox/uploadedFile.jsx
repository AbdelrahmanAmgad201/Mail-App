import fileIcon  from './pics/file-icon.png'
import videoIcon  from './pics/video-icon.png'
import soundIcon  from './pics/sound-icon.png'
import ImageIcon  from './pics/image.png'

function UploadedFiles({file,onRemove}){
    let Icon =null;
    
    if (file.type.startsWith('image/')) {
        Icon = ImageIcon;
    } else if (file.type.startsWith('video/')) {
        Icon = videoIcon;
    } else if (file.type.startsWith('audio/')) {
        Icon = soundIcon;
    } else {
        Icon = fileIcon;
    }
    return(   
    <div style={{ display: 'flex', alignItems: 'center', gap: '10px', marginBottom: '10px' }}>
      <img
        src={Icon}
        alt="Uploaded File"
        style={{
          width: '50px',
          height: '50px',
          objectFit: 'cover',
          border: '1px solid #ccc',
          borderRadius: '5px',
        }}
      />
      <span>{file.name}</span>
      {<button
        onClick={onRemove}
        style={{
            border: 'none',
            background: '#f44336',
            color: '#fff',
            padding: '5px 10px',
            borderRadius: '5px',
            cursor: 'pointer',
        }}
        >
        X
      </button>}
    
    </div>
    );
}
export default UploadedFiles;