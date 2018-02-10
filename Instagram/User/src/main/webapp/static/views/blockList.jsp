<div ng-include src="'static/views/navigation.html'"></div>

hyaaa
<div ng-controller="UpdateController as update">
<div ng-repeat="list in update.blockList">
    <h2> {{list.blockedUsername}} </h2>
</div>
</div>