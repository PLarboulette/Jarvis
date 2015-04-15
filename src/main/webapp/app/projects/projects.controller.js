

/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location','$log','Projects','$http','$mdSidenav'];

    function projectsController($scope, $location, $log, Projects, $http, $mdSidenav) {

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
            console.log(tab);
            // $location.path('addProject');

        }

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
                        if ( old && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
                        if ( current )                $log.debug('Hello ' + selected.title + '!');
                        console.log($scope.selectedIndex);
                    });


                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
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
                });
        };

        $scope.saveProjectSettings = function () {
            console.log("[SAUVEGARDE PARAMETRES PROJET");
        }







    };



})();



/*var tabs =  $.ajax({
 method: "GET",
 url: 'rest/user/Pierre/project'
 }).done(function (data) {
 console.log("[SUCCESS] Récupération des projets.");
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

