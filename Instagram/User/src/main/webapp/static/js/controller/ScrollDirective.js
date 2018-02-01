(function () {
    angular.module('userModule').directive('infinityscroll', infinityscroll);
    infinityscroll.$inject =['$http', '$q','$rootScope'];
    function infinityscroll($http, $q, $rootScope) {
        var vm = this;
        // return {
        //     restrict: 'A',
        //     link: function (scope, element, attrs) {
        //         element.bind('scroll', function () {
        //             if ((element[0].scrollTop + element[0].offsetHeight) == element[0].scrollHeight) {
        //                 //scroll reach to end
        //                 scope.$apply(attrs.infinityscroll)
        //             }
        //         });
        //     }
        // }

        return{
            restrict: 'A',
            link: function(scope, elem, attrs){
                raw = elem[0];
                elem.bind("scroll", function(){
                    if(raw.scrollTop+raw.offsetHeight+5 >= raw.scrollHeight){
                        scope.loading = true;
                        scope.$apply(attrs.infinityscroll);
                    }
                });
            }
        }
    }
});