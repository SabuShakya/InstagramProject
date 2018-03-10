(function () {
    angular.module('adminModule').controller('ProfileController', ProfileController);
    ProfileController.$inject = [
        '$uibModal',
        '$rootScope',
        'HttpService',
        '$localStorage',
        '$location',
        '$interval'];

    function ProfileController($uibModal, $rootScope, HttpService, $localStorage, $location, $interval) {
        var vm = this;
        vm.userDisplayName = '';
        vm.photoList = [];
        vm.profilePhotoList = [];
        vm.followers = '';
        vm.following = '';
        vm.totalPictures = '';
        $rootScope.message = '';
        $rootScope.saved = false;
        $rootScope.photo = '';
        $rootScope.clickedPhoto = '';
        $rootScope.pic = '';

        vm.showFollowersList= false;
        vm.showFollowingList= false;

        vm.userDisplayName = $localStorage.openProfileOf.username;

        vm.commentModal = commentModal;
        vm.allPhotos = allPhotos;
        vm.followersCount=followersCount;
        vm.followingCount=followingCount;
        vm.profilePhoto = profilePhoto;
        vm.followersList = followersList;
        vm.followingList = followingList;

        allPhotos();
        profilePhoto();
        followersCount();
        followingCount();

        function followersCount(){
            HttpService.get("/followersCount/"+$localStorage.openProfileOf.username).then(function (value) {
                vm.followers = value.followers;
                vm.totalPictures = value.totalPictures;
                vm.showFollowersList= false;
            },function (reason) {
                vm.followers = reason.followers;
                vm.totalPictures = reason.totalPictures;
                vm.showFollowersList= true;
            });
        }

        function followingCount(){
            HttpService.get("/followingCount/"+$localStorage.openProfileOf.username).then(function (value) {
                vm.following = value.following;
                vm.showFollowingList= false;
            },function (reason) {
                vm.following = reason.following;
                vm.showFollowingList= true;
            });
        }

        function followersList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/followersList.jsp',
                controller :'FollowersListController',
                controllerAs: 'followersCtrl',
                size: 'sm'
            });
            vm.modalInstance.result.then(
                function () {

                },function() {
                    followingCount();
                    followersCount();
                })
        }

        function followingList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/followingList.jsp',
                controller :'FollowingListController',
                controllerAs: 'followingCtrl',
                size: 'sm'
            });
            vm.modalInstance.result.then(
                function () {
                },function() {
                    followingCount();
                    followersCount();
                })
        }

        function allPhotos() {
            HttpService.get("/allPhotos/" + $localStorage.openProfileOf.username).then(function (value) {
                vm.photoList = value;
                vm.showList = false;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function commentModal(image_path, caption) {
            $rootScope.photo = image_path;
            $rootScope.caption = caption;

            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/comments.jsp',
                controller: 'CommentsController',
                controllerAs: 'comment',
                size: 'md'
            });
        }

        function profilePhoto() {
            HttpService.get("/getProfilePhoto/" + $localStorage.openProfileOf.username).then(function (value) {
                $rootScope.pic = value.profile_pic;
                $localStorage.profilePicture = value.profile_pic;
                vm.profilePicture = value.profile_pic;
                vm.profilePhotoList = value;
                console.log("success");
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }
    }
})();