import React from 'react'
import Header from './Header';
import RoomContainer from './RoomContainer';
import ChatContainer from './chat/ChatContainer';

const Home = () => {
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