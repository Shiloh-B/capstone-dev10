import React from 'react'
import { Button, TextField } from '@mui/material';

const SignInForm = ({ handleChange, swapView, handleSignIn, loginError }) => {

  return (
    <div className='sign-in-container'>
      <h1>Sign In To Your Account</h1>
      <form className='sign-in-form' onSubmit={handleSignIn}>
        <div className='login-fields'>
          <TextField required name='username' label='Username' type='text' variant='standard' className='login-input-field' onChange={handleChange} />
        </div>
        <div className='login-fields'>
          <TextField required name='password' label='Password' type='password' variant='standard' className='login-input-field'  onChange={handleChange} />
          {
            loginError !== '' ?
            <h1 className='login-error'>{loginError}</h1> :
            <></>
          }
        </div>
        <Button variant='contained' className='sign-in-button' type='submit'>Sign In</Button>
        <h1 className='swap-sign-view' onClick={swapView}>Don't have an account? Sign up here.</h1>
      </form>
    </div>
  )
}

export default SignInForm