

/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location','$log','Projects','$http'];

    function projectsController($scope, $location, $log, Projects, $http) {





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

            $http.get("http://www.w3schools.com/angular/customers.php").
                success(function(data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available
                    console.log(data);
                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                });

        };

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

