app.directive('criteriaValues', function() {
  return {
    restrict: 'E',
    replace: true,
    controller: 'CriteriaValuesInputCtrl',
    templateUrl: '/directive/criteria-values/criteria-values.html',
    scope: {
      values: '=',
      selected: '='
    }
  }
});

app.controller('CriteriaValuesInputCtrl', ['$scope',
       function CriteriaValuesInputCtrl(    $scope) {

  $scope.inputMode = false;
  $scope.stringValues = [];

  $scope.switchMode = function() {

    if ($scope.inputMode) {
      // Mode input vers boutons
      $scope.inputMode = false;
      $scope.values = inferTypes($scope.stringValues);
    } else {
      // Mode boutons vers input
      $scope.inputMode = true;
      $scope.stringValues = $scope.values.map(function(a) { return a.toString(); });
    }

    console.log($scope.values);
  };

  var inferTypes = function(values) {
    for (var i in values) {
      var val = values[i];
      if (typeof val === "string") {

        // Type Number
        if (val.match(/^[0-9\.]+$/)) {
          values[i] = parseFloat(val);
          continue;
        }

        // Type Booléen
        if (val === "true" || val === "false") {
          values[i] = (val === "true");
        }
      }
    }
    return values;
  };

  $scope.switchAndPrevent = function($event)
  {
    if ($event.keyCode == 13) {
      $scope.switchMode();
      $event.preventDefault();
    }
  };

  $scope.select = function(value) {
    $scope.selected = value;
  };

  $scope.typeBool = function(value) {
    return typeof value === 'boolean';
  }

  $scope.typeNum = function(value) {
    return typeof value === 'number';
  }
}]);
