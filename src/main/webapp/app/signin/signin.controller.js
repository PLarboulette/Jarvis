/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('SigninController', signinController);

    signinController.$inject = ['$scope', '$location', 'jarvisAppConnexion','$rootScope'];

    function signinController($scope, $location, jarvisAppConnexion, $rootScope) {
        $scope.user = {};

        $scope.requeting = false;

        $scope.errors = {};

        $scope.submit = submit;

        function submit() {
            $scope.requeting = true;
            $scope.errors = {};
            jarvisAppConnexion.connection($scope.user)
                .success(function () {
                    $rootScope.login = $scope.user.login;
                    $location.path('projects');
                })
               ;
        }

        $scope.newUser = function () {
            $location.path('addUser');
        }
    }

})();

