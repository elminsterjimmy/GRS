angular.module('grsApp').
  directive('includeReplace', includeReplaceDirective4NgInclude).
  directive('includeReplaceUiView', includeReplaceDirective4UIView).
  directive('recordAvailabilityValidator', recordAvailabilityValidator);

// fix the issue about new div created by angualar js.
function includeReplaceDirective4NgInclude() {
  return {
    require: 'ngInclude',
    restrict: 'A', /* optional */
    link: removeParent
  };
}

function includeReplaceDirective4UIView() {
  return {
    restrict: 'A', /* optional */
    link: removeParent
  };
}

function recordAvailabilityValidator(requestService) {
  return {
    require: 'ngModel',
    link: function (scope, element, attrs, ngModel) {
      var apiUrl = "http://localhost:8080" + attrs.recordAvailabilityValidator;

      function setAsLoading(bool) {
        ngModel.$setValidity('recordLoading', !bool);
      }

      function setAsAvailable(bool) {
        ngModel.$setValidity('recordAvailable', bool);
      }

      ngModel.$parsers.push(function (value) {
        if (!value || value.length == 0) return;

        setAsLoading(true);
        setAsAvailable(false);

        requestService.request(apiUrl, 'POST', {v : value}, function () {
          setAsLoading(false);
          setAsAvailable(true);
        }, function () {
          setAsLoading(false);
          setAsAvailable(false);
        });
        return value;
      })
    }
  }
}

function removeParent(scope, element, attrs) {
  element.replaceWith(element.children());
}