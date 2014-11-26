function StewardController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.stewardId) {
        $scope.steward = apiProvider.steward().get({id: $routeParams.stewardId});
    } else {
        $scope.stewards = apiProvider.steward().query();
    }
}

StewardController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('StewardController', StewardController);