angular.module('grsApp').factory('common', common);

common.$inject = [ 'logger' ];

function common(logger) {
  
  var service = {
      ajaxSuccess : ajaxSuccess,
      ajaxFailed : ajaxFailed
  };
  return service;
  
  function ajaxSuccess(response) {
    return response.data;
  }
  
  function ajaxFailed(error) {
    logger.error('XHR Failed for ajax call.' + error.data);
  }
}