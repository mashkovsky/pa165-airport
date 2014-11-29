function DestinationController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.destinationId) {
        $scope.destination = apiProvider.destination().get({id: $routeParams.destinationId});
    } else {
        $scope.destinations = apiProvider.destination().query();
    }
    
    $scope.updateDestination = function() {
        //
        apiProvider.destination().update({
            id: $routeParams.destinationId,
            country: $scope.destination.country,
            city: $scope.destination.city
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.createDestination = function() {
        apiProvider.destination().save({
            country: $scope.destination.country,
            city: $scope.destination.city
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
    
    $scope.deleteDestination = function() {
        //
        apiProvider.destination().remove({
            id: $routeParams.destinationId
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
            } 
            else {
                $scope.result = 'error';
            }
        });
    }
}

DestinationController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('DestinationController', DestinationController);