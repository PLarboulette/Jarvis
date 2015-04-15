/**
 * Created by Pierre on 09/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('AddProjectController', addProjectController);

    addProjectController.$inject = ['$scope', '$location','$log','Projects','$http','$route'];

    function addProjectController($scope, $location, $log, Projects, $http, $route){

    $scope.project = {
        projectName : "",
        projectDescription : "",
        projectBeginDate : "",
        projectEndDate :"",
        projectTechnologies : ""
    }

    $scope.createProject = function() {
        console.log("OK");
        var projectToCreate = {};
        projectToCreate.name = $scope.project.projectName;
        projectToCreate.description = $scope.project.projectDescription;
        projectToCreate.beginDate = $scope.project.projectBeginDate;
        projectToCreate.endDate = $scope.project.projectEndDate;
        projectToCreate.technologies =  ["Java"];



        $http.post('rest/user/userID/project', projectToCreate).
            success(function(data, status, headers, config) {
                $route.reload();
                $location.path('projects');
            });

    }

    };






})();

