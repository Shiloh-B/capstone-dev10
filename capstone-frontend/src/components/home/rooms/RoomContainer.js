import React, { useState } from 'react'
import Room from './Room';
import JoinRoom from './JoinRoom';

const RoomContainer = ({ setCurrentRoom }) => {

  const [roomToJoin, setRoomToJoin] = useState('');
  const [rooms, setRooms] = useState([{
    roomId: 1,
    name: "Main"
  }]);

  const roomList = rooms.map((room, key) => <Room room={room} key={key} setCurrentRoom={setCurrentRoom} />);

  const handleChange = (e) => {
    setRoomToJoin(e.target.value);
  }

  const handleJoinRoom = (e) => {
    e.preventDefault();
    // socket stuff

    for(let i = 0; i < rooms.length; i++) {
      if(rooms[i].name.toLocaleLowerCase() == roomToJoin.toLocaleLowerCase()) {
        setRoomToJoin('');
        return;
      }
    }

    let newRooms = [...rooms];
    newRooms.push({roomId: 0, name: roomToJoin});
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