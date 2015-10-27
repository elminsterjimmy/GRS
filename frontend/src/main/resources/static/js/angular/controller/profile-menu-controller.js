angular.module('grsApp').controller('ProfileMenuController', profileMenuCtrl);

function profileMenuCtrl($rootScope, $cookies, EventConstants, logger) {

  var vm = this;
  vm.logout = logout;

  function logout() {
    logger.info("logout");
    $cookies.remove("USER-TOKEN");
    $rootScope.$broadcast(EventConstants.authUnavailableEvent);
  }
}

