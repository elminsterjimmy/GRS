angular.module('grsApp').factory('common', common);

function common(logger, URLConstants) {

  var service = {
    ajaxSuccess: ajaxSuccess,
    ajaxFailed: ajaxFailed,
    reloadState: reloadState,
    activedApp: URLConstants.dummy
  };

  return service;

  function reloadState($state, $stateParams) {
    $state.transitionTo($state.current.name, $stateParams, {
      reload: true,
      inherit: false,
      notify: true
    });
  }

  function ajaxSuccess(response) {
    return response;
  }

  function ajaxFailed(response, status) {
    logger.error('XHR Failed for ajax call.' + response);
    return response;
  }
}