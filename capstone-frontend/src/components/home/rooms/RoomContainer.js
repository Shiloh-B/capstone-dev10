import React, { useState, useEffect, useContext } from 'react'
import Room from './Room';
import JoinRoom from './JoinRoom';
import UserContext from '../../../context/UserContext';


const RoomContainer = ({ setCurrentRoom }) => {

  const [user, setUser] = useContext(UserContext);
  const [roomToJoin, setRoomToJoin] = useState('');
  const [roomList, setRoomList] = useState([]);
  const [rooms, setRooms] = useState([{
    roomId: 1,
    roomName: "Main",
    isActive: true
  }]);

  // need to wait for the function to be initialized prior to passing it as a prop
  useEffect(() => {
    setRoomList(rooms.map((room, key) => <Room room={room} key={key} setCurrentRoom={setCurrentRoom} handleChangeActiveRoom={handleChangeActiveRoom} />));
  }, [rooms]);

  useEffect(() => {
    if(!user.userId) return;

    // grab all rooms for the user
    fetch(`${window.API_URL}/room/user/${user.userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      if(res.status !== 200) {
        return null;
      }

      return res.json();
    }).then((data) => {
      data[0].isActive = true;
      for(let i = 1; i < data.length; i++) {
        data[i].isActive = false;
      }
      setRooms(data);
    });
  }, [user]);

  const addRoomHasUser = (roomId) => {
    fetch(`${window.API_URL}/room/roomhasuser/${roomId}/${user.userId}`, {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      console.log(res);
    });
  }

  const addRoom = () => {
    fetch(`${window.API_URL}/room`, {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        roomId: 0,
        roomName: roomToJoin
      })
    }).then((res) => {
      if(res.status !== 200) {
        return null;
      }
      return res.json();
    }).then((data) => {
      // access to room data here after creation, don't really know what I wanna do with this yet.
      // add the roomhasuser reference
      addRoomHasUser(data.roomId);
    });
  }

  const handleChange = (e) => {
    setRoomToJoin(e.target.value);
  }

  const handleJoinRoom = (e) => {
    e.preventDefault();

    // first thing is to check if the room already exists in db
    fetch(`${window.API_URL}/room/name/${roomToJoin}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        'Content-Type': 'application/json'
      }
    }).then((res) => {
      if(res.status === 404) {
        // lets add the room here, because it doesn't exist
        addRoom();
        return null;
      }
      return res.json();
    }).then((data) => {
      if(data) {
        // here we can decide to grab the rooms data/ add the room_has_user instance
        addRoomHasUser(data.roomId);
      }
    });

    // socket stuff

    // don't join empty room
    if(roomToJoin.trim() == '') return;

    for(let i = 0; i < rooms.length; i++) {
      if(rooms[i].roomName.toLocaleLowerCase() == roomToJoin.toLocaleLowerCase()) {
        setRoomToJoin('');
        // you already joined this room error
        return;
      }
    }

    let newRooms = [...rooms];
    newRooms.push({roomId: 0, roomName: roomToJoin, isActive: false});
    setRooms(newRooms);

    setRoomToJoin('');
  }

  const handleChangeActiveRoom = (roomName) => {

    console.log(roomName);

    let newRooms = [...rooms];

    for(let i = 0; i < newRooms.length; i++) {
      if(newRooms[i].roomName == roomName) {
        newRooms[i].isActive = true;
        setCurrentRoom(rooms[i]);
      } else {
        newRooms[i].isActive = false;
      }
    }

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