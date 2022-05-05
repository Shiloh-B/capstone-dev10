import React, { useState } from 'react'
import Room from './Room';

const RoomContainer = ({ setCurrentRoom }) => {

  const [rooms, setRooms] = useState([{
    roomId: 1,
    name: "Main"
  }]);

  const roomList = rooms.map((room, key) => <Room room={room} key={key} setCurrentRoom={setCurrentRoom} />);

  return (
    <div className='room-container'>
      {roomList}
    </div>
  )
}

export default RoomContainer