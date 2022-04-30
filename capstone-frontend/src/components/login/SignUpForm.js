import React from 'react'
import { Button, TextField } from '@mui/material';

const SignUpForm = ({ handleChange, swapView, handleSignUp, signUpError }) => {
  return (
    <div className='sign-in-container'>
      <h1>Sign Up For An Account</h1>
      <form className='sign-in-form' onSubmit={handleSignUp}>
        <div className='login-fields'>
          <TextField required name='username' label='Username' type='text' variant='standard' className='login-input-field' onChange={handleChange} />
        </div>
        <div className='login-fields'>
          <TextField required name='password' label='Password' type='password' variant='standard' className='login-input-field'  onChange={handleChange} />
        </div>
        <div className='login-fields'>
          <TextField required name='confirmPassword' label='Confirm Password' type='password' variant='standard' className='login-input-field'  onChange={handleChange} />
          {
            signUpError !== '' ?
            <h1 className='login-error'>{signUpError}</h1> :
            <></>
          }
        </div>
        <Button variant='contained' className='sign-in-button' type='submit'>Sign Up</Button>
        <h1 className='swap-sign-view' onClick={swapView}>Already have an account? Sign in here.</h1>
      </form>
    </div>
  )
}

export default SignUpForm