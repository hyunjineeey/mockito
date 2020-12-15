var socket;

function setup() {
    socket = new WebSocket(
    "ws://" +
      location.hostname +
      (location.port ? ":" + location.port : "") +
      "/lab11/lab11mockito",
    "web_server"
    );
    try {
      // handle command from server
      socket.onmessage = function got_packet(msg) {
        var data = JSON.parse(msg.data);
        var paragraph = document.getElementById("confirmation");
        paragraph.textContent = data.text;
      };
    } catch (exception) {
      alert("Error" + exception);
    }
}

function placeOrder() {
  socket.send(JSON.stringify({text: document.getElementById("order").value}));
}