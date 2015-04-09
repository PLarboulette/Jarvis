/**
 * Created by Pierre on 09/04/2015.
 */


(function () {
    angular
        .module('JarvisApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/addProject', {
                templateUrl: 'app/addProject/addProject.html',
                controller: 'addProjectController'
            });
    }
})();


