(function () {
    angular.module('adminModule').controller('MainController',MainController);

    MainController.$inject=['breadcrumbs','$scope'];

    function MainController(breadcrumbs,$scope) {
        $scope.breadcrumbs = breadcrumbs;
    }
})();