angular.
  module('grsApp').factory('metaService', metaService);

function metaService(requestService, logger, URLConstants) {
  
  logger.info("init common service.");
  
  var service = {
      getMetaData : getMetaData
  };
  return service;

  function getMetaData() {
    return requestService.request(URLConstants.dummy.BaseUrl + URLConstants.dummy.Service.Meta.Url);
  }
}