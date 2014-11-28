function PlaneController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    
    if($routeParams.planeId) {
        $scope.plane = apiProvider.plane().get({id: $routeParams.planeId});
    } else {
        $scope.planes = apiProvider.plane().query();
    }
    
    // update plane
    $scope.updatePlane = function() {
        //
        apiProvider.plane().update({
            id: $routeParams.planeId,
            type: $scope.plane.type,
            name: $scope.plane.name,
            capacity: $scope.plane.capacity
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
    
    $scope.createPlane = function() {
        apiProvider.plane().save({
            type: $scope.plane.type,
            name: $scope.plane.name,
            capacity: $scope.plane.capacity
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
    
    $scope.deletePlane = function() {
        //
        apiProvider.plane().remove({
            id: $routeParams.planeId
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
    
    ///////////////////////////////////////////////////////////////////
}

PlaneController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('PlaneController', PlaneController);