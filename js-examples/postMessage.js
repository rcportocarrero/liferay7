//Compartir datos entre Iframe y página padre

//Desde Iframe
parent.postMessage("Hola mundo","https://paginapadre.com");


//Desde Web padre
var eventMethod = window.addEventListener ? "addEventListener" : "attachEvent";
    var eventer = window[eventMethod];
    var messageEvent = eventMethod == "attachEvent" ? "onmessage" : "message";

    // Listen to message from child window
    eventer(messageEvent,function(e) {
	  console.log("Valor iframe:" + e.data);
    },false);