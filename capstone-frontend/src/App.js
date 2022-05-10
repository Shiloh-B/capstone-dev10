import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState } from 'react';
import UserContext from './context/UserContext';
import SocketContext from './context/SocketContext';
import Home from './components/home/Home';
import NotFound from './components/utility/NotFound';
import Login from './components/login/Login';
import About from './components/about/About';

function App() {

  const [socket, setSocket] = useState(null);
  const [user, setUser] = useState({
    email: '',
    username: ''
  });

  return (
    <div className="App">
      <SocketContext.Provider value={[socket, setSocket]}>
        <UserContext.Provider value={[user, setUser]}>
          <Router>
            <Routes>
              <Route path="*" element={<NotFound />} />
              <Route path='/home' element={<Home />} />
              <Route path ='/auth' element={<Login />} />
              <Route path = '/about' element={<About />}/>
            </Routes>
          </Router>
        </UserContext.Provider>
      </SocketContext.Provider>
      
    </div>
  );
}

export default App;
