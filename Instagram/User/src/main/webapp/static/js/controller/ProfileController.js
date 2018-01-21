(function(){
    angular.module('userModule').controller('ProfileController', ProfileController);
    ProfileController.$inject =['$uibModal', '$rootScope','HttpService','$localStorage', '$location'];

    function ProfileController($uibModal, $rootScope, HttpService, $localStorage, $location){
        var vm =this;
        vm.userDisplayName= '';
        vm.photoList=[];
        vm.showList = true;
        vm.followers = '';
        vm.following = '';
        vm.totalPictures = '';
        $rootScope.message='';
        $rootScope.saved = false;
        $rootScope.photo = '';
        $rootScope.profilepic='';

        vm.url = "/allPhotos/" + $localStorage.storedObj.username;
        vm.userDisplayName = $localStorage.storedObj.username;

        vm.openModal=openModal;
        vm.editProfile=editProfile;
        vm.commentModal=commentModal;

        HttpService.get(vm.url).then(function(value){
            vm.photoList = value;
            vm.showList = false;
        },function (reason) {
            console.log("Error occured"+reason);
        });

        HttpService.get("/followsCount/"+$localStorage.storedObj.username).then(function (value) {
            vm.followers = value.followers;
            vm.following = value.following;
            vm.totalPictures = value.totalPictures;
        },function (reason) {
            console.log(reason);
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

        function editProfile() {
            vm.modalInstance=$uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/static/views/uploadProfilePhoto.jsp',
                controller :'UploadProfilePhotoController',
                controllerAs: 'profilePhoto',
                size: 'lg'
            });

        }
    }
})();