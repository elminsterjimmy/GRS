angular.module('grsApp').controller('mainController', mainCtrl).controller('headerController', headerCtrl);

mainCtrl.$inject = [ 'userService', 'logger' ];
headerCtrl.$inject = [ 'metaService', 'logger' ];

function mainCtrl(userService, logger) {
  var vm = this;
  vm.user = [];
  
  activate();
  
  function activate() {
    return getUserInfo().then(function() {
      logger.info('Got user data.');
    });
  }

  function getUserInfo() {
    return userService.getUserInfo().then(function(data) {
      vm.user = data;
      return vm.user;
    });
  }
}

function headerCtrl(metaService, logger) {
  var vm = this;
  vm.meta = [];

  activate();

  function activate() {
    return getMeta().then(function() {
      logger.info('Got meta data.');
    });
  }

  function getMeta() {
    return metaService.getMetaData().then(function(data) {
      vm.meta = data;
      return vm.meta;
    });
  }
}