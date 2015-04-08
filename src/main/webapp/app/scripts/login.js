/**
 * Created by Pierre on 02/04/2015.
 */

var app = angular.module("jarvisApp",[]);

app.controller("test",function($scope) {

        $scope.phones=[{"name":"test"},{"name":"Test23"}];
    });


app.controller("show",function($scope) {
    $scope.showContent = true;
});