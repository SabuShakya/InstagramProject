(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['$localStorage','HttpService'];

    function MainController($localStorage,HttpService) {
        var vm = this;
        vm.imageList = [];
        vm.message = '';
        vm.showFollowMsg = false;
        vm.url = "/showPhotos/"+$localStorage.uname+"/"+$localStorage.tokenNo;

        HttpService.get(url).then(function (value) {

        },function (reason) {

        });

    }
})();

