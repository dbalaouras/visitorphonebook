var $loginBox = $('#loginBox');
var $aboutBox = $('#aboutBox');

// focus on the username field when the loginbox is shown
$loginBox.on('shown', function() {
	$('#username_or_email').focus();
});

$aboutBox.on('shown', function() {
	$('#about_box_close_btn').focus();
});

/*
 * ! jquery.scrollto.js 0.0.1 - https://github.com/yckart/jquery.scrollto.js
 * Scroll smooth to any element in your DOM.
 * 
 * Copyright (c) 2012 Yannick Albert (http://yckart.com) Licensed under the MIT
 * license (http://www.opensource.org/licenses/mit-license.php). 2013/02/17
 */
$.scrollTo = $.fn.scrollTo = function(x, y, options) {
	if (!(this instanceof $))
		return $.fn.scrollTo.apply($('html, body'), arguments);

	options = $.extend({}, {
		gap : {
			x : 0,
			y : 0
		},
		animation : {
			easing : 'swing',
			duration : 600,
			complete : $.noop,
			step : $.noop
		}
	}, options);

	return this.each(function() {
		var elem = $(this);
		elem.stop().animate(
				{
					scrollLeft : !isNaN(Number(x)) ? x : $(y).offset().left
							+ options.gap.x,
					scrollTop : !isNaN(Number(y)) ? y : $(y).offset().top
							+ options.gap.y
				}, options.animation);
	});
};

$('ul.bs-docs-sidenav a').click(function(e) {
	"use strict";
	$('html,body').scrollTo(this.hash, this.hash);
	e.preventDefault();
});
