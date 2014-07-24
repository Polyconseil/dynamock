var API_ROOT = 'api';

var app = angular.module('dynamockApp', ['ui.router', 'ui.bootstrap', 'ui.ace']);

app.config(['$locationProvider', '$stateProvider', '$urlRouterProvider',
    function($locationProvider, $stateProvider, $urlRouterProvider) {

  // Pour pouvoir passer en mode html5, il faut configurer un handler catch-all côté serveur
  $locationProvider.html5Mode(false);

  // Catch-all pour les url non mappées
  $urlRouterProvider.otherwise("/");

  $stateProvider
    .state('dashboard', {
      url: "/",
      templateUrl: "/modules/dashboard/dashboard.html"
    })
    .state('mock', {
      abstract: true,
      url: "/mock",
      templateUrl: "/modules/mock/mock.html"
    })
    .state('mock.list', {
      url: "", // L'url vide signifie qu'il sera utilisé quand on arrive sur l'url du parent
      templateUrl: "/modules/mock/list.html"
    })
    .state('mock.upsert', {
      url: "/{mockId:[a-zA-Z0-9_.-]+}",
      templateUrl: "/modules/mock/upsert.html"
    });

}]);

app.filter('nl2br', function($sce) {
  return function(val) {
    return $sce.trustAsHtml(val.replace(/\n/g, "<br>"));
  };
});

app.run(  ['$rootScope', '$state',
  function ($rootScope,   $state) {
    $rootScope.$state = $state;
    $rootScope.messages = [];
}]);

app.filter('stringify', function() {
  return function(input) {
    return JSON.stringify(input, null, 2);
  };
});
