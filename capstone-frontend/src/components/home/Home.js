import React, { useEffect, useContext } from 'react'
import { io } from 'socket.io-client';
import SocketContext from '../../context/SocketContext';
import Header from './Header';
import RoomContainer from './rooms/RoomContainer';
import ChatContainer from './chat/ChatContainer';

const Home = () => {

  const [socket, setSocket] = useContext(SocketContext);

  useEffect(() => {
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