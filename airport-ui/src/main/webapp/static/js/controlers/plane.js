function PlaneController($scope, $http, apiProvider, $routeParams, $translate, $timeout) {
    if($routeParams.planeId) {
        $scope.plane = apiProvider.plane().get({id: $routeParams.planeId});
    } else {
        $scope.planes = apiProvider.plane().query();
    }
}

PlaneController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout'];
app.controller('PlaneController', PlaneController);