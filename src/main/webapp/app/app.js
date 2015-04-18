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
            'ngResource',
            'ngMdIcons'
        ])
        .config(config)

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider.
            otherwise({
                redirectTo: '/connection'
            });


    }

})();