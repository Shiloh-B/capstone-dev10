import React, { useContext } from 'react'
import UserContext from '../../../context/UserContext';

const Message = ({ message }) => {

  const [user, setUser] = useContext(UserContext);

  return (
    <div className={user.username === message.username ? 'single-message-container user-message' : 'single-message-container server-message'}>
      {
        message.username ? 
        <h1 className='single-message'>{message.username}: {message.messageContent}</h1> :
        <h1 className='single-message'>{message.messageContent}</h1>
      }
      
    </div>
  )
}

export default Message