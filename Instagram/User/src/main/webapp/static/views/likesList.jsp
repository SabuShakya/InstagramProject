<div class="modal-header">
    <h1 class="modal-title" id="modal-title">Liked By:</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="like in likesctrl.likes"></div>
    <%--<img src="/uploads/+">--%>
    {{like.username}}
</div>
<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="likesctrl.cancel()">Cancel</button>
</div>