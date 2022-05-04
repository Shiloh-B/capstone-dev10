import React from 'react'
import { Button } from '@mui/material';

const JoinRoom = ({ handleChange, handleJoinRoom, roomToJoin }) => {
  return (
    <div className='room-input-container'>
      <form className='room-form' onSubmit={handleJoinRoom}>
        <input className='room-input' onChange={handleChange} value={roomToJoin} />
        <Button variant='contained' type='submit'>Join Room</Button>
      </form>
    </div>
  )
}

export default JoinRoom