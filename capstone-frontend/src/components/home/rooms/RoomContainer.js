import React, { useState } from 'react'
import Room from './Room';
import JoinRoom from './JoinRoom';

const RoomContainer = () => {

  const [rooms, setRooms] = useState(['Main']);
  const [roomToJoin, setRoomToJoin] = useState('');
  const [isActive, setIsActive] = useState(true);

  const roomList = rooms.map((room, key) => <Room roomName={room} key={key} isActive={false} setIsActive={setIsActive} />);

  const handleChange = (e) => {
    setRoomToJoin(e.target.value);
  }

  const handleChangeActive = (e) => {
    
  }

  const handleJoinRoom = (e) => {
    e.preventDefault();
    // socket stuff here

    let newRooms = [...rooms];
    newRooms.push(roomToJoin);
    setRooms(newRooms);

    setRoomToJoin('');
  }

  return (
    <div className='room-container'>
      <div className='room-list-container'>
        {roomList}
      </div>
      <JoinRoom handleChange={handleChange} handleJoinRoom={handleJoinRoom} roomToJoin={roomToJoin} />
    </div>
    
  )
}

export default RoomContainer