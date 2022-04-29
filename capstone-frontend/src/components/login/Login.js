import React, { useContext, useState } from 'react';
import SignInForm from './SignInForm';
import SignUpForm from './SignUpForm';
import UserContext from '../../context/UserContext';
import { useNavigate } from 'react-router-dom';


const Login = () => {

  const [user, setUser] = useContext(UserContext);
  const [isSignIn, setIsSignIn] = useState(true);

  const navigate = useNavigate();

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
    // testing purpose only, username set to email
    let newUser = {...user};
    newUser.username = newUser.email;
    setUser(newUser);
    console.log('sign in');

    // this will be hidden behind a success callback
    navigate('/home');
  }

  const handleSignUp = (e) => {
    e.preventDefault();
    if(user.password !== user.confirmPassword) {
      console.log('Passwords dont match.');
      return;
    }
    console.log('sign up');
  }

  return (
    <div className='login-container'>
      <div className='login-wrapper'>
        <h1 className='login-header'>chat.app</h1>
        {
          isSignIn ?
          <SignInForm handleChange={handleChange} swapView={swapView} handleSignIn={handleSignIn} /> :
          <SignUpForm handleChange={handleChange} swapView={swapView} handleSignUp={handleSignUp} />
        }
      </div>
    </div>
  )
}

export default Login