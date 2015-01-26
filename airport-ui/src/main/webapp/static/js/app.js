var app = angular.module('airport', ['ngResource', 'ngRoute', 'pascalprecht.translate'], function($anchorScrollProvider) { 
    $anchorScrollProvider.disableAutoScrolling();
});

app.config(['$httpProvider', function($httpProvider) {  
    $httpProvider.responseInterceptors.push('redirectInterceptor');
}]);
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/login', {
        templateUrl: 'static/partials/login.html',
        controller: 'LoginController'
      }).
      /* flight */
      when('/flight', {
        templateUrl: 'static/partials/flight/list.html',
        controller: 'FlightController'
      }).
      when('/flight/create', {
        templateUrl: 'static/partials/flight/create.html',
        controller: 'FlightController'
      }).
      when('/flight/edit/:flightId', {
        templateUrl: 'static/partials/flight/edit.html',
        controller: 'FlightController'
      }).
      when('/flight/detail/:flightId', {
        templateUrl: 'static/partials/flight/detail.html',
        controller: 'FlightController'
      }).
      /* destination */
      when('/destination', {
        templateUrl: 'static/partials/destination/list.html',
        controller: 'DestinationController'
      }).
      when('/destination/create', {
        templateUrl: 'static/partials/destination/create.html',
        controller: 'DestinationController'
      }).
      when('/destination/edit/:destinationId', {
        templateUrl: 'static/partials/destination/edit.html',
        controller: 'DestinationController'
      }).
      /* plane */
      when('/plane', {
        templateUrl: 'static/partials/plane/list.html',
        controller: 'PlaneController'
      }).
      when('/plane/create', {
        templateUrl: 'static/partials/plane/create.html',
        controller: 'PlaneController'
      }).
      when('/plane/edit/:planeId', {
        templateUrl: 'static/partials/plane/edit.html',
        controller: 'PlaneController'
      }).
      /* steward */
      when('/steward', {
        templateUrl: 'static/partials/steward/list.html',
        controller: 'StewardController'
      }).
      when('/steward/create', {
        templateUrl: 'static/partials/steward/create.html',
        controller: 'StewardController'
      }).
      when('/steward/edit/:stewardId', {
        templateUrl: 'static/partials/steward/edit.html',
        controller: 'StewardController'
      }).
      /* default */
      otherwise({
        redirectTo: '/',
        templateUrl: 'static/partials/index.html',
      });
  }]);

app.factory('redirectInterceptor', ['$location', '$q', function($location, $q) { return function(promise) {
   promise.then(
        function(response) {
            if (typeof response.data === 'string') {
                if (response.data.indexOf('<html>') != -1) {
                    $location.path("/login");
                }
            }
            return response;
        },
        function(response) {
            return $q.reject(response);
        }
    );
    return promise;
};
}]);

/*app.factory('sessionInjector', ['SessionService', function(SessionService) {  
    var sessionInjector = {
        request: function(config) {
            if (!SessionService.isAnonymus) {
                config.headers['x-session-token'] = SessionService.token;
            }
            return config;
        }
    };
    return sessionInjector;
}]);*/

app.provider('apiProvider', function apiProvider() {
    this.baseUrl = null;
    this.token = null;
    this.language = null;

    this.$get = ['$http', '$resource', function($http, $resource) {
        var baseUrl = this.baseUrl;
        var token = this.token;
        var language = this.language;
        return {
            language : language,
            token : token,
            actions : {
                'get' : { method: 'GET'},
                'save': { method: 'POST' },
                'query' : { method: 'GET', isArray : true },
                'update': { method: 'PUT' },
                'remove': { method: 'DELETE' } 
            },
            baseUrl: function() {
              return baseUrl;
            },
            loggedUser: function() {
              return $resource(baseUrl + '/user',
                { token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            destination: function() {
              return $resource(baseUrl + '/destinations/:id',
                { id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            flight: function() {
              return $resource(baseUrl + '/flights/:id',
                {
                  id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            plane: function() {
              return $resource(baseUrl + '/planes/:id',
                {
                  id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            steward: function() {
              return $resource(baseUrl + '/stewards/:id',
                {
                  id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            }
        }
    }];
    this.setBaseUrl = function(url) {
        this.baseUrl = url;
    };
    this.setToken = function(name) {
        this.token = name;
    };
    this.getLanguage = function() {
        return this.language;
    };
    this.setLanguage = function(language) {
        this.language = language;
    };
});

app.config(['apiProviderProvider', function(apiProvider){
      apiProvider.setBaseUrl('http://localhost:8080/pa165/rest');
      apiProvider.setToken('');
      
      // detect lang
      var lang = navigator.language || navigator.userLanguage;
      if(lang !== 'cs'){
          lang = 'en';
      }
      
      // set degault lang
      apiProvider.setLanguage(lang);
}]);

// bootstrap
angular.element(document).ready(function() {
    angular.bootstrap(document, ['airport']);
});