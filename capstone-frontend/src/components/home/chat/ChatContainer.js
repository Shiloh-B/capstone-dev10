import { TextField, Button } from '@mui/material'
import React, { useState, useContext, useEffect } from 'react';
import UserContext from '../../../context/UserContext';
import SocketContext from '../../../context/SocketContext';
import MessageContainer from './MessageContainer';

const ChatContainer = ({ currentRoom }) => {

  const [user, setUser] = useContext(UserContext);
  const [socket, setSocket] = useContext(SocketContext);
  const [message, setMessage] = useState('');

  const [messages, setMessages] = useState([]);

  // populate messages
  useEffect(() => {
    fetch(`http://localhost:8080/message/room/${currentRoom.roomId}`, {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }).then((res) => {
      if(res.status != 200) {
        return null;
      }
      return res.json();
    }).then((data) => {
      if(data) {
        setMessages(data);
      } else {
        setMessages([]);
      }
    })
  }, []);

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

    // check if the message is empty, if it is just return
    if(message === '' || !message) return;


    let newMessages = [...messages];
    

    // emit broadcast
    socket.emit('chat message', {username: user.username, messageContent: message});

    const messageToPost = {
        messageId: 0,
        messageContent: message,
        roomId: currentRoom.roomId,
        userId: user.userId,
        username: user.username
    }

      newMessages.push(messageToPost);
      setMessages(newMessages);
      setMessage('');

    // store the message
    fetch('http://localhost:8080/message', {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(messageToPost)
    }).then((res) => {
      if(res.status !== 201) {
        // alert message here that the message didn't send
        return null;
      }
      return;
    });
  }

  const scrollContainer = (ref) => {
    ref.current.scrollIntoView({ behavior: "smooth" });
  }

  return (
    <div className='chat-container'>
      <MessageContainer messages={messages} scrollContainer={scrollContainer} />
      <form className='message-form' autoComplete='off' onSubmit={submitMessage}>
        <TextField label='Say Hello!' variant='outlined' className='message-input' name='message' value={message} aria-autocomplete='false' onChange={handleMessageChange} />
        <Button variant='contained' className='submit-message-button' type='submit'>Send</Button>
      </form>
    </div>
  )
}

export default ChatContainer