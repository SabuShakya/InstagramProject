(function () {
    angular.module('adminModule').controller('UploadsPerDayController',UploadsPerDayController);

    UploadsPerDayController.$inject =['HttpService'];

    function UploadsPerDayController(HttpService) {
        var vm = this;
        vm.uploadList=[];
        vm.commentList = [];
        vm.getUploadsOfDay = getUploadsOfDay;
        vm.showComments = showComments;
        vm.openLikeListModal=openLikeListModal;

        getUploadsOfDay();

        function getUploadsOfDay() {
            // var URL = "/"+$localStorage.showUploadsOfUser+"?page="+vm.CurrentPage+"&size="+vm.maxSize;
            var URL ="/getUploadsOfDay";
            HttpService.get(URL).then(
                function (value) {
                    vm.uploadList = value;
                    // vm.totalItems = value[0].totalItems;
                }, function (reason) {
                    console.log(reason);
                });
        }

        function showComments(uploads) {
            HttpService.get("/getCommentsOfThisPicture/" + uploads.image_path).then(
                function (value) {
                    if (vm.showing) {
                        vm.showList = false;
                        vm.showing = false;
                    } else {
                        vm.commentList = value;
                        vm.showing = true;
                    }
                }, function (reason) {
                    console.log(reason);
                });
        }
        function openLikeListModal(post) {
            $rootScope.imageName = post.image_path;
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/likesList.jsp',
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