/* Common JavaScript for jQuery demonstration pages. */
$(function () {
	$('head').prepend('<link href="kw-name.rss.xml" type="application/rss+xml" rel="alternate" title="RSS Feed">');
	// Add header links
	var current = location.href.replace(/^.*[\\\/]([^\.]+)\.[^\.]+$/, '$1').toLowerCase();
	$('<div id="header-links">' +
		(current == 'index' ? '' : '<a id="home" href="index.html">' +
		'<img src="img/homePage.png" alt="Home page" title="Home page"></a>&nbsp;') +
		'<a href="#" id="crosslink"><img src="img/plugins.png" alt="Other plugins" title="Other plugins"></a>&nbsp;' +
		'<div id="crosslinks"><ul>' +
		(current == 'backgroundpos' ? '' : '<li><a href="backgroundPos.html">Background Position</a></li>') +
		(current == 'bookmark'      ? '' : '<li><a href="bookmark.html">Bookmark</a></li>') +
		(current == 'calculator'    ? '' : '<li><a href="calculator.html">Calculator</a></li>') +
		(current == 'calendars'     ? '' : '<li><a href="calendars.html">Calendars</a></li>') +
		(current == 'countdown'     ? '' : '<li><a href="countdown.html">Countdown</a></li>') +
		(current == 'dateentry'     ? '' : '<li><a href="dateEntry.html">Date Entry</a></li>') +
		(current == 'datetimeentry' ? '' : '<li><a href="datetimeEntry.html">Date/Time Entry</a></li>') +
		(current == 'datepick'      ? '' : '<li><a href="datepick.html">Datepicker</a></li>') +
		(current == 'flightboard'   ? '' : '<li><a href="flightBoard.html">Flight Board</a></li>') +
		(current == 'gchart'        ? '' : '<li><a href="gChart.html">Google Chart</a></li>') +
		(current == 'gsblogbar'     ? '' : '<li><a href="gsblogbar.html">Google Search Blogbar</a></li>') +
		(current == 'gsbookbar'     ? '' : '<li><a href="gsbookbar.html">Google Search Bookbar</a></li>') +
		(current == 'gsnewsbar'     ? '' : '<li><a href="gsnewsbar.html">Google Search Newsbar</a></li>') +
		(current == 'gsvideobar'    ? '' : '<li><a href="gsvideobar.html">Google Search Videobar</a></li>') +
		'</ul><ul>' +
		(current == 'icalendar'     ? '' : '<li><a href="icalendar.html">iCalendar</a></li>') +
		(current == 'imagecube'     ? '' : '<li><a href="imageCube.html">Image Cube</a></li>') +
		(current == 'keypad'        ? '' : '<li><a href="keypad.html">Keypad</a></li>') +
		(current == 'labeleffect'   ? '' : '<li><a href="labelEffect.html">Label Effect</a></li>') +
		(current == 'linkedsliders' ? '' : '<li><a href="linkedSliders.html">Linked Sliders</a></li>') +
		(current == 'localisation'  ? '' : '<li><a href="localisation.html">Localisation</a></li>') +
		(current == 'maxlength'     ? '' : '<li><a href="maxlength.html">Max Length</a></li>') +
		(current == 'more'          ? '' : '<li><a href="more.html">More</a></li>') +
		(current == 'realperson'    ? '' : '<li><a href="realPerson.html">Real Person</a></li>') +
		(current == 'relationships' ? '' : '<li><a href="relationships.html">Relationships</a></li>') +
		(current == 'svg'           ? '' : '<li><a href="svg.html">SVG Integration</a></li>') +
		(current == 'themes'        ? '' : '<li><a href="themes.html">Themes</a></li>') +
		(current == 'timeentry'     ? '' : '<li><a href="timeEntry.html">Time Entry</a></li>') +
		'</ul></div>' +
		'<a href="kw-name.rss.xml"><img src="img/rss.png" alt="RSS Feed" title="RSS Feed"></a>' +
		'</div>').insertBefore('h1');
	var crosslink = $('#crosslink');
	var offset = crosslink.offset();
	var crosslinks = $('#crosslinks');
	crosslink.click(function() {
		crosslinks.css({left: ($.browser.msie || $.browser.opera ?
			offset.left : crosslink[0].offsetLeft) +
			crosslink.width() - crosslinks.width(),
			top: ($.browser.msie || $.browser.opera ? offset.top :
			crosslink[0].offsetTop) + crosslink.height()});
		crosslinks.slideToggle();
	});
	$(document).mousedown(function(event) {
		var target = $(event.target);
		if (!target.parents().andSelf().is('#crosslink,#crosslinks')) {
			crosslinks.slideUp();
		}
	});
	$('#download').append(' <img src="img/download.png" alt="" style="">');
	if ($.fn.bookmark) {
		$('#bookmark').bookmark({compact: true, popup: true, icons: 'img/bookmarks.png',
			popupText: '<button>Bookmark this <img src="img/bookmarker.png" alt=""></button>'});
	}
	// Ratings
	showRating(current, '#rating', ': ');
	// Stripe tables
	$('table').each(function() {
		$('tr:odd', this).addClass('alternate');
	});
	// Initialise tabs
	if ($.fn.tabs) {
		$('#tabs').tabs($.fn.tabs.tabProps);
	}
	// Execute example script tags
	$('code.jsdemo').each(function () {
		$(this).removeClass('jsdemo').addClass('js').hide().
			wrap('<div class="showCode"></div>').
			before('<a href="#" class="showCode">Show code</a>').
			text('\n' + $(this).text());
		eval($(this).text().replace(/&lt;/g, '<').replace(/&gt;/g, '>'));
	});
	$('code.css').each(function () {
		$(this).hide().wrap('<div class="showCode"></div>').
			before('<a href="#" class="showCode">Show CSS</a><br>');
	});
	$('code.htmldemo').each(function () {
		$(this).removeClass('htmldemo').addClass('html').
			hide().wrap('<div class="showCode"></div>').
			before('<a href="#" class="showCode">Show HTML</a><br>');
	});
	$('a.showCode').toggle(function() {
		$(this).text($(this).text().replace(/Show/, 'Hide')).
			parent().css('width', 'auto').find('code').show();
		return false;
	}, function() {
		$(this).text($(this).text().replace(/Hide/, 'Show')).
			parent().css('width', '80px').find('code').hide();
		return false;
	});
	// Code highlighting
	if ($.fn.chili) {
		$('code').chili({recipeFolder: 'js/'});
	}
});

