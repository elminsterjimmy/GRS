angular.
  module('grsApp').factory('userService', userService);

userService.$inject = ['$http', 'logger', 'common'];

function userService($http, logger, common) {
  logger.info("init user service.");
  
  var service = {
      getUserInfo : getUserInfo
  };
  return service;

  function getUserInfo() {
    return $http.get('/json/dummy/user.json')
            .then(common.ajaxSuccess)
            .catch(common.ajaxFailed);
  }
}