/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    angular
        .module('JarvisApp')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            when('/connection', {
                templateUrl: 'app/signin/signin.html',
                controller: 'SigninController'
            });
    }
})();

