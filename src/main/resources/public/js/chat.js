$(() => {
   let stopClient = null;

   function updateConnectionStatus(isConnected) {
       $('#username').prop('disabled', isConnected);
       $('#connectBtn').prop('disabled', isConnected);
       $('#sendBtn').prop('disabled', !isConnected);
       $('#disconnectBtn').prop('disabled', !isConnected);
       if (isConnected) {
           $('#messages').innerHTML = '';
       }
   }

   function onMessage(message) {
       const body = JSON.parse(message.body)
       console.log(message);
       $(`<p>${body.sender}: ${body.text}</p>`).appendTo($('#messages'));
   }

   function connect() {
       const socket = new SockJS('/chat');
       stopClient = Stomp.over(socket);
       stopClient.connect({user:'jan'}, () => {
           updateConnectionStatus(true);
           stopClient.subscribe("/chat-topic/messages", onMessage)
       });
   }

   function send() {
       const message = JSON.stringify({
          sender: $('#username').val(),
          recipient: $('#recipient').val(),
          text: $('#message').val()
       });
       stopClient.send('/ws/chat', {}, message);
   }

   function disconnect() {
       stopClient.disconnect();
       updateConnectionStatus(false);
   }

   updateConnectionStatus(false);
   $('#connectBtn').click(connect);
   $('#sendBtn').click(send);
   $('#disconnectBtn').click(disconnect)
});