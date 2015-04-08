/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    'use strict';

    angular
        .module('JarvisApp')
        .controller('SigninController', signinController);

    signinController.$inject = ['$scope', '$location', 'jarvisAppConnexion'];

    function signinController($scope, $location, jarvisAppConnexion) {
        $scope.user = {
            login: "",
            password: ""
        };

        $scope.requeting = false;

        $scope.errors = {};

        $scope.submit = submit;

        function submit() {
            $scope.requeting = true;
            $scope.errors = {};
            jarvisAppConnexion.connection($scope.user)
                .success(function () {
                    $location.path('projects');
                })
                .error(function () {
                    $scope.requeting = false;
                    $scope.errors.unauthorized = true;
                });
        }
    }

})();

