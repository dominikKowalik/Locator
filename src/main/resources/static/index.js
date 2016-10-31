/**
 * Created by dominik on 2016-10-30.
 */
var app = angular.module('locator', []);



app.controller('loginController', function ($scope, $http) {
    $scope.isHidden = true;
    $scope.isLoginFormHidden = false;
    $scope.isRegisterFormHidden = true;


    $scope.signIn = function () {

        var string = $scope.user + ":" + $scope.pass;
        var encodedString = btoa(string);

        $http({method: 'GET', url: 'user',
            headers: { 'Authorization': 'Basic ' + encodedString }
        }).then(function (response) {
            $scope.result = response.data;
            $scope.isHidden = false;
        }, function (response) {
            $scope.result = "nieprawidłowe hasło i/lub nazwa użytkownika";
            $scope.isHidden = false;
        })
    }

    $scope.showOppositeForm = function () {
        $scope.isLoginFormHidden = !$scope.isLoginFormHidden;
        $scope.isRegisterFormHidden = !$scope.isRegisterFormHidden;
    }

    $scope.signUp = function () {

        if($scope.passR == $scope.passConfirm)
        {$scope.isHidden = true;
    var url = "register"
        var data = {
            username : $scope.usernameR,
            password : $scope.passR,
            email : $scope.emailR
        };
        $http.post(url, data).
    then(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        console.log(data);
    }).
    error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });
    }else{
            $scope.result = "potwierdź hasło prawidłowo";
            $scope.isHidden = false;
        }
    }
});




