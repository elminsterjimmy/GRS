angular.module('grsApp').controller('headerController', headerCtrl);

function headerCtrl($rootScope, $scope, userService, EventConstants, logger) {

  var vm = this;
  vm.user = {};

  getHeaderInfo();

  $scope.$on(EventConstants.authAvailableEvent, getHeaderInfo);

  function getHeaderInfo() {
    return userService.getUserInfo().then(function(response) {
      if (response.status == 200) {
        vm.user = response.data.data;
      } else if (response.status == 403) {
        // TODO auth timeout broadcase
      } else {
        // TODO server unavailable
      }
    });
  }
  return this;
}

