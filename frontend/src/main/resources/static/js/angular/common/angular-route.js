angular.module('grsApp', [ 'ngRoute' ]).config(routeConfig).run([ '$route', reloadRoute ]);

function routeConfig($routeProvider) {
  $routeProvider.when('/', {
    templateUrl : 'tpl/common/empty.html'
  }).when('/collections', {
    templateUrl : 'tpl/common/content.html',
    controller : 'collectionsController',
    controllerAs : 'collections',
    resolve : loader('')
  }).otherwise({
    // redirectTo : '/'
    templateUrl : 'tpl/common/content.html'
  });
}

// fix the bug for the nest include and view (https://github.com/angular/angular.js/issues/1213)
function reloadRoute($route) {
  $route.reload();
}

