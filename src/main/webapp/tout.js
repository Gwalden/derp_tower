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
				askGame();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
	}
}

function askGame(){
	$.ajax({
		type: "POST",
		url: "/v1/games",
		dataType: "json",
		beforeSend : function(req) {
			req.setRequestHeader("Authorization", "Basic " + btoa(name + ":" + pswd));
		},
		dataType: "json",
		success: function(json){
			getGame();
			console.log("ASKGAME OK");
		}
	})
}


function putGame(){
	$.ajax({
		type: "PUT",
		url: "/v1/games",
		dataType: "json",
		beforeSend : function(req) {
			req.setRequestHeader("Authorization", "Basic " + btoa(name + ":" + pswd));
		},
		dataType: "json",
		success: function(json){
			console.log("PUTGAME OK");

		}
	})
}

function getGame(){
	$.ajax({
		type: "GET",
		url: "/v1/games",
		dataType: "json",
		beforeSend : function(req) {
			req.setRequestHeader("Authorization", "Basic " + btoa(name + ":" + pswd));
		},
		dataType: "json",
		success: function(json){
			$("#log").hide();
			$("#game").show();
			if(premierTour == 0){
				console.log("premier tour");
				console.log(json);
				premierTour++;
				majListe(json);
				draw();
			}else{
				animate(json);
				setTimeout(function(){
					majListe(json);
					draw();
					num=0;
				}, 1000)};
				$('#newturn').prop('disabled', false);
				setTimeout(function(){getGame();}, 1000);

		},
		error: function() {
			setTimeout(function(){getGame();}, 1000);	    	  
		}
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
			name = $("#usrlogin").val();
			pswd =  $("#usrpswd").val();
			console.log(json);
			askGame();
		},
		error: function( xhr, status, errorThrown ) {
			alert( "Sorry, there was a problem!" );
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
		},
	});
}