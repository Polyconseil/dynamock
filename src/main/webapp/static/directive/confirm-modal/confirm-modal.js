app.directive('confirmModal', ['$sce', function($sce) {
  return {
    restrict: 'E',
    replace: true,
    controller: 'ConfirmModalCtrl',
    templateUrl: 'static/directive/confirm-modal/confirm-modal.html',
    scope: {}
  };
}]);


app.controller('ConfirmModalCtrl', ['$scope', '$rootScope', 
       function ConfirmModalCtrl   ( $scope,   $rootScope) {

    $scope.closeLayer = function closeLayer(id) {
      $scope.message = null;
      $rootScope.messages.shift();
    };

    $rootScope.$watch("messages", function(newVal, o) {
      if(newVal[0]) {
        $scope.message = newVal[0];
      }
    }, 
    true );
}]);

