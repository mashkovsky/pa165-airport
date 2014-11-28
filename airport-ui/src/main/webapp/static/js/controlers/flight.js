function FlightController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
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
                $scope.result = 'ok';
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
                $scope.result = 'ok';
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
                $scope.result = 'ok';
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
}

FlightController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('FlightController', FlightController);