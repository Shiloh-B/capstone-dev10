import './darkmode.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React, { useState } from 'react';
import UserContext from './context/UserContext';
import SocketContext from './context/SocketContext';
import Home from './components/home/Home';
import NotFound from './components/utility/NotFound';
import Login from './components/login/Login';
import About from './components/about/About';
import { createTheme, ThemeProvider } from '@mui/material/styles';


function App() {

  const darkMode = createTheme({
    palette: {
      type: 'dark',
      primary: {
        main: '#202022',
        light: '#3a3a3f',
        dark: '#121213',
      },
      secondary: {
        main: '#f50057',
      },
    },
  });

  const [socket, setSocket] = useState(null);
  const [user, setUser] = useState({
    email: '',
    username: ''
  });

  return (
    <div className="App">
      <SocketContext.Provider value={[socket, setSocket]}>
        <UserContext.Provider value={[user, setUser]}>
        <ThemeProvider theme={darkMode}>
          <Router>
            <Routes>
              <Route path="*" element={<NotFound />} />
              <Route path='/home' element={<Home />} />
              <Route path ='/auth' element={<Login />} />
              <Route path = '/about' element={<About />}/>
            </Routes>
          </Router>
          </ThemeProvider>
        </UserContext.Provider>
      </SocketContext.Provider>
      
      
    </div>
  );
}

export default App;