function showRating(name, id, prefix) {
	var rating = {backgroundpos: [0.0, 0], bookmark: [4.0, 9],
		calculator: [4.5, 8], calendars: [5.0, 4], calendarspicker: [5.0, 4],
		countdown: [4.5, 50], dateentry: [4.5, 14],
		datepick: [5.0, 49], datetimeentry: [4.0, 1],
		flightboard: [4.5, 3], gchart: [4.5, 23],
		gsblogbar: [0.0, 0], gsbookbar: [0.0, 0],
		gsnewsbar: [0.0, 0], gsvideobar: [3.5, 2],
		icalendar: [4.5, 4], imagecube: [4.0, 8],
		keypad: [4.5, 10], labeleffect: [0.0, 0],
		linkedsliders: [0.0, 0], localisation: [3.0, 3],
		realperson: [4.0, 4], relationships: [4.0, 1],
		svg: [4.5, 58], themes: [4.0, 2],
		timeentry: [4.0, 41]}[name];
	var html = '';
	if (rating) {
		html = (prefix || '') + '<span>';
		for (var i = 0; i < 5; i++) {
			html += '<img src="img/star' + (i + 0.5 == rating[0] ? '.5' :
				(i < rating[0] ? '1' : '0')) + '.gif">';
		}
		html += '</span> (' + rating[1] + ' vote' + (rating[1] != 1 ? 's' : '') + ')';
	}
	$(id).addClass('rating').html(html);
}

function jumpTo(tab, id) {
	$('#tabs').triggerTab(tab);
	setTimeout(function() { scrollTo(0, $('a[name=' + id + ']').offset().top); }, 100);
	return false;
}
