import React, { useEffect, useContext, useState } from 'react'
import { io } from 'socket.io-client';
import SocketContext from '../../context/SocketContext';
import UserContext from '../../context/UserContext';
import Header from './Header';
import RoomContainer from './rooms/RoomContainer';
import ChatContainer from './chat/ChatContainer';
import { useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';

const Home = () => {

  const [socket, setSocket] = useContext(SocketContext);
  const [user, setUser] = useContext(UserContext);
  const [currentRoom, setCurrentRoom] = useState({
    roomId: 1,
    name: "Main"
  });
  const navigate = useNavigate();

  useEffect(() => {

    if(!localStorage.getItem("token")) {
      navigate('/auth');
      return;
    }

    setUser({username: jwtDecode(localStorage.getItem("token")).sub});

    let s = io('http://localhost:3003');
    setSocket(s);

    getUserDetails(jwtDecode(localStorage.getItem("token")).sub);

  }, []);

  const getUserDetails = (username) => {
    fetch(`http://localhost:8080/user/${username}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }).then((res) => {
      if(res.status != 200) {
        return;
      }

      return res.json();
    }).then((data) => {
      if(data) {
        setUser({
          username: data.username,
          userId: data.appUserId
        });
      }
    });
  }

  return (
    <div className='landing-container'>
      <Header />
      <div className='room-chat-container'>
        <RoomContainer setCurrentRoom={setCurrentRoom} />
        <ChatContainer currentRoom={currentRoom} />
      </div>
    </div>
  )
}

export default Home