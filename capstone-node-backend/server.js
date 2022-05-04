// express server in here
const jwtDecode = require('jwt-decode');
const io = require('socket.io')(3003, {
  cors: {
    origin: '*'
  }
});

io.on('connection', (socket) => {

  socket.on('chat message', (msg) => {
    socket.broadcast.emit('chat message', msg);
  })
});

io.use((socket, next) => {
  if(socket.handshake.auth.token) {
    socket.username = getUsernameFromToken(socket.handshake.auth.token);
    next();
  } else {
    next(new Error('No token received.'));
  }
})

const getUsernameFromToken = (token) => {
  return jwtDecode(token).sub;
}