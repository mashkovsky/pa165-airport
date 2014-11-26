function FlightController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.flightId) {
        $scope.flight = apiProvider.flight().get({id: $routeParams.flightId});
    } else {
        $scope.flights = apiProvider.flight().query();
    }
}

FlightController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('FlightController', FlightController);