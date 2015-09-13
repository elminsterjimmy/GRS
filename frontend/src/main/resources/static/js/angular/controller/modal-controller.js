angular.module('grsApp').controller('modalController', modalCtrl);

modalCtrl.$inject = [ '$modal', 'logger' ];

function modalCtrl($modal, logger) {
  var vm = this;
  
  vm.open = open;
  
  function open(templateUrl, size) {
    $modal.open({
      animation: true,
      templateUrl: templateUrl,
      size: size,
    });
  }
}