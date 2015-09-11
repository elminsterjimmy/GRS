angular.
  module('grsApp').factory('collectionsService', collectionService);

collectionService.$inject = ['$http', 'logger'];

function collectionService($http, logger) {
  
  logger.info("init collection service.");
  
  var service = {
      getAllCollectionList: getAllCollectionList,
      markRating: markRating,
      markFavorite: markFavorite
  };
  return service;

  function getAllCollectionList() {
    console.log("getAllCollectionList");
  }
  
  function markRating() {
    console.log("markRating");
  }
  
  function markFavorite() {
    console.log("markFavorite");
  }
}