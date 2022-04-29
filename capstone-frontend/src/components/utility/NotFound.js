import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const NotFound = () => {

  const navigate = useNavigate();

  useEffect(() => {
    navigate('/auth');
  }, []);

  return (
    <div>
      <h1>Route not found.</h1>
    </div>
  )
}

export default NotFound