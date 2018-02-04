(function () {
    angular.module("adminModule").controller("UserUploadsController", UserUploadsController);

    UserUploadsController.$inject = ['HttpService', '$localStorage'];

    function UserUploadsController(HttpService, $localStorage) {
        var vm = this;
        vm.uploadList = [];
        vm.showing = false;
        vm.showList = true;
        vm.getUploadsOfUser = getUploadsOfUser;
        vm.showComments = showComments;
        getUploadsOfUser();

        function getUploadsOfUser() {
            HttpService.get("/getUploadsOf/" + $localStorage.showUploadsOfUser).then(
                function (value) {
                    vm.uploadList = value;
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
    }
})();
