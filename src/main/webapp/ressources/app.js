console.log("restart from scratch");
var champion=null;
var premierTour=0;
$(document).ready(function(){



  $('#game').hide();

  $('#hydrea').click(function(){
    hideBordersCards();
    champion=1;
    $('#infos').html("Voici le super champion hydrea");
    $('#hydrea').css("border","1px solid white");
    $('#visu').html("<img src='ressources/pions/hydrea.png' class='visu'>");

  });

  $('#battler').click(function(){
    hideBordersCards();
    champion=2;
    $('#infos').html("Voici le super champion battler");
    $('#battler').css("border","1px solid white");
    $('#visu').html("<img src='ressources/pions/battler.png' class='visu'>");
  });

  $('#derp').click(function(){
    hideBordersCards();
    champion=3;
    $('#infos').html("Voici le super champion derp");
    $('#derp').css("border","1px solid white");
    $('#visu').html("<img src='ressources/pions/derp.png' class='visu'>");
  });

  $('#gorrok').click(function(){
    hideBordersCards();
    champion=4;
    $('#infos').html("Voici le super champion gorrok");
    $('#gorrok').css("border","1px solid white");
    $('#visu').html("<img src='ressources/pions/gorrok.png' class='visu'>");
  });

  $('#naksian').click(function(){
    hideBordersCards();
    champion=5;
    $('#infos').html("Voici le super champion naksian");
    $('#naksian').css("border","1px solid white");
    $('#visu').html("<img src='ressources/pions/naksian.png' class='visu'>");
  });

  $('#l').click(function(){
    poserChamp(champion,1);
  });
  $('#lb').click(function(){
    poserChamp(champion,2);
  });
  $('#rb').click(function(){
    poserChamp(champion,3);
  });
  $('#r').click(function(){
    poserChamp(champion,4);
  });

});

function hideBordersCards(){
  $('#naksian').css("border","0px solid white");
  $('#gorrok').css("border","0px solid white");
  $('#derp').css("border","0px solid white");
  $('#battler').css("border","0px solid white");
  $('#hydrea').css("border","0px solid white");
}

function poserChamp(champion,lane){

  if(champion == null){
     $('#infos').html("Veuillez selectionner un Champion avant de choisir votre lane !");
  }else{

    //requete
     $.ajax({
      url: "/v1/games",
      contentType : 'application/json',
      data: JSON.stringify({
        "champ": champion,
        "lane": lane
      }),

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
    });
  }
}

function majManaScor(json){
  if(name == json.player1.name){
    $("#mana").html("Vous : "+json.mana1+"<br>J2 : "+json.mana2);
    $("#score").html("Vous : "+json.score1+"<br>J2 : "+json.score2);
  }else{
    $("#mana").html("J1 : "+json.mana1+"<br>Vous : "+json.mana2);
    $("#score").html("J1 : "+json.score1+"<br>Vous : "+json.score2);
  }
  
}


//unite
var unite = function(posX,posY,ID,t,pv){this.X = posX;this.Y=posY;this.Id=ID,this.type=t,this.vie=pv}

//variables
var liste1 = [];
var liste2 = [];

//recuperation des éléments du canvas (a caler dans le document.ready)
var canvas =  document.getElementById("plat");
var ctx = canvas.getContext("2d");
ctx.fillStyle="#FE2E2E";

//images
var sprit1 =  new Image();
var sprit2 =  new Image();
var sprit3 =  new Image();
var sprit4 =  new Image();
var sprit5 =  new Image();
sprit1.src = "ressources/pions/hydrea.png";
sprit2.src = "ressources/pions/battler.png";
sprit3.src = "ressources/pions/derp.png";
sprit4.src = "ressources/pions/gorrok.png";
sprit5.src = "ressources/pions/naksian.png";

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

  var type;//sprites

  //joueur 1
  for (var i = 0; i < liste1.length; i++) {
    type = liste1[i].type;
    if(type == 1){
      console.log("JE SUIS LA");
      ctx.drawImage(sprit1,0,0,41,41,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(type == 2){
      ctx.drawImage(sprit2,0,0,41,41,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(type == 3){
      ctx.drawImage(sprit3,0,0,41,41,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(type == 4){
      ctx.drawImage(sprit4,0,0,41,41,liste1[i].X, liste1[i].Y, 32, 32);
    }else if(type == 5){
      ctx.drawImage(sprit5,0,0,41,41,liste1[i].X, liste1[i].Y, 32, 32);
    }

    ctx.fillStyle="#01DF01";
    ctx.fillRect(liste1[i].X, liste1[i].Y, liste2[i].vie*30, 4);
  }

  //j2
  for (var i = 0; i < liste2.length; i++) {
      type = liste2[i].type;
      if(type == 1){
       ctx.drawImage(sprit1,0,0,41,41,liste2[i].X, liste2[i].Y, 32, 32);
      }else if(type == 2){
        ctx.drawImage(sprit2,0,0,41,41,liste2[i].X, liste2[i].Y, 32, 32);
      }else if(type == 3){
        ctx.drawImage(sprit3,0,0,41,41,liste2[i].X, liste2[i].Y, 32, 32);
      }else if(type == 4){
        ctx.drawImage(sprit4,0,0,41,41,liste2[i].X, liste2[i].Y, 32, 32);
      }else if(type == 5){
        ctx.drawImage(sprit5,0,0,41,41,liste2[i].X, liste2[i].Y, 32, 32);
      }

      ctx.fillStyle="#01DF01";
      ctx.fillRect(liste2[i].X, liste2[i].Y, liste2[i].vie*30, 4);
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
    liste1[k] = new unite((json.list_joueur1[k].x-1)*41,(json.list_joueur1[k].y-1)*41,json.list_joueur1[k].id,json.list_joueur1[k].type,json.list_joueur1[k].vie);

  }

   //joueur 1
  for(var k = 0; k<json.list_joueur2.length; k++){
    liste2[k] = new unite((json.list_joueur2[k].x-1)*41,(json.list_joueur2[k].y-1)*41,json.list_joueur2[k].id,json.list_joueur2[k].type,json.list_joueur2[k].vie);

  }
}