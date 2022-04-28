import React, { useContext } from 'react'
import UserContext from '../../../context/UserContext';

const Message = ({ message }) => {

  const [user, setUser] = useContext(UserContext);

  return (
    <div className={user.username === message.username ? 'single-message-container user-message' : 'single-message-container server-message'}>
      <h1 className='single-message'>{message.username}: {message.message}</h1>
    </div>
  )
}

export default Message