angular.module('grsApp').controller('mainController', mainCtrl).controller('headerController', headerCtrl);

mainCtrl.$inject = [ 'commonService', 'logger' ];
headerCtrl.$inject = [ 'commonService', 'logger' ];

function mainCtrl(commonService, logger) {

}

function headerCtrl(commonService, logger) {
  var vm = this;

  vm.meta = [];

  activate();

  function activate() {
    return getMeta().then(function() {
      logger.info('Got meta data.');
    });
  }

  function getMeta() {
    return commonService.getMetaData().then(function(data) {
      vm.meta = data;
      return vm.meta;
    });
  }
}