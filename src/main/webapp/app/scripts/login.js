/**
 * Created by Pierre on 02/04/2015.
 */

function Connexion() {
    var login = $("#login").val();
    var password = $("#password").val();

    $.ajax({
        url : "/Jarvis/rest/connect",
        type: "GET",
        data:"login="+login+"&password="+password,
        success    : function(data){

            if (data != "" ) {
                console.log("utilisateur détecté");

                /*Callback*/


            } else {
                console.log("Pas d'utilisateur");
            }
        }
    });




}

function loadProjectsForUser(id) {

}
