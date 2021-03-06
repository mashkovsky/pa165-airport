/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function UserController($scope, $http, apiProvider, $routeParams, $translate, $timeout, $location, $rootScope) {
   
    $scope.newUserEmail = '';
    $scope.newUserName = '';
    $scope.newPassword = '';
    $scope.newPasswordConfirm = '';
    
    
    $scope.createNewUser = function() {   
                
        apiProvider.newUser().save({
            email: $scope.newUserEmail,
            name: $scope.newUserName,
            password: $scope.newPassword,
            is_privileged: 0
        },
        function(res) {
            if(res.$resolved) {
                $scope.result = 'success';
                setTimeout(function () {
                    window.location.href = "http://localhost:8080/pa165/index.html#/user"; //will redirect to your blog page (an ex: blog.html)
                }, 500);
            } 
            else {
                $scope.result = 'error';
            }
        });
       
    }
}

UserController.$inject = ['$scope', '$http', 'apiProvider', '$routeParams', '$translate', '$timeout', '$location', '$rootScope'];
app.controller('UserController', UserController);

