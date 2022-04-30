import { TextField, Button } from '@mui/material'
import React, { useState, useContext, useEffect } from 'react';
import UserContext from '../../../context/UserContext';
import SocketContext from '../../../context/SocketContext';
import MessageContainer from './MessageContainer';

const ChatContainer = () => {

  const [user, setUser] = useContext(UserContext);
  const [socket, setSocket] = useContext(SocketContext);
  const [message, setMessage] = useState('');

  const [messages, setMessages] = useState([]);

  useEffect(() => {
    if(socket == null) return;

    socket.on('chat message', (msg) => {
      let newMessages = [...messages];
      newMessages.push(msg);
      setMessages(newMessages);
    });

  }, [socket, messages]);

  const handleMessageChange = (e) => {
    setMessage(e.target.value);
  }

  const submitMessage = (e) => {
    e.preventDefault();
    let newMessages = [...messages];
    newMessages.push({ username: user.username, message: message})
    setMessages(newMessages);
    setMessage('');

    // emit broadcast
    socket.emit('chat message', {username: user.username, message: message});
  }

  return (
    <div className='chat-container'>
      <MessageContainer messages={messages} />
      <form className='message-form' autoComplete='off' onSubmit={submitMessage}>
        <TextField label='Say Hello!' variant='outlined' className='message-input' name='message' value={message} aria-autocomplete='false' onChange={handleMessageChange} />
        <Button variant='contained' className='submit-message-button' type='submit'>Send</Button>
      </form>
    </div>
  )
}

export default ChatContainer