<div ng-include src="'static/views/navigation.html'"></div>
<div class ="container">

<img class="img-responsive img-circle margin" src="uploads/{{update.image}}" width="20%" height="10%">
    <h2>{{update.userDisplayName}}</h2>
</div>

<div class="container">
<a href="#!/changePassword">Change Password</a>
    <br>
    <br>

<%--<div>--%>
    <%--Private account:--%>
    <%--<i class="fa fa-toggle-on active" ng-if="update.status == true" ng-click="update.init()" ng-model="update.status"></i>--%>
    <%--<i class="fa fa-toggle-on fa-rotate-180 inactive" ng-if="update.status == false" ng-click="update.changeStatus()" ng-model="update.status"></i>--%>
    <%--&lt;%&ndash;<pre>{{update.status }}</pre>&ndash;%&gt;--%>
<%--</div>--%>

<div>
    <div ng-if="update.showPrivateBtn">
        Private account:<i class="fa fa-toggle-on active" ng-click="update.init()"></i>
</div>

<div ng-hide="update.showPrivateBtn">
    Private account:<i class="fa fa-toggle-on fa-rotate-180 inactive" ng-click="update.changeStatus()"></i>
</div>
</div>
    <br>

<div>
    <a href="#!/deactivateAccount">Temporarily disable my account</a>
</div>
</div>


