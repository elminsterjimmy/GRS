<section class="hbox stretch">
  #parse("/fragment/mainAside.tpl")
  <section id="content">
    <section class="hbox stretch">
      <section>
        <section class="vbox">
          <!-- main -->
          <section class="scrollable padder-lg w-f-md" id="bjax-target">
            #if($view.main_content)
              #parser("${vm.view.main_content}.tpl")
            #end
          </section>
          <!-- footer -->
          <footer class="footer bg-dark">
            #if($view.main_content)
              #parser("${vm.view.main_footer}.tpl")
            #end
          </footer>
        </section>
      </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
  </section>
</section>