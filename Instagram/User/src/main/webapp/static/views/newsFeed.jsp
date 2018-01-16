<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Instagram</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#/main">Home</a></li>
            <li><a href="/#!/profile">Profile</a></li>
        </ul>
    </div>
</nav>
<section>
    <div align="center" ng-repeat="post in main.posts">
        <div class="overlay">
            <span></span>
        </div>
        <div class='caption'>
            </a><span>{{post.caption}}</span>
        </div>

        <div class="thumbnail principal-post">
            <img src="/uploads/{{post.image_path}}">
        </div>
        <label>Created On:
            <span class="date-of-post">{{post.created_date}}</span>
        </label>
        <div class='footer'>

            <div class='react'>
                <a href='#' role='button'><span class='love'></span></a>
                <a href='#' role='button'><span class='comment'></span></a>
                <a href='#' role='button'><span class='save'></span></a>

            </div><br>

            <div class='comment-section'>
                <input type='text' id='cmnt' ng-model="post.comment" placeholder='Add a comment...'>
                <button ng-click="main.addComment()">Add</button>
                <span class='dot02'></span>
            </div>
        </div> <!-- end Footer -->
    </div>
</section>
