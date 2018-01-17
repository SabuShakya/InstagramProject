(function() {
    angular.module('userModule').controller("MainController", MainController);
    MainController.$inject= ['HttpService','$localStorage'];

    function MainController(HttpService,$localStorage) {
        var vm= this;
        vm.posts = {};
        vm.message = '';
        vm.addComment = addComment;

        HttpService.get("/getPosts/"+$localStorage.storedObj.username).then(
            function (value) {
                vm.posts = value;
            },function (reason) {
                vm.message = "Follow Others to see their posts.";
            });
        
        function addComment() {

        }
    }
})();

