angular.
  module('grsApp').factory('userService', userService);

function userService(requestService, URLConstants, logger) {
  logger.info("init user service.");
  
  var service = {
    getUserInfo : getUserInfo,
    login : login,
    register : register
  };
  return service;

  function getUserInfo() {
    return requestService.request(URLConstants.testAppUrl + '/user/current');
  }

  function login(user) {
    return requestService.request(URLConstants.testAppUrl + '/user/login', 'POST', user);
  }

  function register(user) {
    return requestService.request(URLCOnstants.testAppUrl + '/user', 'POST', user);
  }
}