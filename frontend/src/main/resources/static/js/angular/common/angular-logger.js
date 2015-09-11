angular.module('grsApp').factory('logger', logger);

function logger() {
  
  var service = {
      info : info,
      warn : warn,
      error : error
  };
  return service;
  
  function info(msg) {
    console.info(msg);
  }
  
  function warn(msg) {
    console.warn(msg);
  }
  
  function error(msg) {
    console.error(msg);
  }
}