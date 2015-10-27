angular.module('grsApp').controller('userController', userCtrl);

function userCtrl($rootScope, $scope, $modalInstance, $cookies, $state, $stateParams, EventConstants, userService, logger) {

  var vm = this;
  vm.login = login;

  function login(form) {
    vm.submitted = true;
    $scope.loginForm.retour.$setValidity("server", true);
    if(form.$valid) {
      userService.login(vm.user).then(function(data) {
        if (!data.status) {
          // server offline
        }
        if (data.status == 202) {
          $modalInstance.close("login");
          var csrfToken = data.headers('X-CSRF-TOKEN');
          var authToken = data.headers('X-AUTH-TOKEN');
          $cookies.put("USER-TOKEN", authToken);
          $rootScope.$broadcast(EventConstants.authAvailableEvent);
          $state.transitionTo("index", $stateParams, {
            reload: true,
            inherit: false,
            notify: true
          });
        } else if (data.status == 401) {
          logger.info("401 error");
          $scope.loginForm.retour.$setValidity("server", false);
          vm.error = data.data.errors[0].localizedErrorMessage;
        } else {
          logger.info("other error");
          $scope.loginForm.retour.$setValidity("server", false);
          vm.error = 'server unavailable!';
        }
      }).catch( function(err) {
        vm.error = err;
      });
    }
  }

  function cancel() {
    $modalInstance.close("cancel");
  }
}

