(function(){
    angular.module('userModule').controller('ProfileController', ProfileController);
    ProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location', '$interval'];

    function ProfileController($uibModal, $rootScope, HttpService, $localStorage, $location, $interval){
        var vm =this;
        vm.userDisplayName= '';
        vm.photoList=[];
        vm.profilePhotoList=[];
        vm.showList = true;
        vm.followers = '';
        vm.following = '';
        vm.totalPictures = '';
        $rootScope.saved = false;
        $rootScope.photo = '';
        $rootScope.pic='';

        vm.userDisplayName = $localStorage.storedObj.username;

        vm.openModal=openModal;
        vm.editProfile=editProfile;
        vm.commentModal=commentModal;
        vm.allPhotos= allPhotos;
        vm.followCount=followCount;
        vm.profilePhoto=profilePhoto;
        vm.followersList=followersList;
        vm.followingList=followingList;

        allPhotos();
        // followCount();
        // profilePhoto();

        function followCount(){
            HttpService.get("/followsCount/"+$localStorage.storedObj.username).then(function (value) {
                vm.followers = value.followers;
                vm.following = value.following;
                vm.totalPictures = value.totalPictures;
            },function (reason) {
                console.log(reason);
            });
        }
        // $interval(vm.followCount,1000);

        function followersList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/followersList.jsp',
                controller :'FollowersListController',
                controllerAs: 'followersCtrl',
                size: 'lg'
            });
        }

        function followingList(){
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/followingList.jsp',
                controller :'FollowingListController',
                controllerAs: 'followingCtrl',
                size: 'lg'
            });
        }

        function allPhotos(){
            HttpService.get("/allPhotos/" + $localStorage.storedObj.username).then(function (value) {
                vm.photoList = value;
                vm.showList = false;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function openModal(){
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/upload.jsp',
                controller :'ImgUploadController',
                controllerAs: 'img',
                size: 'lg'
            });

            vm.modalInstance.result.then(
                function(){
                   allPhotos();
                   followCount();
                },
                function(){})
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

        function profilePhoto() {
            HttpService.get("/ProfilePhotos/" + $localStorage.storedObj.username).then(function (value) {
                $rootScope.pic = value.profile_pic;
                vm.profilePhotoList = value;
                console.log("success");
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function editProfile() {
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/uploadProfilePhoto.jsp',
                controller :'UploadProfilePhotoController',
                controllerAs: 'profilePhoto',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function(){
                    profilePhoto();
                },
                function(){})
        }
    }
})();