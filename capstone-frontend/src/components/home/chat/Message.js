import jwtDecode from 'jwt-decode';
import React, { useContext, useState } from 'react'
import UserContext from '../../../context/UserContext';
import { Button } from '@mui/material';

const Message = ({ message }) => {

  const [user, setUser] = useContext(UserContext);
  const [deleting, setDeleting] = useState(false);

  const handleDelete = () => {
    setDeleting(true);
  }

  const handleCloseModal = () => {
    setDeleting(false);
  }

  const handleConfirmDelete = () => {

  }

  const findMessage = () => {
    
  }

  return (
    <div className={user.username === message.username ? 'single-message-container user-message' : 'single-message-container server-message'}>
      {
        jwtDecode(localStorage.getItem('token')).authorities == 'ROLE_ADMIN' ?
        <div className='delete-message-button' onClick={handleDelete}>&times;</div> :
        <></>
      }
      {
        message.username ? 
        <h1 className='single-message'>{message.username}: {message.messageContent}</h1> :
        <h1 className='single-message'>{message.messageContent}</h1>
      }
      {
        deleting ?
        <div className='delete-modal'>
          <div className='delete-modal-content'>
            <span className='close' onClick={handleCloseModal}>&times;</span>
            <h1>Are you sure you want to delete message "{message.messageContent}"?</h1>
            <Button variant='contained' color='error'>Delete</Button>
          </div>
        </div> :
        <></>
      }
      
    </div>
  )
}

export default Message