import jwtDecode from 'jwt-decode';
import React, { useEffect, useRef } from 'react'
import Message from './Message';

const MessageContainer = ({ messages, scrollContainer, setMessages }) => {

  const logs = messages.map((message, key) => <Message message={message} key={key} /> );
  const messageContainerRef = useRef(null);

  useEffect(() => {
    scrollContainer(messageContainerRef);
  }, [messages]);
  
  return (
    <div className='message-container'>
      {logs}
      <div ref={messageContainerRef} />
    </div>
  )
}

export default MessageContainer