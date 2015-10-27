angular.
  module('grsApp').factory('authService', authService);

function authService($cookies, $state, modalController, logger) {
  logger.info("init user service.");

  var service = {
    authSuccess : authSuccess,
    authTimeout : authTimeout
  };
  return service;

  function authSuccess(data) {
    var csrfToken = data.headers('X-CSRF-TOKEN');
    var authToken = data.headers('X-AUTH-TOKEN');
    $cookies.put("USER-TOKEN", authToken);
    $state.transitionTo("index", $stateParams, {
      reload: true,
      inherit: false,
      notify: true
    });
  }

  function authTimeout(modalController) {
    // open login dialog
    modalController.open('./tpl/dialog/login.html', 'userController');
    // TODO set error message to session timeout, please login again.
  }

}