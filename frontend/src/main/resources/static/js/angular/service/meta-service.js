angular.
  module('grsApp').factory('metaService', metaService);

metaService.$inject = ['$http', 'logger', 'common'];

function metaService($http, logger, common) {
  
  logger.info("init common service.");
  
  var service = {
      getMetaData : getMetaData
  };
  return service;

  function getMetaData() {
    return $http.get('/json/dummy/meta.json')
            .then(common.ajaxSuccess)
            .catch(common.ajaxFailed);
  }
}