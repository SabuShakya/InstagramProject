(function() {
    angular.module('userModule').controller("mainController", mainController);
    mainController.$inject= ['HttpService','$localStorage','$rootScope','$uibModal','$log'];

    function mainController(HttpService,$localStorage,$rootScope,$uibModal, $log) {
        var vm = this;
        vm.posts = [];
        vm.fetching = false;
        vm.totalItems = '';
        vm.CurrentPage =1;
        vm.maxSize = 3;
        vm.pageChanged= pageChanged;
        vm.getPosts=getPosts;
        getPosts();

        function getPosts() {
           // vm.currentPage++;
            // vm.fetching=true;
            var URL = "/getPosts/"+$localStorage.storedObj.username+"?page="+vm.CurrentPage+"&size="+vm.maxSize;
            HttpService.get(URL).then(
                function (value) {
                    vm.posts = vm.posts.concat(value);
                    vm.totalItems=value[0].totalItems;
                    vm.fetching=false;
                }, function (reason) {
                    vm.message = "Follow Others to see their posts.";
                });
        }

        function pageChanged() {
            $log.log("Page changed to:"+vm.CurrentPage);
            // getPosts();
            if (vm.CurrentPage < vm.totalItems) {
                vm.CurrentPage += 1;
                getPosts();
            }
        };

    }

})();

