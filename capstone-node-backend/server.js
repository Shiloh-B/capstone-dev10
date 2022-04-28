// express server in here

const io = require('socket.io')(3003, {
  cors: {
    origin: '*'
  }
});

io.on('connection', (socket) => {
  console.log(`user ${socket.id} has joined.`);

  socket.on('chat message', (msg) => {
    socket.broadcast.emit('chat message', msg);
    console.log(msg);
  })
});