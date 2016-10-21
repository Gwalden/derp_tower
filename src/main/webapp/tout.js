function hello(){
	console.log("hello");
}

function getListGame(){
	$.ajax({
    	url: "localhost:8080/v1/game",
    	type: "GET",
    	dataType : "json",
    	success: function( json ) {
    		console.log(json);
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
	$('#launch').click(getListGame());
}