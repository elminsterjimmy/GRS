angular.
  module('grsApp').factory('collectionsService', collectionService);

collectionService.$inject = ['$http', 'logger', 'common'];

function collectionService($http, logger, common) {
  
  logger.info("init collection service.");
  
  var service = {
      getAllCollectionList: getAllCollectionList,
      markRating: markRating,
      markFavorite: markFavorite
  };
  return service;

  function getAllCollectionList(user) {
    logger.info("getAllCollectionList");
    return $http.get('/json/dummy/collections.json')
      .then(common.ajaxSuccess)
      .catch(common.ajaxFailed);
  }
  
  function markRating(user, item) {
    logger.info("markRating");
  }
  
  function markFavorite(user, item) {
    logger.info("markFavorite");
  }
}