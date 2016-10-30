/**
 * Created by dominik on 2016-10-30.
 */
var app = angular.module('locator', []);



app.controller('loginController', function ($scope, $http) {
    $scope.isHidden = true;
    $scope.logIn = function () {

        var string = $scope.user + ":" + $scope.pass;
        var encodedString = btoa(string);

        $http({method: 'GET', url: 'user',
            headers: { 'Authorization': 'Basic ' + encodedString }
        }).then(function (response) {
            $scope.result = response.data;
            $scope.isHidden = true;
        }, function (response) {
            $scope.result = "nie udało się połączyć";
            $scope.isHidden = false;
        })
    }
});




