(function() {
    angular.module('userModule').controller("mainController", mainController);
    mainController.$inject= ['HttpService','$localStorage','$rootScope','$uibModal','$log'];

    function mainController(HttpService,$localStorage,$rootScope,$uibModal, $log) {
        var vm = this;
        vm.posts = [];
        vm.fetching = true;
        vm.totalItems = '';
        vm.CurrentPage = 1;
        vm.maxSize = 8;
        vm.pageChanged = pageChanged;
        vm.getPosts = getPosts;
        getPosts();

        function getPosts() {
            // vm.currentPage++;
            vm.fetching = false;
            var URL = "/getPosts/" + $localStorage.storedObj.username + "?page=" + vm.CurrentPage + "&size=" + vm.maxSize;
            HttpService.get(URL).then(
                function (value) {
                    vm.posts = vm.posts.concat(value);
                    vm.totalItems = value[0].totalItems;
                    vm.fetching = true;
                }, function (reason) {
                    vm.message = "Follow Others to see their posts.";
                });
        }

        function pageChanged() {
            if (vm.fetching) {
                vm.totalPage = (vm.totalItems / vm.maxSize) + 1;
                $log.log("Page changed to:" + vm.CurrentPage);

                if (vm.CurrentPage < vm.totalPage) {
                    vm.CurrentPage += 1;
                    getPosts();

                }
            };
        }
    }

})();

