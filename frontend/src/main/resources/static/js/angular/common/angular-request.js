angular.
  module('grsApp').config(enableCORS).config(enableWhiteList).factory('requestService', requestService);

// https://github.com/angular/angular.js/pull/1454
function enableCORS($httpProvider) {
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}

function enableWhiteList($sceDelegateProvider) {
  $sceDelegateProvider.resourceUrlWhitelist(['self', 'http://localhost:8080/**']);
}

function requestService($http, $cookies, logger, common) {
  logger.info("init user service.");

  var service = {
    request: request
  };
  return service;

  // the function to add CSRF token and additional request parameters automatically.
  function request(url, method, data, successCallback, failedCallback) {
    if (undefined === method) {
      method = 'GET';
    }
    if (undefined === data) {
      data = {};
    }
    if (undefined === successCallback) {
      successCallback = common.ajaxSuccess;
    }
    if (undefined === failedCallback) {
      failedCallback = common.ajaxFailed;
    }
    var crsfToken = $cookies.get("CSRF-TOKEN");
    var jsessionId = $cookies.get('JSESSIONID');
    var userToken = $cookies.get('USER-TOKEN');
    data._ = (new Date()).getTime();
    return $http({
      method: method,
      url: url,
      headers: {
        'X-CSRF-TOKEN': crsfToken,
        'X-AUTH-TOKEN': userToken,
        'Content-Type': 'application/json'
      },
      data : data,
      withCredentials: true
    }).then(successCallback)
      .catch(failedCallback);
  }


}