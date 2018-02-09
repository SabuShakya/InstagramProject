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
        vm.showList = true;
        vm.followers = '';
        vm.following = '';
        vm.totalPictures = '';
        $rootScope.message = '';
        $rootScope.saved = false;
        $rootScope.photo = '';
        $rootScope.clickedPhoto = '';
        $rootScope.pic = '';

        vm.userDisplayName = $localStorage.openProfileOf.username;

        vm.commentModal = commentModal;
        vm.allPhotos = allPhotos;
        vm.followCount = followCount;
        vm.profilePhoto = profilePhoto;
        vm.followersList = followersList;
        vm.followingList = followingList;

        allPhotos();
        followCount();
        profilePhoto();

        function followCount() {
            HttpService.get("/followsCount/" + $localStorage.openProfileOf.username).then(function (value) {
                vm.followers = value.followers;
                vm.following = value.following;
                vm.totalPictures = value.totalPictures;
            }, function (reason) {
                console.log(reason);
            });
        }

        function followersList() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/followersList.jsp',
                controller: 'FollowersListController',
                controllerAs: 'followersCtrl',
                size: 'lg'
            });
        }

        function followingList() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/followingList.jsp',
                controller: 'FollowingListController',
                controllerAs: 'followingCtrl',
                size: 'lg'
            });
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
                templateUrl: '/static/views/comments.jsp',
                controller: 'CommentsController',
                controllerAs: 'comment',
                size: 'lg'
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