import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MenuIcon from '@mui/icons-material/Menu';
import { AppBar, Toolbar, Typography, IconButton, Menu, MenuItem } from '@mui/material'

const Header = () => {
  
  const [anchorEl, setAnchorEl] = useState(null);
  const navigate = useNavigate();

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogOut = () => {
    localStorage.removeItem("token");
    navigate('/auth');
  }
  const handleAbout = () =>{
    navigate('/about');
  }  
   const handleHome = () => {
    navigate('/home');

  }
  

  return (
    <AppBar position="static">
        <Toolbar>
          <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              aria-controls="burger-menu"
              sx={{ mr: 2 }}
              className='burger-menu-header'
          >
            <MenuIcon />
          </IconButton>
          <Menu
            id="burger-menu"
            anchorEl={anchorEl}
            anchorOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            keepMounted
            transformOrigin={{
              vertical: 'top',
              horizontal: 'right',
            }}
            open={Boolean(anchorEl)}
            onClose={handleClose}
          >
            <MenuItem onClick={() => console.log('handle')}>Rooms</MenuItem>
          </Menu>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <span 
            className='home-link'
            onClick={()=>navigate("/home")}>chat.app</span>
          </Typography>
          
          <div>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleMenu}
              color="inherit"
            >
              <AccountCircle />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorEl}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorEl)}
              onClose={handleClose}
            >
              <MenuItem onClick={handleLogOut}>Logout</MenuItem>   
              <MenuItem onClick={handleAbout}>About</MenuItem>
              <MenuItem onClick={handleHome}>Home</MenuItem>
            </Menu>
          </div>
          
        </Toolbar>
      </AppBar>
  )
}

export default Header