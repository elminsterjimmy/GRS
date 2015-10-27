angular.
  module('grsApp').factory('collectionsService', collectionService);

function collectionService(requestService, logger, URLConstants) {
  logger.info("init collection service.");
  
  var service = {
      getAllCollectionList: getAllCollectionList,
      markRating: markRating,
      markFavorite: markFavorite
  };
  return service;

  function getAllCollectionList(user) {
    logger.info("getAllCollectionList");
    return requestService.request(URLConstants.dummyUrl + '/json/dummy/collections.json');
  }
  
  function markRating(user, item) {
    logger.info("markRating");
  }
  
  function markFavorite(user, item) {
    logger.info("markFavorite");
  }
}