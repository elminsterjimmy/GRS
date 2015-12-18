angular.module('grsApp').config(routeConfig).run(['$state', reloadState]);//.run(checkAuthentication);

function routeConfig($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/');
  $stateProvider.
    state('index', {
      url: "",
      views: {
        'contentMain': {templateUrl: 'tpl/common/empty.html'},
        'contentFooter': {templateUrl: 'tpl/common/defaultFooter.html'}
      }
    }).
    state('collections', {
      url: "/collections",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/collections.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('news', {
      url: "/news",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/news.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('badges', {
      url: "/badges",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/badges.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('settings', {
      url: "/settings",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/settings.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('statistics', {
      url: "/statistics",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/statistics.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('trophies', {
      url: "/trophies",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/trophies.html',
          controller: 'collectionsController',
          controllerAs: 'collections'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/collections-service.js',
              'js/angular/controller/collections-controller.js']
          });
        }]
      }
    }).
    state('profile', {
      url: "/profile",
      views: {
        "contentMain": {
          templateUrl: 'tpl/view/profile.html',
          controller: 'profileController',
          controllerAs: 'profile'
        },
        "contentFooter": {templateUrl: 'tpl/common/defaultFooter.html'}
      },
      resolve: { // Any property in resolve should return a promise and is executed before the view is loaded
        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
          // you can lazy load files for an existing module
          return $ocLazyLoad.load({
            serie: true,
            files: [
              'js/angular/service/profile-service.js',
              'js/angular/controller/profile-controller.js']
          });
        }]
      }
    })
}

// fix the bug for ui-view in ng-include (https://github.com/angular-ui/ui-router/issues/679)
function reloadState($state) {

}

//function checkAuthentication($rootScope, $location, $state, EventConstants, userService) {
//  $rootScope.$on('$stateChangeStart', function (e, toState, toParams, formState, fromParams) {
//    userService.getUserInfo().then(function(response) {
//      if (200 === response.status) {
//        $rootScope.loggedIn = true;
//        $rootScope.$broadcast(EventConstants.authAvailableEvent);
//      } else {
//        $rootScope.$broadcast(EventConstants.authUnavailableEvent);
//        $rootScope.loggedIn = false;
//        if ("index" != toState) {
//          e.preventDefault();
//          $state.go('index');
//        }
//      }
//    });
//  });
//}

// fix the bug for the nest include and view (https://github.com/angular/angular.js/issues/1213)
function reloadRoute($route) {
  $route.reload();
}

