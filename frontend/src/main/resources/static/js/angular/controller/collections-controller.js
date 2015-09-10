angular.
  module('grsApp').
    controller('collectionsController', [ "$scope", "$http", collectionsCtrl ]);

function collectionsCtrl() {
  var vm = this;
  vm.repeat = repeat;
  
  function repeat(n) {
    return new Array(n);
  }
}