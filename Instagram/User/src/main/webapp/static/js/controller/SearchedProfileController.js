(function(){
    angular.module('userModule').controller('SearchedProfileController', SearchedProfileController);
    SearchedProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location'];

    function SearchedProfileController($uibModal, $rootScope, HttpService, $localStorage, $location){
        var vm =this;
        vm.photoList=[];
        $rootScope.message='';
        $rootScope.saved = false;
        $rootScope.photo = '';
        vm.url = "/allPhotos/" + $localStorage.openProfileOf.username;
        vm.userDisplayName = $localStorage.openProfileOf.username;
        vm.showFollowBtn = true;
        vm.followObj={
            userName : $localStorage.storedObj.username,
            following_userName : $localStorage.openProfileOf.username
        };
        vm.openModal=openModal;
        vm.commentModal=commentModal;
        vm.followUser = followUser;
        vm.unfollowUser = unfollowUser;

        HttpService.get(vm.url).then(function(value){
            vm.photoList = value;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

        HttpService.post("/checkFollow",vm.followObj).then(function (value) {
            vm.showFollowBtn = false;
        },function (reason) {
            vm.showFollowBtn = true;
        });

        function openModal(){
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/upload.jsp',
                controller :'ImgUploadController',
                controllerAs: 'img',
                size: 'lg'
            });
        }

        function commentModal(image_path,caption) {
            $rootScope.photo = image_path;
            $rootScope.caption =caption;

            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/comments.jsp',
                controller :'CommentsController',
                controllerAs: 'comment',
                size: 'lg'
            });
        }
        function followUser() {
            HttpService.post("/followUser",vm.followObj).then(function (value) {
                vm.showFollowBtn = false;
            },function (reason) {
                vm.showFollowBtn = true;
                console.log("error following"+reason);
            });
        }
        function unfollowUser() {
            HttpService.post("/unfollowUser",vm.followObj).then(function (value) {
                vm.showFollowBtn = true;
            },function (reason) {
                vm.showFollowBtn = false;
                console.log("error following"+reason);
            });
        }
    }
})();