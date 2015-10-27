angular.module('grsApp').controller('collectionsController', collectionsCtrl);

function collectionsCtrl(collectionsService, logger) {
  var vm = this;
  vm.items = [];

  activate();

  function activate() {
    return getCollections().then(function() {
      logger.info('Retrieved collections');
    });
  }

  function getCollections() {
    return collectionsService.getAllCollectionList().then(function(data) {
      vm.items = data;
      return vm.items;
    });
  }
}