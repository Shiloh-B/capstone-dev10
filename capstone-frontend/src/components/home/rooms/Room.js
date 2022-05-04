import React, { useState, useEffect } from 'react'

const Room = ({ roomName, isActive }) => {

  const [activeRoom, setActiveRoom] = useState(isActive);

  useEffect(() => {
    if(roomName === 'Main') {
      setActiveRoom(true);
    }
  }, [activeRoom]);

  const handleRoomChange = (e) => {
    // function that will eventually swap a room to active

    // setIsActive(true);
  }

  return (
    <div className={isActive ? 'single-room-container active-room' : 'single-room-container'} name={roomName} onClick={handleRoomChange}>
      <h1 className='single-room-name'>{roomName}</h1>
    </div>
  )
}

export default Room