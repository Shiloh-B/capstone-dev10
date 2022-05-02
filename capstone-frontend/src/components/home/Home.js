import React, { useEffect, useContext } from 'react'
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
  const navigate = useNavigate();

  useEffect(() => {

    if(!localStorage.getItem("token")) {
      navigate('/auth');
      return;
    }

    setUser({username: jwtDecode(localStorage.getItem("token")).sub});

    let s = io('http://localhost:3003');
    setSocket(s);
  }, []);
  return (
    <div className='landing-container'>
      <Header />
      <div className='room-chat-container'>
        <RoomContainer />
        <ChatContainer />
      </div>
    </div>
  )
}

export default Home