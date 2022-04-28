import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState } from 'react';
import UserContext from './context/UserContext';
import Home from './components/home/Home';
import NotFound from './components/utility/NotFound';

function App() {

  const [user, setUser] = useState({
    email: 'shilohballards@gmail.com',
    username: 'Shiloh'
  });

  return (
    <div className="App">
      <UserContext.Provider value={[user, setUser]}>
        <Router>
          <Routes>
            <Route path="*" element={<NotFound />} />
            <Route path='/home' element={<Home />} />
          </Routes>
        </Router>
      </UserContext.Provider>
    </div>
  );
}

export default App;
