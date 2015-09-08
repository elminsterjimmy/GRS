angular.module('grsApp').directive('includeReplace', includeReplaceDirective);

// fix the issue about new div created by angualar js.
function includeReplaceDirective() {
  return {
    require : 'ngInclude',
    restrict : 'A', /* optional */
    link : function(scope, el, attrs) {
      el.replaceWith(el.children());
    }
  };
}