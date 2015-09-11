angular.
  module('grsApp').factory('commonService', commonService);

commonService.$inject = ['$http', 'logger'];

function commonService($http, logger) {
  
  logger.info("init common service.");
  
  var service = {
      getMetaData : getMetaData
  };
  return service;

  function getMetaData() {
    return $http.get('/json/dummy/meta.json')
            .then(getMetaDataComplete)
            .catch(getMetaDataFailed);

    function getMetaDataComplete(response) {
        return response.data;
    }

    function getMetaDataFailed(error) {
        logger.error('XHR Failed for getMeta.' + error.data);
    }
  }
}