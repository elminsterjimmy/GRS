angular.module('grsApp', [ 'ui.router', 'oc.lazyLoad' ]).config(routeConfig).run(['$state', reloadState]);

function routeConfig($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/');
  
  $stateProvider.
    state('index', {
      url : "",
      views: {
        'contentMain' : { templateUrl : 'tpl/common/empty.html'},
        'contentFooter' : { templateUrl : 'tpl/common/defaultFooter.html'}
      }
    }).
    state('collections', {
      url : "/collections",
      views: {
        "contentMain" : {
          templateUrl : 'tpl/view/collections.html',
          controller : 'collectionsController',
          controllerAs : 'collections'
        },
        "contentFooter" : { templateUrl : 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function($ocLazyLoad) {
          // you can lazy load files for an existing module
          console.log('load');
          return $ocLazyLoad.load('js/angular/controller/collections-controller.js');
        }]
      }
  //    controller : 'collectionsController',
  //    controllerAs : 'collections',
    });
  
}

// fix the bug for ui-view in ng-include (https://github.com/angular-ui/ui-router/issues/679)
function reloadState($state) {
  
}

// fix the bug for the nest include and view (https://github.com/angular/angular.js/issues/1213)
//function reloadRoute($route) {
//  $route.reload();
//}

