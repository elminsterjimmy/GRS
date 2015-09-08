angular.
  module('grsApp').
    controller('mainController', [ "$scope", "$http", mainCtrl ]).
    controller('headerController', [ "$scope", "$http", headerCtrl ]);

function mainCtrl() {
  
}

function headerCtrl() {
  var vm = this;
  vm.title = "title";
}