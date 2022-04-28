import { TextField, Button } from '@mui/material'
import React, { useState, useContext } from 'react';
import UserContext from '../../../context/UserContext';
import MessageContainer from './MessageContainer';

const ChatContainer = () => {

  const [user, setUser] = useContext(UserContext);
  const [message, setMessage] = useState('');

  // TODO: WILL DELETE ONCE DB IS SET UP
  const testData = [{
    username: "Becky",
    message: "Hello World!"
  }];

  const [messages, setMessages] = useState(testData);

  const handleMessageChange = (e) => {
    setMessage(e.target.value);
  }

  const submitMessage = (e) => {
    e.preventDefault();
    let newMessages = [...messages];
    newMessages.push({ username: user.username, message: message})
    setMessages(newMessages);
  }

  return (
    <div className='chat-container'>
      <MessageContainer messages={messages} />
      <form className='message-form' autoComplete='off' onSubmit={submitMessage}>
        <TextField label='Say Hello!' variant='outlined' className='message-input' name='message' autoComplete='false' onChange={handleMessageChange} />
        <Button variant='contained' className='submit-message-button' type='submit'>Send</Button>
      </form>
    </div>
  )
}

export default ChatContainer