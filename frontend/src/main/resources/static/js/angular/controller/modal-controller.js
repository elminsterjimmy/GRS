angular.module('grsApp').controller('modalController', modalCtrl);

function modalCtrl($modal, URLConstants, logger) {

  var service = {
    open : open,
  };

  return service;

  function open(templateUrl, control, size) {
    $modal.open({
      animation: true,
      templateUrl: templateUrl,
      controller: control,
      controllerAs : 'ctrl',
      size: size
    });
  }
}