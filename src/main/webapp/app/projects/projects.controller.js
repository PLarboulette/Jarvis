

/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location','$log','Projects','$http','$mdSidenav', '$route'];

    function projectsController($scope, $location, $log, Projects, $http, $mdSidenav, $route) {

        $scope.project = {
            projectNameSettings : "",
            projectDescriptionSettings : "",
            projectBeginDateSettings : "",
            projectEndDateSettings : "",
            projectTechnologiesSettings : ""
        }

        loadProjects();

        $scope.addTab = function (title, view) {
            view = view || title + " Content View";
            tabs.push({ title: title, content: view, disabled: false});
        };
        $scope.removeTab = function (tab) {
            var index = tabs.indexOf(tab);
            tabs.splice(index, 1);
        };




        $scope.addProject = function() {
            $location.path('addProject');

        }

        $scope.idProject = 0;

        function loadProjects () {
            $http.get("rest/user/userID/project").
                success(function(data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available
                    var tabs = [],selected = null, previous = null;
                    for (var i = 0 ; i < data.length; i++) {
                        tabs.push( {title: data[i]['name'],
                            content: data[i]['description'],
                            id : data[i]['id']
                        } );
                    }
                    $scope.tabs = tabs;
                    $scope.selectedIndex =  1 ;
                    $scope.$watch('selectedIndex', function(current, old){
                        previous = selected;
                        selected = tabs[current];
                        $scope.idProject = tabs[current]['id'];
                    });
                });
        };


        $scope.closeProjectSettings = function() {
            $mdSidenav('projectSettings').close()
                .then(function(){
                    $log.debug("close RIGHT is done");
                });
        };

        $scope.toggleProjectSettings = function() {
            $mdSidenav('projectSettings').toggle()
                .then(function(){
                    $log.debug("toggle RIGHT is done");
                    var idProject  = $scope.idProject;

                    $http.get("rest/user/userID/project/idProject",{params : {"projectID" : idProject}} ).
                        success(function(data, status, headers, config) {
                            console.log(data);
                            $scope.project.projectNameSettings = data['name'];
                            $scope.project.projectDescriptionSettings  = data['description'];
                            $scope.project.projectBeginDateSettings = data['beginDate'];
                            $scope.project.projectEndDateSettings = data['endDate'];
                            var tabTechnologies = data['technologies'];
                            console.log(tabTechnologies);
                            /*for (int i = 0; i < tabTechnologes.length; i++) {
                                $scope.project.projectTechnologiesSettings += tabTechnologes[i];
                            }*/
                           // $scope.project.projectTechnologiesSettings =

                        }).
                        error(function(data, status, headers, config) {
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                        });

                    // Charger infos projet
                });
        };

        $scope.saveProjectSettings = function () {
            var projectToCreate = {};
            projectToCreate.name = $scope.project.projectNameSettings;
            projectToCreate.description = $scope.project.projectDescriptionSettings;
            projectToCreate.beginDate = $scope.project.projectBeginDateSettings;
            projectToCreate.endDate = $scope.project.projectEndDateSettings;
            projectToCreate.id = $scope.idProject;

            // Faire un tableau pour les technologies
            projectToCreate.technologies = ["java","CSharp"];

                // $scope.project.projectTechnologiesSettings;

            $http.put('rest/user/userID/project', projectToCreate).
                success(function(data, status, headers, config) {
                    $route.reload();
                    $location.path('projects');
                });

        }







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

