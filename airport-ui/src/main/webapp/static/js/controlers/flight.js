function FlightController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    
    $scope.plane = {id:'', origin:'', destination:'', departure:'', arrival:'', 
        capacity:'', plane:'', stewards:[]};
    
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
            departure: $scope.flight.departure,
            arrival: $scope.flight.arrival,
            capacity: $scope.flight.capacity,
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
    
    $scope.createFlight = function() {
        apiProvider.flight().save({
            origin: $scope.flight.origin,
            destination: $scope.flight.destination,
            departure: $scope.flight.departure,
            arrival: $scope.flight.arrival,
            capacity: $scope.flight.capacity,
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
    
    $scope.deleteFlight = function() {
        //
        apiProvider.flight().remove({
            id: $routeParams.flightId
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
            !$scope.flight.stewards) 
            {
                $scope.incomplete = true;
            }
        
        if($scope.flight.capacity == null ||
            $scope.flight.capacity < 1) 
            {
                $scope.error = true;
            }
    }
}

FlightController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('FlightController', FlightController);