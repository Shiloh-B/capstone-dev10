import { TextField, Button } from '@mui/material'
import React, { useState, useContext, useEffect } from 'react';
import UserContext from '../../../context/UserContext';
import SocketContext from '../../../context/SocketContext';
import MessageContainer from './MessageContainer';
import jwtDecode from 'jwt-decode';
import { io } from 'socket.io-client';

const ChatContainer = ({ currentRoom, getUserDetails }) => {

  const [user, setUser] = useContext(UserContext);
  const [socket, setSocket] = useContext(SocketContext);
  const [message, setMessage] = useState('');

  const [messages, setMessages] = useState([]);

  useEffect(() => {

    let s = io(`${window.SOCKET_URL}`, { auth: { token: localStorage.getItem('token') } });
    setSocket(s);
    getUserDetails(jwtDecode(localStorage.getItem("token")).sub);

    fetch(`${window.API_URL}/message/room/${currentRoom.roomId}`, {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }).then((res) => {
      if (res.status != 200) {
        return null;
      }
      return res.json();
    }).then((data) => {
      if (data) {
        setMessages(data);
      } else {
        setMessages([]);
      }
    }).then(() => {
      s.emit('chat message', { messageContent: `User ${jwtDecode(localStorage.getItem("token")).sub} has joined.` });
    })
  }, []);

  useEffect(() => {
    // make sure the socket and messages are populated
    if (!socket || messages.length === 0) return;
    socket.on('chat message', (msg) => {

      // make sure we don't keep displaying the same user joining

      let newMessages = [...messages];
      newMessages.push(msg);
      setMessages(newMessages);

    });
  }, [messages, socket])


  const handleMessageChange = (e) => {
    setMessage(e.target.value);
  }

  const submitMessage = (e) => {
    e.preventDefault();

    // check if the message is empty, if it is just return
    if (message.trim() === '' || !message) return;


    let newMessages = [...messages];


    // emit broadcast
    socket.emit('chat message', { username: user.username, messageContent: message });

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
    fetch(`${window.API_URL}/message`, {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(messageToPost)
    }).then((res) => {
      if (res.status !== 201) {
        // alert message here that the message didn't send
        return null;
      }
      return;
    });
  }

  const scrollContainer = (ref) => {
    ref.current.scrollIntoView({ behavior: "smooth" });
  }

  if (socket) {
    return (
      <div className='chat-container'>
        <MessageContainer messages={messages} scrollContainer={scrollContainer} />
        <form className='message-form' autoComplete='off' onSubmit={submitMessage}>
          <TextField label='Say Hello!' variant='outlined' className='message-input' name='message' value={message} aria-autocomplete='false' onChange={handleMessageChange} />
          <Button variant='contained' className='submit-message-button' type='submit'>Send</Button>
        </form>
      </div>
    )
  } else {
    return <></>;
  }

}

export default ChatContainer