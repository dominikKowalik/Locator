var app = angular.module('locator', []);

app.controller('getUser', function ($scope, $http) {
    $scope.getUser = function (userName) {
        $http.get("/user/byname/" + userName).then(function (response) {
            var result =  angular.fromJson(response.data);
            $scope.username = result.username;
            $scope.statement = result.statement;
        }, function (response) {
            $scope.result = "something went wrong";
        })
    }
});