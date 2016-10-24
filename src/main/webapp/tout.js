function hello(){
	console.log("hello");
}

function getListGame(){
	$.ajax({
    	url: "/v1/userdb",
    	type: "POST",
    	contentType : 'application/json',
    	dataType : "json",
    	data : JSON.stringify({
			"name" : $("#usrlogin").val(),
			"password" : $("#usrpswd").val(),
			"email" : $("#usrmail").val(),
        }),
    	success: function( json ) {
    		$("<p>Vous etes inscris</p>").appendTo('#launch');
    		console.log("USER CREE");
    	},
    	error: function( xhr, status, errorThrown ) {
        alert( "Sorry, there was a problem!" );
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    },
    	complete: function( xhr, status ) {
        	alert( "The request is complete!" );
    	}
	});
}

function affiche(){
	getListGame();
}