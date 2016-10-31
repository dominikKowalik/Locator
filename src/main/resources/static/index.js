/**
 * Created by dominik on 2016-10-30.
 */
var app = angular.module('locator', []);

app.controller('appController', function ($scope, $rootScope, $http) {

    $scope.listFriends = function () {

        $http({
            method: 'GET', url: 'user',
            headers: {'Authorization': $rootScope.authenticationHeader}
        }).then(function (response) {
            $scope.ifFriendsHidden = false;
            $scope.names = response.data;
        }, function (response) {

        })

    }

});

app.controller('loginController', function ($scope, $rootScope, $http) {
    $scope.isHidden = true;
    $scope.isLoginFormHidden = false;
    $scope.isRegisterFormHidden = true;
    $scope.isHiddenSuccess = true;
    $scope.isLoginRegisterHidden = false;
    $rootScope.isAppHidden = true;
    $scope.signIn = function () {
        var string = $scope.user + ":" + $scope.pass;
        var encodedString = btoa(string);
        $http({
            method: 'GET', url: 'register/exists',
            headers: {'Authorization': 'Basic ' + encodedString}
        }).then(function (response) {
            $rootScope.username = $scope.user;
            $rootScope.authenticationHeader = 'Basic ' + encodedString;
            $scope.isLoginRegisterHidden = true;
            $rootScope.isAppHidden = false;
        }, function (response) {
            $scope.result = "nieprawidłowe hasło i/lub nazwa użytkownika";
            $scope.isHidden = false;
        })
    }
    $scope.showOppositeForm = function () {
        $scope.isLoginFormHidden = !$scope.isLoginFormHidden;
        $scope.isRegisterFormHidden = !$scope.isRegisterFormHidden;
        $scope.isHidden = true;
    }
    $scope.signUp = function () {
        //check if password is the same as confirmpassword
        if ($scope.passR == $scope.passConfirm && !($scope.emailR == undefined) && !($scope.passR == undefined) && !($scope.usernameR == undefined)) {
            //information box doesnt visible
            $scope.isHidden = true;
            var url = "register";
            //setting data to send
            var data = {
                username: $scope.usernameR,
                password: $scope.passR,
                email: $scope.emailR
            };
            //sending data
            $http.post(url, data).then(function (response) {
                //user has sent already exists
                $scope.isHidden = true;
                $scope.isHiddenSuccess = false;
                $scope.result = "Rejestracja się udała"
                console.log(response.status);
                console.log(response.data);
            }, function (response) {
                $scope.isHiddenSuccess = true;
                $scope.result = "użytkownik o tej nazwie już istnieje";
                $scope.isHidden = false;
            });
        } else {
            // if there is incorrect input, form validation
            if ($scope.usernameR == undefined) {
                $scope.result = "wprowadź nazwe użytkownika";
                $scope.isHidden = false;
            } else if ($scope.passR == undefined) {
                $scope.result = "wprowadź hasło";
                $scope.isHidden = false;
            } else if (!($scope.passR == $scope.passConfirm)) {
                $scope.result = "upewnij się, że prawidłowo przepisałeś hasło";
                $scope.isHidden = false;
            } else if ($scope.emailR == undefined) {
                $scope.result = "wprowadź prawidłowy adres email";
                $scope.isHidden = false;
            }
        }
    }
});




