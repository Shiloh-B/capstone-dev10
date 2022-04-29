import React, { useContext, useState } from 'react';
import SignInForm from './SignInForm';
import SignUpForm from './SignUpForm';
import UserContext from '../../context/UserContext';


const Login = () => {

  const [user, setUser] = useContext(UserContext);
  const [isSignIn, setIsSignIn] = useState(true);

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
    console.log('sign in');
  }

  const handleSignUp = (e) => {
    e.preventDefault();
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