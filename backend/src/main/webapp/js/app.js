/**
 * 
 */
// toggle active
$(document).on(
		'click',
		'[data-toggle^="class"]',
		function(e) {
			e && e.preventDefault();
			var $this = $(e.target), $class, $target, $tmp, $classes, $targets;
			!$this.data('toggle')
					&& ($this = $this.closest('[data-toggle^="class"]'));
			$class = $this.data()['toggle'];
			$target = $this.data('target') || $this.attr('href');
			$class && ($tmp = $class.split(':')[1])
					&& ($classes = $tmp.split(','));
			$target && ($targets = $target.split(','));
			$classes
					&& $classes.length
					&& $.each($targets, function(index, value) {
						if ($classes[index].indexOf('*') !== -1) {
							var patt = new RegExp('\\s'
									+ $classes[index].replace(/\*/g,
											'[A-Za-z0-9-_]+').split(' ').join(
											'\\s|\\s') + '\\s', 'g');
							$($this).each(function(i, it) {
								var cn = ' ' + it.className + ' ';
								while (patt.test(cn)) {
									cn = cn.replace(patt, ' ');
								}
								it.className = $.trim(cn);
							});
						}
						($targets[index] != '#')
								&& $($targets[index]).toggleClass(
										$classes[index])
								|| $this.toggleClass($classes[index]);
					});
			$this.toggleClass('active');
		});
/**
// update data-target
$(document).on('click', 'a', function(e) {
	var $this = $(e.target);
	var href = $this.attr("href");
	var $target = $($this.attr("data-target"));
	$.get(href).done(function(data) {
	    $target.html(data);
	    if ("statistics.html" == href) {
	    	platformChart();
	    	trophyChart();
	    	monthActiveChart();
	    	trophyProcessChart();
	    	trophyByDayChart();
	    	trophyByHourChart();
	    } else if ("gameCollections.html" == href) {
	    	
	    }
	  });
	return false;
});
*/