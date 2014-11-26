function DestinationController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.destinationId) {
        $scope.destination = apiProvider.destination().get({id: $routeParams.destinationId});
    } else {
        $scope.destinations = apiProvider.destination().query();
    }
}

DestinationController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('DestinationController', DestinationController);