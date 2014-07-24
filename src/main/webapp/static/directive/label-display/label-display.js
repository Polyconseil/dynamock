app.directive('labelDisplay', ['$sce', function($sce) {
  return {
    restrict: 'E',
    replace: true,
    controller: 'LabelDisplayCtrl',
    templateUrl: '/directive/label-display/label-display.html',
    scope: {
      label: '=',
      size: '='
    },
    link: function(scope, element, attrs) {
    }
  }
}]);

app.filter('bolHtml', function($sce) {
  return function(val) {
    var iframeStyle = '<style>body{margin: 0; overflow: hidden;}</style>';
    var bolStyle = '<link rel="stylesheet" href="http://www.sfr.fr.ipp2.sfr.fr/mobile-next/wro/core.css">';
    return $sce.trustAsHtml(iframeStyle+bolStyle+val);
  };
});

app.controller('LabelDisplayCtrl', ['$scope', '$element', '$timeout',
       function LabelDisplayCtrl   ( $scope ,  $element,   $timeout) {

  $scope.innerSize = {};
  $scope.updateSize = function() {
    $timeout($scope.updateSize, 1000);

    var innerContent;
    try {
      innerContent = angular.element(document.getElementById('result-iframe').contentWindow.document.body);
    } catch(e) {
      // noop;
    }
    if (!innerContent) {
      return;
    }

    $scope.innerSize = {x: innerContent.width(), y: innerContent.height()};
  };
  $scope.updateSize();

}]);

