angular.
  module('grsApp').factory('userService', userService);

userService.$inject = ['$http', '$cookieStore', 'logger', 'common', 'URLConstants'];

function userService($http, $cookieStore, logger, common, URLConstants) {
  logger.info("init user service.");
  
  var service = {
    getUserInfo : getUserInfo,
    login : login
  };
  return service;

  function getUserInfo() {
    return $http.get(URLConstants.dummyUrl + '/json/dummy/user.json')
            .then(common.ajaxSuccess)
            .catch(common.ajaxFailed);
  }

  function login(user) {
    var crsfToken = $cookieStore.get("CSRF-TOKEN");
    user._ = (new Date()).getTime();
    $http({
      method: 'POST',
      url: URLConstants.testAppUrl + '/user/login',
      headers: {
        'X-CSRF-TOKEN': crsfToken,
        'Content-Type': 'application/json'
      },
      data : user
    }).then(common.ajaxSuccess)
      .catch(common.ajaxFailed);;
  }

}