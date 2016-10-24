var name;
var pswd;


function login()
{
	if($("#userlogin").val() != "") {
	     $.ajax
	     ({
	       type: "GET",
	       url: "/v1/userdb/login",
	       dataType: 'json',
	       beforeSend : function(req) {
	        req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#userpswd").val()));
	       },
	       success: function (data) {
	    	   name = $("#userlogin").val();
	    	   pswd =  $("#userpswd").val();
	        askgame();
	       },
	       error : function(jqXHR, textStatus, errorThrown) {
	       			alert('error: ' + textStatus);
	       	}
	     });
	     }
}

function askgame(){
	$.ajax({
		type: "POST",
		url: "/v1/games",
		dataType: "json",
		beforeSend : function(req) {
			req.setRequestHeader("Authorization", "Basic " + btoa(name + ":" + pswd));
		},
	})
}


function hello(){
	console.log("hello");
}

function creatUser(){
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
    		askgame();
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