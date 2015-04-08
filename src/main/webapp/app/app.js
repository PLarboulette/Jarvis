/**
 * Created by Pierre on 06/04/2015.
 */


(function () {

    'use strict';

    angular
        .module('JarvisApp', [
            'ngRoute',
            'ngMaterial',
            'ngMessages',
            'ngResource'
        ])
        .config(routeConfig)

    routeConfig.$inject = ['$routeProvider'];

    function routeConfig($routeProvider) {
        $routeProvider.
            otherwise({
                redirectTo: '/connection'
            });
    }

})();