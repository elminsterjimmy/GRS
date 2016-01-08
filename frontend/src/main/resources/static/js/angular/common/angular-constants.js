/**
 * Created by jgu on 9/28/2015.
 */
angular.module('grsApp').
  constant('URLConstants', {
    "dummy" : {
      "BaseUrl" : "/frontend/src/main/resources/static",
      "Service" : {
        "Meta" : {
          "Url" : "/json/dummy/meta.json",
          "Method" : "GET"
        },
        "BaseUserInfo" : {
          "Url" : "/json/dummy/user.json",
          "Method" : "GET"
        },
        "Login" : {
          "Url" : "/json/dummy/login.json",
          "Method" : "GET"
        },
        "Register" : {
          "Url" : "/json/dummy/register.json",
          "Method" : "GET"
        },
        "Collection" : {
          "Url" : "/json/dummy/collections.json",
          "Method" : "GET"
        },
        "Profile" : {
          "Url" : "/json/dummy/profile.json",
          "Method" : "GET"
        },
      }
    },
    "appUrl" : {
      "BaseUrl" : "http://localhost:8080/v1.0",
      "Service" : {
        "BaseUserInfo" : {
          "Url" : "/user/current",
          "Method" : "GET"
        },
        "Login" : {
          "Url" : "/user/login",
          "Method" : "POST"
        },
        "Register" : {
          "Url" : "/user",
          "Method" : "POST"
        },
        "Profile" : {
          "Url" : "/user/current/profile",
          "Method" : "GET"
        },
      }
    },
  })
  .constant('EventConstants', {
    "authAvailableEvent" : 'authAvailableEvent',
    "authUnavailableEvent" : 'authUnavailableEvent',
    "serverUnavailableEvent" : 'serverUnavailableEvent'
  });
