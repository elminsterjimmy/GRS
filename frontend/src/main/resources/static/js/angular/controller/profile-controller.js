angular.module('grsApp').controller('profileController', profileCtrl);

function profileCtrl(profileService, logger) {
  var vm = this;
  vm.default = {};
  vm.items = [];

  activate();

  function activate() {

    vm.selectedyear = 2005;
    vm.selectedmonth = 'January';
    vm.selectedday = 4;

    vm.default.years = [];
    var now = new Date();

    for (var i = 1900; i <= now.getFullYear(); i++) {
      vm.default.years.push(i);
    }

    vm.default.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    vm.default.days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];

    vm.updateDay = function() {
      // TODO
      //if (vm.selectedmonth) {
      //  vm.default.days = [];
      //  for (var i = 1; i < daysInMonth(vm.selectedyear, vm.selectedmonth); i++) {
      //    vm.default.days.push(i);
      //  }
      //}
    }
  }

  function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
  }

  return this;
}
