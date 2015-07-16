<!-- .aside -->
<aside class="bg-black dk nav-xs aside hidden-print" id="nav">
  <section class="vbox">
    <section class="w-f-md scrollable">
      <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px"
        data-railOpacity="0.2">
        <!-- nav -->
        <nav class="nav-primary hidden-xs">
          <ul class="nav bg clearfix">
            <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
              #springMessage("view.main.aside.discover.text")
            </li>
            <li><a href="index.html"> <i class="icon-disc icon text-success"></i> 
                <span class="font-bold">#springMessage("view.main.aside.whats.new.text")</span>
              </a>
            </li>
            <li><a href="gameCollections2.html" data-target="#bjax-target" data-el="#bjax-el"
              data-replace="true"> <i class="icon-music-tone-alt icon text-info"></i> 
                <span class="font-bold">#springMessage("view.main.aside.game.collections.text")</span>
              </a>
            </li>
            <li><a href="events.html"> <i class="icon-drawer icon text-primary-lter"></i> <b
              class="badge bg-primary pull-right">6</b> 
                <span class="font-bold">#springMessage("view.main.aside.events.text")</span>
              </a>
            </li>
            <li><a href="trophies.html" data-target="#bjax-target" data-el="#bjax-el" data-replace="true">
              <i class="icon-list icon  text-info-dker"></i> 
                <span class="font-bold">#springMessage("view.main.aside.trophies.text")</span>
              </a>
            </li>
            <li><a href="statistics.html" data-target="#bjax-target" data-el="#bjax-el" data-replace="true">
              <i class="icon-social-youtube icon  text-primary"></i> 
                <span class="font-bold">#springMessage("view.main.aside.statistics.text")</span>
              </a>
            </li>
            <li><a href="video.html" data-target="#bjax-target" data-el="#bjax-el" data-replace="true"> <i
              class="icon-social-youtube icon  text-primary"></i> 
                <span class="font-bold">#springMessage("view.main.aside.badges.text")</span>
              </a>
            </li>
            <li class="m-b hidden-nav-xs"></li>
          </ul>
        </nav>
        <!-- / nav -->
      </div>
    </section>
    <footer class="footer hidden-xs no-padder text-center-nav-xs">
      <div class="bg hidden-xs ">
        <div class="dropdown dropup wrapper-sm clearfix">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
            <span class="thumb-sm avatar pull-left m-l-xs">
              <img src="images/a3.png" class="dker" alt="...">
              <i class="on b-black"></i>
            </span> 
            <span class="hidden-nav-xs clear"> 
              <span class="block m-l"> 
              <strong class="font-bold text-lt">$user.name</strong>
              <b class="caret"></b>
            </span>
            <span class="text-muted text-xs block m-l">$user.title</span>
            </span>
          </a>
          <ul class="dropdown-menu animated fadeInRight aside text-left">
            <li>
              <span class="arrow bottom hidden-nav-xs"></span>
              <a href="#">#springMessage("view.main.aside.setting.text")</a>
            </li>
            <li>
              <a href="profile.html">#springMessage("view.main.aside.profile.text")</a>
            </li>
            <li>
              <a href="#"> 
                <span class="badge bg-danger pull-right">3</span>
                #springMessage("view.main.aside.notifications.text")
              </a>
            </li>
            <li>
              <a href="docs.html">#springMessage("view.main.aside.help.text")</a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="modal.lockme.html" data-toggle="ajaxModal">#springMessage("view.main.aside.logout.text")</a>
            </li>
          </ul>
        </div>
      </div>
    </footer>
  </section>
</aside>
<!-- /.aside -->