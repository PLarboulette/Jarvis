/**
 * Created by plarboul on 15/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('AddUserController', addUserConttroller);

    addUserConttroller.$inject = ['$scope', '$location','$log','Projects','$http','$route'];

    function addUserConttroller($scope, $location, $log, Projects, $http, $route){


        // TO DO  --> Service et pas http !
        $scope.submit = function() {

            var userToCreate = {};
            userToCreate.login = $scope.user.login;
            userToCreate.lastName = $scope.user.lastName;
            userToCreate.firstName = $scope.user.firstName;


            $http.post('rest/users', userToCreate).
                success(function(data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(data);
                    console.log("ok pour l'insertion de l'utilisateur");

                    $route.reload();
                    $location.path('projects');


                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });



        }
    };


})();
