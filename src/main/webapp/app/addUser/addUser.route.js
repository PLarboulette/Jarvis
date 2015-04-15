


(function () {
    angular
        .module('JarvisApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/addUser', {
                templateUrl: 'app/addUser/addUser.html',
                controller: 'AddUserController'
            });
    }
})();/**
 * Created by plarboul on 15/04/2015.
 */
