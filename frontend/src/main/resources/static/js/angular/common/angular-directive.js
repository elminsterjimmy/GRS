angular.module('grsApp').
  directive('includeReplace', includeReplaceDirective4NgInclude).
  directive('includeReplaceUiView', includeReplaceDirective4UIView);

// fix the issue about new div created by angualar js.
function includeReplaceDirective4NgInclude() {
  return {
    require : 'ngInclude',
    restrict : 'A', /* optional */
    link : removeParent
  };
}

function includeReplaceDirective4UIView() {
  return {
    restrict : 'A', /* optional */
    link : removeParent
  };
}

function removeParent(scope, el, attrs) {
  el.replaceWith(el.children());
}