angular.module('myApp')
    .factory('locator', [function() {
        return {
            // Send the Authorization header with each request
            'request': function(config) {
                config.headers = config.headers || {};
                var encodedString = btoa("domo1234:domo1234");
                config.headers.Authorization = 'Basic '+encodedString;
                return config;
            }
        };
    }]);