import React, { useContext, useState, useEffect } from 'react';
import SignInForm from './SignInForm';
import SignUpForm from './SignUpForm';
import UserContext from '../../context/UserContext';
import { useNavigate } from 'react-router-dom';
import jwtDecode from 'jwt-decode';

const Login = () => {

  const [user, setUser] = useContext(UserContext);
  const [errors, setErrors] = useState('');
  const [isSignIn, setIsSignIn] = useState(true);

  const navigate = useNavigate();

  // make sure we aren't already logged in and if the token is expired
  useEffect(() => {
    if(!localStorage.getItem("token")) return;

    let dToken = jwtDecode(localStorage.getItem("token"));

    if(dToken.exp > Date.now()) {
      localStorage.removeItem("token");
      return;
    }

    setUser({
      username: dToken.sub
    });

    navigate('/home');
  }, []);

  const handleChange = (e) => {
    let newUser = {...user};
    newUser[e.target.name] = e.target.value;
    setUser(newUser);
  }

  const swapView = () => {
    setIsSignIn(!isSignIn);
  }

  const handleSignIn = (e) => {
    e.preventDefault();
    
    // call auth
    fetch(`${window.API_URL}/authenticate`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: user.username,
        password: user.password
      })

    }).then((res) => {
      setErrors('');

      if(res.status === 200) {
        return res.json();
      }
      return res.status;
    }).then((data) => {
      if(data.jwt_token) {
        localStorage.setItem("token", data.jwt_token);
        getUserDetails();
        // setUser({username: user.username});
        navigate('/home');
      } else {
        setErrors('Wrong username or password.');
      }
    });
  }

  const getUserDetails = () => {
    fetch(`${window.API_URL}/user/${user.username}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    }).then((res) => {
      if(res.status != 200) {
        return;
      }

      return res.json();
    }).then((data) => {
      if(data) {
        setUser({
          username: data.username,
          userId: data.appUserId
        });
      }
    });
  }

  const handleSignUp = (e) => {
    e.preventDefault();
    setErrors('');

    if(user.password !== user.confirmPassword) {
      setErrors('Passwords dont match.');
      return;
    }
    
    fetch(`${window.API_URL}/create_account`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: user.username,
        password: user.password
      })
    }).then((res) => {
      if(res.status === 201) {
        return res.json();
      } else {
        return res.status;
      }
    }).then((data) => {
      if(data.userId) {
        handleSignIn(e);
        if(errors !== '') {
          setErrors('Oops. Something went wrong.');
        }
      } else {
        setErrors('Username already exists.');
      }
    });
  }

  return (
    <div className='login-container'>
      <div className='login-wrapper'>
        <h1 className='login-header'>chat.app</h1>
        {
          isSignIn ?
          <SignInForm handleChange={handleChange} swapView={swapView} handleSignIn={handleSignIn} loginError={errors} /> :
          <SignUpForm handleChange={handleChange} swapView={swapView} handleSignUp={handleSignUp} signUpError={errors} />
        }
      </div>
    </div>
  )
}

export default Login