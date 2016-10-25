console.log("restart from scratch");

$(document).ready(function(){
  $('#game').hide();

  $('#hydrea').click(function(){
    hideBordersCards();
    $('#infos').html("Voici le super champion hydrea");
    $('#hydrea').css("border","1px solid white");

  });

  $('#battler').click(function(){
    hideBordersCards();
    $('#infos').html("Voici le super champion battler");
    $('#battler').css("border","1px solid white");
  });

  $('#derp').click(function(){
    hideBordersCards();
    $('#infos').html("Voici le super champion derp");
    $('#derp').css("border","1px solid white");
  });

  $('#gorrok').click(function(){
    hideBordersCards();
    $('#infos').html("Voici le super champion gorrok");
    $('#gorrok').css("border","1px solid white");
  });

  $('#naksian').click(function(){
    hideBordersCards();
    $('#infos').html("Voici le super champion naksian");
    $('#naksian').css("border","1px solid white");
  });

});

function hideBordersCards(){
  $('#naksian').css("border","0px solid white");
  $('#gorrok').css("border","0px solid white");
  $('#derp').css("border","0px solid white");
  $('#battler').css("border","0px solid white");
  $('#hydrea').css("border","0px solid white");
}




//unite
var unite = function(posX,posY,ID){this.X = posX;this.Y=posY;this.Id=ID,sprites=6}

//variables
var liste1 = [];
var liste2 = [];
var premierTour = 0;

//recuperation des éléments du canvas (a caler dans le document.ready)
var canvas =  document.getElementById("plat");
var ctx = canvas.getContext("2d");
ctx.fillStyle="#FE2E2E";

//sprite
var sprite = new Image();
sprite.src = "ressources/sprites.png";
var num = 0;

//requête
$("#newturn").click(function(event){
    //$("#newturn").prop("disabled", true);
    $.ajax({
      url: "/v1/games",
      type: "PUT",
      dataType: "json",
      beforeSend : function(req) {
			req.setRequestHeader("Authorization", "Basic " + btoa(name + ":" + pswd));
		},
      success: function(json){
        console.log(json);
        $('#newturn').prop('disabled', true);
        if(premierTour == 0){
          console.log("premier tour");
          console.log(json);
          premierTour++;
          majListe(json);
          draw();
          $('#newturn').prop('disabled', false);
          
        }else{

          animate(json);

          setTimeout(function(){

            majListe(json);
            draw();
            num=0;
            $('#newturn').prop('disabled', false);

          }, 1000);
        }
      }   
    })
});


//animation
function animate(json){
  console.log("animation");
	var unite;
  
  //joueur 1
  for(var i = 0 ; i<liste1.length; i++){
    
    unite = getElemById(liste1[i].Id,json,1);

    if(unite != null){
      //console.log("je suis la : "+json.list_joueur1[unite].x);
      TweenLite.to(liste1[i], 1, {X: (json.list_joueur1[unite].x-1)*41, Y: (json.list_joueur1[unite].y-1)*41, sprites: 6, onUpdate:draw});
    }  
  }


  //joueur 2
  for(var i = 0 ; i<liste2.length; i++){
    
    unite = getElemById(liste2[i].Id,json,2);

    if(unite != null){
      //console.log("je suis la : "+json.list_joueur1[unite].x);
      TweenLite.to(liste2[i], 1, {X: (json.list_joueur2[unite].x-1)*41, Y: (json.list_joueur2[unite].y-1)*41, sprites: 6, onUpdate:draw});
    }  
  }
}



function draw(){
	//on dessine tous les elements de la liste
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  console.log("draw");

  var s;//sprites

  //joueur 1
  for (var i = 0; i < liste1.length; i++) {
    s =  liste1[i].sprites;
    if(s < 2){
      ctx.drawImage(sprite,0,0,32,32,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(s >=2 && s<4){
      ctx.drawImage(sprite,32,0,32,32,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(s >= 4 && s<=6){
      ctx.drawImage(sprite,64,0,32,32,liste1[i].X, liste1[i].Y, 32, 32);
    }
  }



    //joueur 2
  for (var i = 0; i < liste2.length; i++) {
    s =  liste2[i].sprites;
    if(s < 2){
      ctx.drawImage(sprite,0,224,32,32,liste2[i].X, liste2[i].Y, 32, 32);
    }else if(s >=2 && s<4){
      ctx.drawImage(sprite,32,224,32,32,liste2[i].X, liste2[i].Y, 32, 32);
    }else if(s >= 4 && s<=6){
      ctx.drawImage(sprite,64,224,32,32,liste2[i].X, liste2[i].Y, 32, 32);
    }

   	//ctx.fillRect(liste[i].X, liste[i].Y, 10, 10);

  }
}

function getElemById(Id,json,joueur){
  var rep=null;

  if(joueur == 1){
    for(var i = 0; i<json.list_joueur1.length ; i++){
      if(json.list_joueur1[i].id == Id){
        rep = i;
      }
    }
  }else if(joueur == 2){
    for(var i = 0; i<json.list_joueur2.length ; i++){
      if(json.list_joueur2[i].id == Id){
        rep = i;
      }
    }
  }
 
  return rep;
}

function majListe(json){
  console.log("maj");
  //joueur 1
  for(var k = 0; k<json.list_joueur1.length; k++){
    liste1[k] = new unite((json.list_joueur1[k].x-1)*41,(json.list_joueur1[k].y-1)*41,json.list_joueur1[k].id);
    liste1[k].sprites=0;
  }

   //joueur 1
  for(var k = 0; k<json.list_joueur2.length; k++){
    liste2[k] = new unite((json.list_joueur2[k].x-1)*41,(json.list_joueur2[k].y-1)*41,json.list_joueur2[k].id);
    liste2[k].sprites=0;
  }
}