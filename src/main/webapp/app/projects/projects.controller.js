

/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('ProjectsController', projectsController);

    projectsController.$inject = ['$scope', '$location','$log'];

    function projectsController($scope, $location, $log) {

        var tabs = [
                { title: 'One', content: "Tabs will become paginated if there isn't enough room for them."},
                { title: 'Two', content: "You can swipe left and right on a mobile device to change tabs."},
                { title: 'Three', content: "You can bind the selected tab via the selected attribute on the md-tabs element."},
                { title: 'Four', content: "If you set the selected tab binding to -1, it will leave no tab selected."}
            ],
            selected = null,
            previous = null;
        $scope.tabs = tabs;
        $scope.selectedIndex = 2;
        $scope.$watch('selectedIndex', function(current, old){
            previous = selected;
            selected = tabs[current];
            if ( old && (old != current)) $log.debug('Goodbye ' + previous.title + '!');
            if ( current )                $log.debug('Hello ' + selected.title + '!');
        });
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
        };

    };



})();


