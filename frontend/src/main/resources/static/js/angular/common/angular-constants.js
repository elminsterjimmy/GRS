/**
 * Created by jgu on 9/28/2015.
 */
angular.module('grsApp').
  constant('URLConstants', {
    "dummyUrl" : "/frontend/src/main/resources/static/",
    "testAppUrl" : "http://localhost:8080/v1.0"
  })
  .constant('EventConstants', {
    "authAvailableEvent" : 'authAvailableEvent',
    "authUnavailableEvent" : 'authUnavailableEvent',
    "serverUnavailableEvent" : 'serverUnavailableEvent'
  });