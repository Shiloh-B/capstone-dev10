import React, { useState, useEffect } from 'react'
import Room from './Room';
import JoinRoom from './JoinRoom';

const RoomContainer = ({ setCurrentRoom }) => {

  const [roomToJoin, setRoomToJoin] = useState('');
  const [roomList, setRoomList] = useState([]);
  const [rooms, setRooms] = useState([{
    roomId: 1,
    name: "Main",
    isActive: true
  }]);

  // need to wait for the function to be initialized prior to passing it as a prop
  useEffect(() => {
    setRoomList(rooms.map((room, key) => <Room room={room} key={key} setCurrentRoom={setCurrentRoom} handleChangeActiveRoom={handleChangeActiveRoom} />));
  }, [roomList, rooms]);

  

  const handleChange = (e) => {
    setRoomToJoin(e.target.value);
  }

  const handleJoinRoom = (e) => {
    e.preventDefault();
    // socket stuff

    // don't join empty room
    if(roomToJoin.trim() == '') return;

    for(let i = 0; i < rooms.length; i++) {
      if(rooms[i].name.toLocaleLowerCase() == roomToJoin.toLocaleLowerCase()) {
        setRoomToJoin('');
        // you already joined this room error
        return;
      }
    }

    let newRooms = [...rooms];
    newRooms.push({roomId: 0, name: roomToJoin, isActive: false});
    setRooms(newRooms);

    setRoomToJoin('');
  }

  const handleChangeActiveRoom = (roomName) => {

    console.log(roomName);

    let newRooms = [...rooms];

    for(let i = 0; i < newRooms.length; i++) {
      if(newRooms[i].name == roomName) {
        newRooms[i].isActive = true;
        setCurrentRoom(rooms[i]);
      } else {
        newRooms[i].isActive = false;
      }
    }

    console.log(newRooms);

    setRooms(newRooms);
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