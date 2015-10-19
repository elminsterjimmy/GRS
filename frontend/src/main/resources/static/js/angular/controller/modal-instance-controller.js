angular.module('grsApp').controller('modalInstanceController', modalInstanceCtrl);

modalInstanceCtrl.$inject = ['$modalInstance', 'userService', 'logger' ];

function modalInstanceCtrl($modalInstance, userService, logger) {

  var vm = this;

  var service = {
    login : login,
    cancel : cancel
  };

  return service;

  function login(user) {

    userService.login(user);
    $modalInstance.close("login");
  }

  function cancel() {
    $modalInstance.close("cancel");
  }
}

