function FlightController($scope, $http, apiProvider, $routeParams, $translate, $timeout, $rootScope) {
    $scope.flight = {id:'', origin:'', destination:'', departure:'', arrival:'', 
        plane:'', stewards:[]};
    
    if($routeParams.flightId) {
        $scope.flight = apiProvider.flight().get({id: $routeParams.flightId});
    } else {
        $scope.flights = apiProvider.flight().query();
    }
    
    $scope.planes = apiProvider.plane().query();
    $scope.destinations = apiProvider.destination().query();
    $scope.allStewards = apiProvider.steward().query();
    
    $scope.updateFlight = function() {
        //
        apiProvider.flight().update({
            id: $routeParams.flightId,
            origin: $scope.flight.origin,
            destination: $scope.flight.destination,
            departure: getDateTime($scope.flight.departure),
            arrival: getDateTime($scope.flight.arrival),
            plane: $scope.flight.plane,
            stewards: $scope.flight.stewards
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/flight"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    
    function getDateTime(dateStr) {
        // 0123456789
        // 2014/12/01 23:11
        var year = parseInt(dateStr.substring(0,4)),
            month = parseInt(dateStr.substring(5,7)),
            day = parseInt(dateStr.substring(8,10)),
            hour = parseInt(dateStr.substring(11,13)),
            minute = parseInt(dateStr.substring(14,16)),
            date = new Date(year, month, day, hour, minute);
    
        return date.getTime();
    }
    
    function isDateAfter(departure, arrival) {
        if(!departure || !arrival) {
            return false;
        }
        return getDateTime(departure) < getDateTime(arrival);
    }
    
    $scope.isDateAfter = function(departure, arrival) {
        
        return isDateAfter(departure, arrival);
    }
    
    $scope.createFlight = function() {
        
        apiProvider.flight().save({
            origin: $scope.flight.origin,
            destination: $scope.flight.destination,
            departure: getDateTime($scope.flight.departure),
            arrival: getDateTime($scope.flight.arrival),
            plane: $scope.flight.plane,
            stewards: $scope.flight.stewards
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/flight"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.deleteFlight = function(id, msg) {
        
        if(confirm(msg)) {
            // delete
            apiProvider.flight().remove({
                id: id
            },
            function(res) {
                if(res.$resolved) {
                    //$scope.result = 'success';
                    window.location.reload();
                } 
                else {
                    $scope.result = 'error';
                }
            });
        }
    }
    
    // test inputs
    $scope.error = true;
    $scope.incomplete = true; 
        
    $scope.test = function() {
        
        $scope.error = false;
        $scope.incomplete = false;
             
        if(!$scope.flight.origin ||
            !$scope.flight.destination ||
            !$scope.flight.departure || 
            !$scope.flight.arrival || 
            !$scope.flight.plane || 
            !$scope.flight.stewards ||
            !$scope.flight.stewards.length) 
            {
                $scope.incomplete = true;
            }
        
        if(($scope.flight.origin && $scope.flight.destination && $scope.flight.origin == $scope.flight.destination) ||
            !isDateAfter($scope.flight.departure, $scope.flight.arrival))
            {
                $scope.error = true;
            }
    }
}

FlightController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout', '$rootScope'];
app.controller('FlightController', FlightController);