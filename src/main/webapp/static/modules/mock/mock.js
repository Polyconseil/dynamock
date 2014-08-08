app.factory('mockService', ['$http', function($http) {
  return {
    list: function() {
      return $http.get(API_ROOT+'/mock/').then(function(result) {
        return result.data;
      });
    },
    get: function(mockId) {
      return $http.get(API_ROOT+'/mock/'+mockId).then(function(result) {
        return result.data;
      });
    },
    delete: function(labelId) {
      return $http.delete(API_ROOT+'/mock/'+labelId).then(function(result) {
        return result.data;
      });
    },
    save: function(mock) {
      return $http.post(API_ROOT+'/mock/', mock);

    }
  }
}]);

app.controller('MockCtrl', ['$scope',
function MockCtrl(    $scope) {

  $scope.tempId = (Date.now()-1392854400000)+'-'+Math.ceil(Math.random()*1000);
}]);

app.controller('MockListCtrl', ['$scope', 'mockService', '$rootScope',
function MockListCtrl(    $scope,   mockService,   $rootScope) {

    mockService.list().then(function(data) {
      $scope.mocks = data;
    });
    
    $scope.predicate = 'namespace';

    $scope.delete = function(mockId) {
      if (confirm("Êtes vous certain de vouloir supprimer le mock ?")) {
        mockService.delete(mockId).then(function() {
          for (var i in $scope.mocks) {
            if ($scope.mocks[i].id === mockId) {
              $scope.mocks.splice(i, 1);
              break;
            }
          }
          //paramétrage de la pop-up de suppression
          $rootScope.messages.push({
              headerContent: 'Confirmation', 
              bodyContent: 'La suppression de l\'élément a été réalisée avec succès.' 
          });
        });
      }
    }

  }]);

app.controller('MockUpdateCtrl', ['$scope', '$state', '$stateParams', 'mockService', '$rootScope',
function MockUpdateCtrl(    $scope,   $state,   $stateParams,   mockService,   $rootScope) {

    /*
     * Initialisation et sauvegarde
     */
    var mockId = $stateParams.mockId;
    
    var namespaces=[];
    mockService.list().then(function(data) {
        $.each(data, function(i, elem) {
            if ($.inArray(elem.namespace, namespaces) == -1) {
            	namespaces.push(elem.namespace);
            }

          });
      });
    $scope.namespaces=namespaces;

    mockService.get(mockId).then(function(mock) {

      if (!mock) {
        mock = {};
      }

      if (!mock.id) {
        mock.id = $stateParams.mockId;
      }

      // Init des champs de l'objet
      if (!mock.request) {
        mock.request = {};
      }
      if (!mock.response) {
        mock.response = {};
      }

      $scope.mock = mock;
      $scope.contentType= mock.response.responseType?mock.response.responseType:"text/html";
    });


    $scope.submit = function() {
      mockService.save($scope.mock).then(function() {
        //paramétrage de la pop-up d'ajout/modification
        $rootScope.messages.push({
            headerContent: 'Confirmation', 
            bodyContent: 'La modification l\'ajout de l\'élément a été réalisée avec succès.' 
        });
      });
    };

}]);
