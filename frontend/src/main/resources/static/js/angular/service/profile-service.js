angular.module('grsApp').factory('profileService', profileService);

function profileService(requestService, common, logger, URLConstants) {
  logger.info("init profile service.");

  var service = {
    "getCurrentUserProfile" : getCurrentUserProfile,
    "getAllLocations" : getAllLocations,
    "getUserProfile" : getUserProfile,
    "updateUserProfile" : updateUserProfile,
  };

  function getCurrentUserProfile() {
    var service = common.activedApp.Service.Profile;
    return requestService.request(common.activedApp.BaseUrl + service.Url, service.Method);
  }

  function getUserProfile() {
  }

  function updateUserProfile() {

  }

  function getAllLocations() {
    return requestService.request("/frontend/src/main/resources/static/json/static/locations.json");
  }

  return service;
}