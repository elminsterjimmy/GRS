angular.
  module('grsApp').factory('userService', userService);

userService.$inject = ['$http', 'logger', 'common', 'URLConstants'];

function userService($http, logger, common, URLConstants) {
  logger.info("init user service.");
  
  var service = {
      getUserInfo : getUserInfo
  };
  return service;

  function getUserInfo() {
    return $http.get(URLConstants.appUrl + '/json/dummy/user.json')
            .then(common.ajaxSuccess)
            .catch(common.ajaxFailed);
  }
}