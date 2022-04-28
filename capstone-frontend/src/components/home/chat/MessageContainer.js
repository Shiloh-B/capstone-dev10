import React, { useState } from 'react'
import Message from './Message';

const MessageContainer = ({ messages }) => {

  const logs = messages.map((message, key) => <Message message={message} key={key} /> );
  
  return (
    <div className='message-container'>
      {logs}
    </div>
  )
}

export default MessageContainer