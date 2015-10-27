angular.module('grsApp').factory('common', common);

common.$inject = ['logger'];

function common(logger) {

  var service = {
    ajaxSuccess: ajaxSuccess,
    ajaxFailed: ajaxFailed
  };
  return service;

  function ajaxSuccess(response) {
    return response;
  }

  function ajaxFailed(response, status) {
    logger.error('XHR Failed for ajax call.' + response);
    return response;
  }
}