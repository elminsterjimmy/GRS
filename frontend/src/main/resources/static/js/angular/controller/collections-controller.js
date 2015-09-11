angular.
  module('grsApp').
    controller('collectionsController', collectionsCtrl);

collectionsCtrl.$inject = ['collectionsService', 'logger'];

function collectionsCtrl(collectionsService, logger) {
  var vm = this;
  vm.repeat = repeat;
  
  vm.rate = 3;
  
  function repeat(n) {
    return new Array(n);
  }
}