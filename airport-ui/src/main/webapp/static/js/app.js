var app = angular.module('airport', ['ngResource', 'ngRoute', 'pascalprecht.translate'], function($anchorScrollProvider) { 
    $anchorScrollProvider.disableAutoScrolling();
});
        
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
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
      when('/flight/delete/:flightId', {
        templateUrl: 'static/partials/flight/delete.html',
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
      when('/destination/delete/:destinationId', {
        templateUrl: 'static/partials/destination/delete.html',
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
      when('/plane/delete/:planeId', {
        templateUrl: 'static/partials/plane/delete.html',
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
      when('/steward/delete/:stewardId', {
        templateUrl: 'static/partials/steward/delete.html',
        controller: 'StewardController'
      }).
      /* default */
      otherwise({
        redirectTo: '/',
        templateUrl: 'static/partials/index.html',
      });
  }]);

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