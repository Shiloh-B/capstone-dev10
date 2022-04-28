import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { io } from 'socket.io-client';
import UserContext from './context/UserContext';
import SocketContext from './context/SocketContext';
import Home from './components/home/Home';
import NotFound from './components/utility/NotFound';

function App() {

  const [socket, setSocket] = useState(null);
  const [user, setUser] = useState({
    email: 'shilohballards@gmail.com',
    username: 'Shiloh'
  });

  useEffect(() => {
    let s = io('http://localhost:3003');
    setSocket(s);
  }, []);

  return (
    <div className="App">
      <SocketContext.Provider value={[socket, setSocket]}>
        <UserContext.Provider value={[user, setUser]}>
          <Router>
            <Routes>
              <Route path="*" element={<NotFound />} />
              <Route path='/home' element={<Home />} />
            </Routes>
          </Router>
        </UserContext.Provider>
      </SocketContext.Provider>
      
    </div>
  );
}

export default App;
