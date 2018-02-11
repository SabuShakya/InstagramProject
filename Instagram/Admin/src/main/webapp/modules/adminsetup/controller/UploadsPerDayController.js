(function () {
    angular.module('adminModule').controller('UploadsPerDayController',UploadsPerDayController);

    UploadsPerDayController.$inject =['HttpService','$rootScope','$uibModal','$localStorage'];

    function UploadsPerDayController(HttpService,$rootScope,$uibModal,$localStorage) {
        var vm = this;
        vm.uploadList=[];
        vm.commentList = [];
        vm.getUploadsOfDay = getUploadsOfDay;
        vm.showComments = showComments;
        vm.openLikeListModal=openLikeListModal;

        getUploadsOfDay();

        function getUploadsOfDay() {
            // var URL = "/"+$localStorage.showUploadsOfUser+"?page="+vm.CurrentPage+"&size="+vm.maxSize;
            // var URL ="/getUploadsPerDay";
            HttpService.get("/getUploadsPerDay").then(
                function (value) {
                    vm.uploadList = value;
                    // vm.totalItems = value[0].totalItems;
                }, function (reason) {
                    console.log("UploadsperDayError: "+reason);
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
        function openLikeListModal(post) {
            $rootScope.imageName = post.image_path;
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/likesList.jsp',
                controller :'LikesListController',
                controllerAs: 'likesctrl',
                size: 'lg'
            });
        }

        // function pageChanged() {
        //     $log.log("Page changed to:"+vm.CurrentPage);
        //     if (vm.CurrentPage < vm.totalItems) {
        //         vm.CurrentPage += 1;
        //     }
        //     getUploadsOfDay();
        // };
    }
})();