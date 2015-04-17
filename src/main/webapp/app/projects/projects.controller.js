

/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location','$log','Projects','$http','$mdSidenav', '$route', '$mdDialog'];

    function projectsController($scope, $location, $log, Projects, $http, $mdSidenav, $route, $mdDialog) {

        $scope.project = {
            projectNameSettings : "",
            projectDescriptionSettings : "",
            projectBeginDateSettings : "",
            projectEndDateSettings : "",
            projectTechnologiesSettings : "",
            projectAchievedSettings : ""
        }

        // Takes the id of the selected project
        $scope.idProject = 0;

        $scope.task = {
            taskName : "",
            taskText : "",
            taskBeginDate : "",
            taskEndDate : "",
            taskDuration : "",
            taskAchieved : ""
        }

        // displays tabs for projects
        loadProjects();






        $scope.addProject = function() {
            $location.path('addProject');

        }

        // Load the projects (All the projects, not only for the selected user --> TO DO)
        function loadProjects () {
            $http.get("rest/user/userID/project").
                success(function(data) {
                    var tabs = [],
                        selected = null,
                        previous = null;
                    for (var i = 0 ; i < data.length; i++) {
                        tabs.push( {title: data[i]['name'],
                            content: data[i]['description'],
                            id : data[i]['id']
                        } );
                    }
                    $scope.tabs = tabs;
                    $scope.selectedIndex =  0 ;
                    $scope.$watch('selectedIndex', function(current, old){
                        previous = selected;
                        selected = tabs[current];
                        $scope.idProject = tabs[current]['id'];
                        loadProject();
                    });
                });
        };

        // load data of the selected project in the tabs
        function loadProject () {
            $http.get("rest/user/userID/project/"+$scope.idProject).
                success(function(data, status, headers, config) {

                    console.log(data);

                    $scope.messages = data;

                });
        }



        // Close the sidenav of the project's settings
        $scope.closeProjectSettings = function() {
            $mdSidenav('projectSettings').close();
        };

        // Display the settings of the selected project
        $scope.toggleProjectSettings = function() {
            $mdSidenav('projectSettings').toggle()
                .then(function(){
                    $http.get("rest/user/userID/project/"+$scope.idProject ).
                        success(function(data) {
                            $scope.project.projectNameSettings = data['name'];
                            $scope.project.projectDescriptionSettings  = data['description'];
                            $scope.project.projectBeginDateSettings = data['beginDate'];
                            $scope.project.projectEndDateSettings = data['endDate'];
                            var tabTechnologies = data['technologies'];
                            var technologiesForInput ="";
                            for (var i = 0 ; i < tabTechnologies.length; i++) {
                                technologiesForInput+=tabTechnologies[i]+" ";
                            }
                            $scope.project.projectTechnologiesSettings = technologiesForInput;
                            $scope.project.projectAchievedSettings = data['achieved'] == "true";
                        });
                });
        };

        // save the new settings of the project
        $scope.saveProjectSettings = function () {
            var projectToCreate = {};
            projectToCreate.name = $scope.project.projectNameSettings;
            projectToCreate.description = $scope.project.projectDescriptionSettings;
            projectToCreate.beginDate = $scope.project.projectBeginDateSettings;
            projectToCreate.endDate = $scope.project.projectEndDateSettings;
            projectToCreate.id = $scope.idProject;
            var tabTechnologies = [];
            var technologiesInput = $scope.project.projectTechnologiesSettings.split(" ");
          for (var i= 0 ; i< technologiesInput.length; i++) {
                tabTechnologies.push(technologiesInput[i]);
            }
            projectToCreate.technologies = tabTechnologies;
            projectToCreate.achieved = $scope.project.projectAchievedSettings;
            $http.put('rest/user/userID/project', projectToCreate).
                success(function(data, status, headers, config) {
                    $route.reload();
                    $location.path('projects');
                });

        }


        // Display the dialog to add a task to the selected project
        $scope.showAddTask = function(ev) {
            $mdDialog.show({
                controller: 'ProjectsController',
                templateUrl: 'app/projects/addTask.tmpl.html',
                targetEvent: ev,
            })
                .then(function(answer) {
                    $scope.alert = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.alert = 'You cancelled the dialog.';
                });
        };


        $scope.cancel = function() {
            $mdDialog.cancel();
        };
        $scope.addTask = function(answer) {

            var taskToCreate = {};
            taskToCreate.name = $scope.task.taskName;
            taskToCreate.description =$scope.task.

            console.log("ok");

            $mdDialog.hide(answer);
        };




    };



})();



/*var tabs =  $.ajax({
 method: "GET",
 url: 'rest/user/Pierre/project'
 }).done(function (data) {
 console.log("[SUCCESS] R�cup�ration des projets.");
 }),selected = null,
 previous = null;


 var tabs = Projects.query({userId:"Pierre"}),


 selected = null,
 previous = null;
 $scope.tabs = tabs ;
 // $scope.selectedIndex = 2;
 $scope.$watch('selectedIndex', function(current, old){
 previous = selected;
 selected = tabs[current];
 if ( old && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
 if ( current )                $log.debug('Hello ' + selected.title + '!');
 });*/

