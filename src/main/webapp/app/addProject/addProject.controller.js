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

        $scope.project = {};

        $scope.createProject = function() {
            Projects.saveProject($scope.project);
            $route.reload();
            $location.path('projects');
        }
    };
})();

