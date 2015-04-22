/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location', '$log', 'Projects',
        '$mdSidenav', '$route', '$mdDialog','Tasks','$rootScope'];

    function projectsController($scope, $location, $log, Projects,
                                $mdSidenav, $route, $mdDialog, Tasks, $rootScope) {

        $scope.project = {};

        $scope.task = {};

        var tabs = Projects.getProjects();
        tabs.$promise.then(function (result) {
            $scope.tabs = result;
        });

        $scope.$watch('selectedIndex', function (current, old) {
            $scope.project.name = tabs[$scope.selectedIndex]['name'];
            $scope.project.description = tabs[$scope.selectedIndex]['description'];
            $scope.project.beginDate = tabs[$scope.selectedIndex]['beginDate']; ;
            $scope.project.endDate = tabs[$scope.selectedIndex]['endDate'];
            $scope.project.technologies = tabs[$scope.selectedIndex]['technologies'];
            $scope.project.achieved = tabs[$scope.selectedIndex]['achieved']=='true';
            $rootScope.idProject = tabs[$scope.selectedIndex]['id'];
            $scope.tasks = tabs[$scope.selectedIndex]['listTasks'];

        });

        $scope.toggleProjectSettings = function () {
            $mdSidenav('projectSettings').toggle()
        };

        $scope.closeProjectSettings = function () {
            $mdSidenav('projectSettings').close();
        };

        $scope.saveProjectSettings = function () {
            Projects.updateProject($scope.project,$rootScope.idProject);
            $route.reload();
            $location.path('projects');
        };

        $scope.showAddTask = function (ev) {
            $mdDialog.show({
                controller: 'ProjectsController',
                templateUrl: 'app/projects/addTask.tmpl.html',
                targetEvent: ev,
            });
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.addTask = function (answer) {
            Tasks.saveTask($scope.task, $rootScope.idProject);
            $route.reload();
            $location.path('projects');
            $mdDialog.hide(answer);
        };

        $scope.addProject = function () {
            $location.path('addProject');
        };

    };


})();
