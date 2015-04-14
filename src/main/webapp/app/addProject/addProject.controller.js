/**
 * Created by Pierre on 09/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('addProjectController', addProjectController);

    addProjectController.$inject = ['$scope', '$location','$log','Projects'];

    function addProjectController($scope, $location, $log, Projects) {

    $scope.project = {
        projectName : "",
        projectDescription : "",
        projectBeginDate : "",
        projectEndDate : ""
    }

    $scope.createProject = function() {
        console.log("OK");
        var projectToCreate = {};
        projectToCreate.projectName = $scope.project.projectName;
        projectToCreate.projectDescription = $scope.project.projectDescription;
        projectToCreate.projectBeginDate = $scope.project.projectBeginDate;
        projectToCreate.projectEndDate = $scope.project.projectEndDate;


        Projects.$save({userId:"Pierre"},project);

    }

    };






})();

