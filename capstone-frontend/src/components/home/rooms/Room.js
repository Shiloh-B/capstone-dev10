import React, { useState, useEffect } from 'react'
import RoomContainer from './RoomContainer';

const Room = ({ room, handleChangeActiveRoom }) => {

  return (
    <div className={room.isActive ? 'single-room-container active-room' : 'single-room-container'} name={room.roomName} onClick={() => handleChangeActiveRoom(room.roomName)}>
      <h1 className='single-room-name'>{room.roomName}</h1>
    </div>
  )
}

export default Room