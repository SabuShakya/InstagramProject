(function () {
    angular.module("adminModule").controller("UserUploadsController", UserUploadsController);

    UserUploadsController.$inject = ['HttpService', '$localStorage','$log','$rootScope','$uibModal'];

    function UserUploadsController(HttpService, $localStorage,$log,$rootScope,$uibModal) {
        var vm = this;
        vm.uploadList = [];

        vm.showing = false;
        vm.showList = true;
        vm.fetching = true;
        vm.totalItems = '';
        vm.CurrentPage = 1;
        vm.maxSize = 2;
        vm.pageChanged = pageChanged;

        vm.getUploadsOfUser = getUploadsOfUser;
        vm.showComments = showComments;
        vm.openLikeListModal = openLikeListModal;

        getUploadsOfUser();

        function getUploadsOfUser() {
            vm.fetching = false;
            var URL = "/getUploadsOf/" + $localStorage.showUploadsOfUser + "?page=" + vm.CurrentPage + "&size=" + vm.maxSize;
            HttpService.get(URL).then(
                function (value) {
                    vm.uploadList = vm.uploadList.concat(value);
                    vm.totalItems = value[0].totalItems;
                    vm.fetching = true;
                }, function (reason) {
                    console.log(reason);
                });
        }

        function showComments(uploads) {
            if (vm.showing) {
                vm.showList = false;
                vm.showing = false;
            } else {
                vm.showing = true;
            }
        }

        function pageChanged() {
            if (vm.fetching) {
                $log.log("Page changed to:" + vm.CurrentPage);
                vm.totalPage = (vm.totalItems / vm.maxSize) + 1;
                if (vm.CurrentPage < vm.totalPage) {
                    vm.CurrentPage += 1;
                }
                getUploadsOfUser();
            }
        };

        function openLikeListModal(post) {
            $rootScope.imageName = post.image_path;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/likesList.jsp',
                controller: 'LikesListController',
                controllerAs: 'likesctrl',
                size: 'sm'
            });
        }
    }
})();
