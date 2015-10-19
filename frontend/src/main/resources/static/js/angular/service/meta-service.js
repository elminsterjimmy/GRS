angular.
  module('grsApp').factory('metaService', metaService);

metaService.$inject = ['$http', 'logger', 'common', 'URLConstants'];

function metaService($http, logger, common, URLConstants) {
  
  logger.info("init common service.");
  
  var service = {
      getMetaData : getMetaData
  };
  return service;

  function getMetaData() {
    return $http.get(URLConstants.dummyUrl + '/json/dummy/meta.json')
            .then(common.ajaxSuccess)
            .catch(common.ajaxFailed);
  }
}