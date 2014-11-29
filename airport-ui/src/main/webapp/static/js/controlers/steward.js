function StewardController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.stewardId) {
        $scope.steward = apiProvider.steward().get({id: $routeParams.stewardId});
    } else {
        $scope.stewards = apiProvider.steward().query();
    }
    
    $scope.updateSteward = function() {
        //
        apiProvider.steward().update({
            id: $routeParams.stewardId,
            firstName: $scope.steward.firstName,
            lastName: $scope.steward.lastName
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
    
    $scope.createSteward = function() {
        apiProvider.steward().save({
            firstName: $scope.steward.firstName,
            lastName: $scope.steward.lastName
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
    
    $scope.deleteSteward = function() {
        //
        apiProvider.steward().remove({
            id: $routeParams.stewardId
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

StewardController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('StewardController', StewardController);