var app = angular.module('airport', ['ngResource', 'ngRoute', 'pascalprecht.translate'], function($anchorScrollProvider) { 
    $anchorScrollProvider.disableAutoScrolling();
});
        
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/flight', {
        templateUrl: 'static/partials/flight/list.html',
        controller: 'FlightController'
      }).
      when('/flight/:flightId', {
        templateUrl: 'static/partials/flight/edit.html',
        controller: 'FlightController'
      }).
      when('/destination', {
        templateUrl: 'static/partials/destination/list.html',
        controller: 'DestinationController'
      }).
      when('/destination/:destinationtId', {
        templateUrl: 'static/partials/destination/edit.html',
        controller: 'DestinationController'
      }).
      when('/plane', {
        templateUrl: 'static/partials/plane/list.html',
        controller: 'PlaneController'
      }).
      when('/plane/:planeId', {
        templateUrl: 'static/partials/plane/edit.html',
        controller: 'PlaneController'
      }).
      when('/steward', {
        templateUrl: 'static/partials/steward/list.html',
        controller: 'StewardController'
      }).
      when('/steward/:stewardId', {
        templateUrl: 'static/partials/steward/edit.html',
        controller: 'StewardController'
      }).
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
              return $resource(baseUrl + '/destination/:id',
                { id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            flight: function() {
              return $resource(baseUrl + '/flight/:id',
                {
                  id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            plane: function() {
              return $resource(baseUrl + '/plane/:id',
                {
                  id:'@id',
                  token : this.token, 
                  lang : this.language
                },
                this.actions
              );
            },
            steward: function() {
              return $resource(baseUrl + '/steward/:id',
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
      apiProvider.setBaseUrl('http://localhost:8080/rest');
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