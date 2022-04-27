import { TextField, Button } from '@mui/material'
import React, { useState } from 'react'

const ChatContainer = () => {

  const [message, setMessage] = useState('');

  const handleMessageChange = (e) => {
    setMessage(e.target.value);
  }

  const submitMessage = (e) => {
    e.preventDefault();
    console.log(message);
  }

  return (
    <div className='chat-container'>
      <div className='message-container'>
      </div>
      <form className='message-form' onSubmit={submitMessage}>
        <TextField label='Say Hello!' variant='outlined' className='message-input' name='message' onChange={handleMessageChange} />
        <Button variant='contained' className='submit-message-button' type='submit'>Send</Button>
      </form>
    </div>
  )
}

export default ChatContainer