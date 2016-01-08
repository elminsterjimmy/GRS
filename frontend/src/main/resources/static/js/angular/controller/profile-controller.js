angular.module('grsApp').controller('profileController', profileCtrl);

function profileCtrl(profileService, logger) {
  var vm = this;
  vm.default = {};

  activate();

  function activate() {
    getCurrentUserProfile();
    getAllLocations();
    initControls();
    initFilters();
  }

  function getCurrentUserProfile() {
    return profileService.getCurrentUserProfile().then(function (response) {
      if (response.status == 200) {
        vm.data = response.data.data;
        var birthday = vm.data['birthday'];
        if (birthday) {
          var birthdayDate = new Date(birthday)
          vm.selectedyear = birthdayDate.getFullYear();
          vm.selectedmonth = birthdayDate.getMonth();
          vm.selectedday = birthdayDate.getDate();
        }
      } else if (response.status == 403) {
        // TODO auth timeout broadcast
      } else {
        // TODO server unavailable
      }
    });
  }

  function getAllLocations() {
    return profileService.getAllLocations().then(function (response) {
      if (response.status == 200) {
        vm.default.locations = response.data;
      }
    });
  }

  function initFilters() {
    vm.filterLocationLevel1 = function(locations) {
      return locations.level === 1;
    }
    vm.filterLocationLevel2 = function(locations) {
      return locations.parent_id === vm.data.livePlaceLv1;
    }
    vm.filterLocationLevel3 = function(locations) {
      return locations.parent_id === vm.data.livePlaceLv2;
    }
  }


  function initControls() {
    initDateDropdown();
  }

  function initDateDropdown() {
    vm.default.years = [];
    var now = new Date();

    for (var i = 1900; i <= now.getFullYear(); i++) {
      vm.default.years.push(i);
    }

    vm.default.months = [
      {
        "value": 0,
        "text": 'January'
      },
      {
        "value": 1,
        "text": 'February'
      },
      {
        "value": 2,
        "text": 'March'
      },
      {
        "value": 3,
        "text": 'April'
      },
      {
        "value": 4,
        "text": 'May'
      },
      {
        "value": 5,
        "text": 'June'
      },
      {
        "value": 6,
        "text": 'July'
      },
      {
        "value": 7,
        "text": 'August'
      },
      {
        "value": 8,
        "text": 'September'
      },
      {
        "value": 9,
        "text": 'October'
      },
      {
        "value": 10,
        "text": 'November'
      },
      {
        "value": 11,
        "text": 'December'
      }];
    vm.default.days = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31];
  }

  function daysInMonth(year, month) {
    return new Date(year, month, 0).getDate();
  }

  function updateDay() {
    // TODO
    //if (vm.selectedmonth) {
    //  vm.default.days = [];
    //  for (var i = 1; i < daysInMonth(vm.selectedyear, vm.selectedmonth); i++) {
    //    vm.default.days.push(i);
    //  }
    //}
  }

  return this;
}
