import React, { useState } from 'react'
import Message from './Message';

const MessageContainer = () => {

  // TODO: WILL DELETE ONCE DB IS SET UP
  const testData = [{
    username: "Shiloh",
    message: "Hello World!"
  }];

  const [messages, setMessages] = useState(testData);

  const logs = messages.map((message, key) => {<Message message={message} key={key} />})
  return (
    <div className='message-container'>
      {logs}
    </div>
  )
}

export default MessageContainer