import React, { useState } from 'react'
import Room from './Room';

const RoomContainer = () => {

  const [rooms, setRooms] = useState(['Main']);

  const roomList = rooms.map((room, key) => <Room roomName={room} key={key} />);

  return (
    <div className='room-container'>
      {roomList}
    </div>
  )
}

export default RoomContainer