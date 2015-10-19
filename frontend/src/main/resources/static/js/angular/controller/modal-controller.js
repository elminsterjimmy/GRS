angular.module('grsApp').controller('modalController', modalCtrl);

modalCtrl.$inject = [ '$modal', 'URLConstants', 'logger' ];

function modalCtrl($modal, URLConstants, logger) {

  var service = {
    open : open,
  };

  return service;

  function open(templateUrl, size) {
    $modal.open({
      animation: true,
      templateUrl: URLConstants.dummyUrl + templateUrl,
      controller: 'modalInstanceController',
      controllerAs : 'ctrl',
      size: size
    });
  }
}