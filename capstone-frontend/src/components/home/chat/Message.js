import jwtDecode from 'jwt-decode';
import React, { useContext, useState } from 'react'
import UserContext from '../../../context/UserContext';
import { Button } from '@mui/material';

const Message = ({ message }) => {

  const [user, setUser] = useContext(UserContext);
  const [deleting, setDeleting] = useState(false);
  const [deleted, setDeleted] = useState(false);

  const handleDelete = () => {
    setDeleting(true);
  }

  const handleCloseModal = () => {
    setDeleting(false);
  }

  const handleConfirmDelete = async () => {
    findMessage();
  }

  const findMessage = () => {
    if(!message.messageId || message.messageId == 0) {
      findMessageUsernameAndContent();
    } else {
      deleteMessageRequest(message.messageId);
    }
  }

  const findMessageUsernameAndContent = () => {
    fetch(`${window.API_URL}/message/find/${message.username}/${message.messageContent}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    }).then((res) => {
      if(res.status !== 200) return null;
      return res.json();
    }).then((data) => {
      if(data) {
        // delete message
        deleteMessageRequest(data.messageId);
      }
    })
  }

  const deleteMessageRequest = (messageId) => {
    fetch(`${window.API_URL}/message/${messageId}`, {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`
      }
    }).then((res) => {
      if(res.status === 204) {
        setDeleted(true);
        setDeleting(false);
      }
    });
  }

  return (
    <div className={user.username === message.username ? 'single-message-container user-message' : 'single-message-container server-message'}>
      {
        jwtDecode(localStorage.getItem('token')).authorities == 'ROLE_ADMIN' && message.username && !deleted ?
        <div className='delete-message-button' onClick={handleDelete}>&times;</div> :
        <></>
      }
      {
        message.username ? 
        <h1 className='single-message'>
          {
            deleted ?
            `Message Deleted.` :
            `${message.username}: ${message.messageContent}`
          }
        </h1> :
        <h1 className='single-message'>{message.messageContent}</h1>
      }
      {
        deleting ?
        <div className='delete-modal'>
          <div className='delete-modal-content'>
            <span className='close' onClick={handleCloseModal}>&times;</span>
            <h1>Are you sure you want to delete message "{message.messageContent}"?</h1>
            <Button variant='contained' color='error' onClick={handleConfirmDelete}>Delete</Button>
          </div>
        </div> :
        <></>
      }
      
    </div>
  )
}

export default Message