'use strict';

var App = angular.module('locator',[]);

App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
}]);