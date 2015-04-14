/**
 * Created by Pierre on 09/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('addProjectController', addProjectController);

    addProjectController.$inject = ['$scope', '$location','$log','Projects','$http','$route'];

    function addProjectController($scope, $location, $log, Projects, $http, $route){

    $scope.project = {
        projectName : "",
        projectDescription : "",
        projectBeginDate : "",
        projectEndDate : ""
    }

    $scope.createProject = function() {
        console.log("OK");
        var projectToCreate = {};
        projectToCreate.name = $scope.project.projectName;
        projectToCreate.description = $scope.project.projectDescription;
        projectToCreate.beginDate = $scope.project.projectBeginDate;
        projectToCreate.endDate = $scope.project.projectEndDate;


        $http.post('rest/user/userID/project', projectToCreate).
            success(function(data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                console.log(data);
                console.log("ok pour l'insertion");

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

